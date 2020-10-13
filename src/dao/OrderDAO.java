package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import controller.ApplicationController;
import entity.Client;
import entity.Employee;
import entity.Order;
import entity.Product;
import entity.Warehouse;

public class OrderDAO {
	private Connection myConn;

	public OrderDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB orders connection success");
	}

	public List<Order> readAll() throws Exception {
		List<Order> list = new ArrayList<Order>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM orders");
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Order> search(String productName) throws Exception {
		List<Order> list = new ArrayList<Order>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			productName = "%" + productName + "%";
			myStmt = myConn.prepareStatement(
					"SELECT * FROM orders WHERE id_product in (SELECT id FROM product where name LIKE ?)");
			myStmt.setString(1, productName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Order entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into orders"
					+ " (id_employee, id_client, id_product, dates, count)" + " values (?, ?, ?, ?,?)");
			myStmt.setLong(1, entity.getEmployee().getId());
			myStmt.setLong(2, entity.getClient().getId());
			myStmt.setLong(3, entity.getProduct().getId());
			myStmt.setDate(4, entity.getDate());
			myStmt.setInt(5, entity.getCount());
			myStmt.executeUpdate();

			// past
			Warehouse warehouse = ApplicationController.warehouseController.getDAO()
					.readAllByIdProduct(entity.getProduct().getId()).get(0);
			if (warehouse.getCount() - entity.getCount() <= 0) {
				ApplicationController.warehouseController.getDAO().Delete(warehouse.getId());
			} else {
				warehouse.setCount(warehouse.getCount() - entity.getCount());
				ApplicationController.warehouseController.getDAO().update(warehouse);
			}
		} finally {
			close(myStmt);
		}
	}

	public List<Order> read(Long id) throws Exception {
		List<Order> list = new ArrayList<Order>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM orders WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Order tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Order entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			Order past = read(entity.getId()).get(0);
			Warehouse warehouse = ApplicationController.warehouseController.getDAO()
					.readAllByIdProduct(past.getProduct().getId()).get(0);

			int countDifference = past.getCount() - entity.getCount();
			myStmt = myConn.prepareStatement(
					"UPDATE orders SET id_employee=?, id_client=?, id_product=?, dates=?, count=? WHERE id=?");
			myStmt.setLong(1, entity.getEmployee().getId());
			myStmt.setLong(2, entity.getClient().getId());
			myStmt.setLong(3, entity.getProduct().getId());
			myStmt.setDate(4, entity.getDate());
			myStmt.setInt(5, entity.getCount());
			myStmt.setLong(6, entity.getId());
			myStmt.executeUpdate();
			// past
			warehouse.setCount(warehouse.getCount() + past.getCount());
			ApplicationController.warehouseController.getDAO().update(warehouse);
			// new
			List<Warehouse> readAllByIdProduct = ApplicationController.warehouseController.getDAO()
					.readAllByIdProduct(entity.getProduct().getId());
			if (readAllByIdProduct.size() > 0) {
				Warehouse warehouse2 = readAllByIdProduct.get(0);
				warehouse2.setCount(warehouse2.getCount() - entity.getCount());
				ApplicationController.warehouseController.getDAO().update(warehouse2);
			}

		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			Order delivery = read(id).get(0);

			myStmt = myConn.prepareStatement("DELETE FROM orders WHERE id=?");

			myStmt.setLong(1, id);
			myStmt.executeUpdate();

			Warehouse warehouse = ApplicationController.warehouseController.getDAO()
					.readAllByIdProduct(delivery.getProduct().getId()).get(0);
			warehouse.setCount(warehouse.getCount() + delivery.getCount());
			ApplicationController.warehouseController.getDAO().update(warehouse);

		} finally {
			close(myStmt);
		}
	}

	private Order convertRowToEntity(ResultSet myRs) throws SQLException {
		Product product=null;
		Employee employee=null;
		Client client = null;
		Order temp = null;
		try {
			Long id = myRs.getLong("id");
			Long id_employee= myRs.getLong("id_employee");
			Long id_client= myRs.getLong("id_client");
			Long id_product = myRs.getLong("id_product");
			Date dates = myRs.getDate("dates");
			int count = myRs.getInt("count");
			
			employee= ApplicationController.employeeController.getDAO().read(id_employee).get(0);
			client= ApplicationController.clientController.getDAO().read(id_client).get(0);
			product = ApplicationController.productController.getDAO().read(id_product).get(0);
			
			temp = new Order(employee, client, product, dates, count);
			temp.setId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}
}
