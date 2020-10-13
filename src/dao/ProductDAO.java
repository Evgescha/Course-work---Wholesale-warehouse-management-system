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

import entity.Product;


public class ProductDAO {
	private Connection myConn;

	public ProductDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB product connection success");
	}

	public List<Product > readAll() throws Exception {
		List<Product > list = new ArrayList<Product >();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM product");
			while (myRs.next()) {
				Product tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Product > search(String name) throws Exception {
		List<Product > list = new ArrayList<Product >();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name= "%" + name+ "%";
			myStmt = myConn.prepareStatement("SELECT * FROM product WHERE name LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Product tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Product entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into product" + " (name, price)" + " values (?, ?)");
			myStmt.setString(1, entity.getName());
			myStmt.setInt(2, entity.getPrice());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Product > read(Long id) throws Exception {
		List<Product > list = new ArrayList<Product >();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM product WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Product tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Product entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE product SET name=?, price=? WHERE id=?");
			myStmt.setString(1, entity.getName());
			myStmt.setInt(2, entity.getPrice());
			myStmt.setLong(3, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM product WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Product  convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		String name= myRs.getString("name");
		int price = myRs.getInt("price");		
		Product temp = new Product (name, price);
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
