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

import entity.Employee;


public class EmployeeDAO {
	private Connection myConn;

	public EmployeeDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB client connection success");
	}

	public List<Employee> readAll() throws Exception {
		List<Employee> list = new ArrayList<Employee>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM employee");
			while (myRs.next()) {
				Employee tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Employee> search(String fio) throws Exception {
		List<Employee> list = new ArrayList<Employee>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			fio= "%" + fio+ "%";
			myStmt = myConn.prepareStatement("SELECT * FROM employee WHERE fio LIKE ?");
			myStmt.setString(1, fio);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Employee tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Employee entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn
					.prepareStatement("insert into employee" + " (fio,phone,email)" + " values ( ?, ?, ?)");
			myStmt.setString(1, entity.getFio());
			myStmt.setString(2, entity.getPhone());
			myStmt.setString(3, entity.getEmail());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Employee> read(Long id) throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("SELECT * FROM employee WHERE id=?");
			myStmt.setLong(1, id);
			myRs=myStmt.executeQuery();
			while (myRs.next()) {
				Employee tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Employee entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE employee SET fio=?, phone=?, email=? WHERE id=?");
			myStmt.setString(1, entity.getFio());
			myStmt.setString(2, entity.getPhone());
			myStmt.setString(3, entity.getEmail());
			myStmt.setLong(4, entity.getId());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM employee WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Employee convertRowToEntity(ResultSet myRs) throws SQLException {
		Long id = myRs.getLong("id");
		String fio = myRs.getString("fio");
		String phone = myRs.getString("phone");
		String email = myRs.getString("email");
		Employee temp = new Employee (fio, phone, email);
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
