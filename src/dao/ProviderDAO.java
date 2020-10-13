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

import entity.Provider;

public class ProviderDAO {
	private Connection myConn;

	public ProviderDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		System.out.println(user + " / " + password + " / " + dburl);
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB provider connection success");
	}

	public List<Provider> readAll() throws Exception {
		List<Provider> list = new ArrayList<Provider>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM provider");
			while (myRs.next()) {
				Provider tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Provider> search(String name) throws Exception {
		List<Provider> list = new ArrayList<Provider>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM provider WHERE name LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Provider tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Provider provider) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into provider" + " (name, adres,phone,email)" + " values (?, ?, ?, ?)");
			myStmt.setString(1, provider.getName());
			myStmt.setString(2, provider.getAdres());
			myStmt.setString(3, provider.getPhone());
			myStmt.setString(4, provider.getEmail());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Provider> read(Long id) throws Exception {
		List<Provider> list = new ArrayList<Provider>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM provider WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Provider tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Provider provider) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE provider SET name=?, adres=?, phone=?, email=? WHERE id=?");
			myStmt.setString(1, provider.getName());
			myStmt.setString(2, provider.getAdres());
			myStmt.setString(3, provider.getPhone());
			myStmt.setString(4, provider.getEmail());
			myStmt.setLong(5, provider.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM provider WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Provider convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		String name = myRs.getString("name");
		String adres = myRs.getString("adres");
		String phone = myRs.getString("phone");
		String email = myRs.getString("email");
		Provider temp = new Provider(name, adres, phone, email);
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
