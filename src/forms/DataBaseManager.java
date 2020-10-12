package forms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {
	Connection connection;
	String login = "root", password = "1234", databaseName = "jsf";
	PreparedStatement st ;
	public void init() {
		try {
			Connection connection = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/" + databaseName, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
//	  PreparedStatement st = (PreparedStatement) connection
//              .prepareStatement("Select name, password from student where name=? and password=?");
//
//          st.setString(1, userName);
//          st.setString(2, password);
//          ResultSet rs = st.executeQuery();
//          if (rs.next()) {
//              dispose();
//              UserHome ah = new UserHome(userName);
//              ah.setTitle("Welcome");
//              ah.setVisible(true);
//              JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
//          } else {
//              JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
//          }
}
