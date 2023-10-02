package di.evaluacioninic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class prueba {
	
	public static void main(String[] args) {
		registerUser("paco", "213213");
		loginUser("paco", "2312231");
	}
	
	private static final String URL_BD = "jdbc:sqlite:db/users.db";

	public static String registerUser(String username, String password) {
		// Connection declaration
		Connection con = null;
		String msg = "";
		try {
			// Gets the connection
			con = DriverManager.getConnection(URL_BD);
			
			// Create statement
			Statement insert = con.createStatement();

			// Insert sentence
			String sqlInsert = "INSERT INTO user (username, password) VALUES " + "(" + username + ", " + password
					+ ");";

			// Execute insert
			int res = insert.executeUpdate(sqlInsert);

			if (res == 1) {
				msg = "Regiser succesfull";
			} else {
				msg = "Error during registration";
			}
			
		} catch (SQLException e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return msg;
	}
public static String loginUser(String username, String password) {
		
		Connection con = null;
		String msg = "";
		try {
			// Gets the connection
			con = DriverManager.getConnection(URL_BD);
			
			// Create statement
			Statement select = con.createStatement();

			// Select sentence
			ResultSet res = select.executeQuery("SELECT username FROM users WHERE username = " + username);
			
			if (res.next()) {
				msg = "User logged in succesfully";
			} else {
				msg = "User does not exist";
			}
			
		} catch (SQLException e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		
		return msg;
	}
}
