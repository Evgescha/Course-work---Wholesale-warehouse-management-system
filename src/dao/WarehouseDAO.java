package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import controller.ApplicationController;
import entity.Product;
import entity.Warehouse;


public class WarehouseDAO {
	private Connection myConn;

	public WarehouseDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB warehouse connection success");
	}

	public List<Warehouse> readAll() throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM warehouse");
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Warehouse> readAllWithCountMoreThenZero() throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM warehouse WHERE count>0");
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	public List<Warehouse> readAllByIdProduct(Long id_product) throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("SELECT * FROM warehouse WHERE id_product i= ?");
			myStmt.setLong(1, id_product);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Warehouse> search(String productName) throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			productName= "%" + productName+ "%";
			myStmt = myConn.prepareStatement("SELECT * FROM warehouse WHERE id_product in (SELECT id FROM product WHERE name LIKE ?)");
			myStmt.setString(1, productName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Warehouse entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("INSERT INTO warehouse" + " (id_product, count)" + " values ( ?, ?)");
			myStmt.setLong(1, entity.getProduct().getId());
			myStmt.setInt(2, entity.getCount());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Warehouse> read(Long id) throws Exception {
		List<Warehouse> list = new ArrayList<Warehouse>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM warehouse WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Warehouse tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Warehouse entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE warehouse SET id_product=?, count=? WHERE id=");
			myStmt.setLong(1, entity.getProduct().getId());
			myStmt.setInt(2, entity.getCount());
			myStmt.setLong(3, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM warehouse WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Warehouse convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		Long id_product = myRs.getLong("id_product");
		int count = myRs.getInt("count");
		Product product = null;
		try {
			product = ApplicationController.productController.getDAO().read(id_product).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Warehouse temp = new Warehouse(product, count);		
		temp.setId(id);
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
