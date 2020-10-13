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
import entity.Delivery;
import entity.Product;
import entity.Provider;
import entity.Warehouse;

public class DeliveryDAO {
	private Connection myConn;

	public DeliveryDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB delivery connection success");
	}

	public List<Delivery> readAll() throws Exception {
		List<Delivery> list = new ArrayList<Delivery>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM delivery");
			while (myRs.next()) {
				Delivery tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Delivery> search(String productName) throws Exception {
		List<Delivery> list = new ArrayList<Delivery>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			productName = "%" + productName + "%";
			myStmt = myConn.prepareStatement(
					"SELECT * FROM delivery WHERE id_product in (SELECT id FROM product where name LIKE ?)");
			myStmt.setString(1, productName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Delivery tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Delivery entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement(
					"insert into delivery" + " (id_product, id_provider, dates, count)" + " values (?, ?, ?, ?)");
			myStmt.setLong(1, entity.getProduct().getId());
			myStmt.setLong(2, entity.getProvider().getId());
			myStmt.setDate(3, entity.getDate());
			myStmt.setInt(4, entity.getCount());
			myStmt.executeUpdate();
			
			
			//past
			List<Warehouse> readAllByIdProduct = ApplicationController.warehouseController.getDAO().readAllByIdProduct(entity.getProduct().getId());
			if(readAllByIdProduct.size()>0) {
				Warehouse warehouse = readAllByIdProduct.get(0);
				warehouse.setCount(warehouse.getCount()+entity.getCount());
				ApplicationController.warehouseController.getDAO().update(warehouse);
			}else {
				ApplicationController.warehouseController.getDAO().create(new Warehouse(entity.getProduct(), entity.getCount()));
			}
		} finally {
			close(myStmt);
		}
	}

	public List<Delivery> read(Long id) throws Exception {
		List<Delivery> list = new ArrayList<Delivery>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM delivery WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Delivery tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Delivery entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			Delivery past = read(entity.getId()).get(0);
			Warehouse warehouse= ApplicationController.warehouseController.getDAO().readAllByIdProduct(past.getProduct().getId()).get(0);
			
			int countDifference = past.getCount()-entity.getCount();
			myStmt = myConn
					.prepareStatement("UPDATE delivery SET id_product=?, id_provider=?, dates=?, count=? WHERE id=?");
			myStmt.setLong(1, entity.getProduct().getId());
			myStmt.setLong(2, entity.getProvider().getId());
			myStmt.setDate(3, entity.getDate());
			myStmt.setInt(4, entity.getCount());
			myStmt.setLong(5, entity.getId());
			myStmt.executeUpdate();
			//past
			warehouse.setCount(warehouse.getCount()-past.getCount());
			ApplicationController.warehouseController.getDAO().update(warehouse);
			//new
			List<Warehouse> readAllByIdProduct = ApplicationController.warehouseController.getDAO().readAllByIdProduct(entity.getProduct().getId());
			if(readAllByIdProduct.size()>0) {
				Warehouse warehouse2 = readAllByIdProduct.get(0);
				warehouse2.setCount(warehouse2.getCount()+entity.getCount());
				ApplicationController.warehouseController.getDAO().update(warehouse2);
			}else {
				ApplicationController.warehouseController.getDAO().create(new Warehouse(entity.getProduct(), entity.getCount()));
			}
			
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			Delivery delivery=read(id).get(0);
			
			myStmt = myConn.prepareStatement("DELETE FROM delivery WHERE id=?");
			Warehouse warehouse= ApplicationController.warehouseController.getDAO().readAllByIdProduct(delivery.getProduct().getId()).get(0);
			
			
			
			
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
			if(warehouse.getCount()-delivery.getCount()==0)
				ApplicationController.warehouseController.getDAO().Delete(warehouse.getId());
				else {
					warehouse.setCount(warehouse.getCount()-delivery.getCount());
					ApplicationController.warehouseController.getDAO().update(warehouse);
				}
				
			
		} finally {
			close(myStmt);
		}
	}

	private Delivery convertRowToEntity(ResultSet myRs) throws SQLException {
		Product product;
		Delivery temp = null;
		try {
			Long id = myRs.getLong("id");
			Long id_product = myRs.getLong("id_product");
			Long id_provider = myRs.getLong("id_provider");
			Date dates = myRs.getDate("dates");
			int count = myRs.getInt("count");
			product = ApplicationController.productController.getDAO().read(id_product).get(0);
		Provider provider = ApplicationController.providerController.getDAO().read(id_provider).get(0);
		temp = new Delivery(provider, product, dates, count);
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
