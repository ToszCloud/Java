package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {
	protected Connection connection = null;
	
	static final String DB_URL = "jdbc:mysql://localhost/UTP";
	static final String USER = "root";
	static final String PASS = "root";
	
	
	public Connection getConnection() {
		try {
			
			//step 2
			Class.forName("com.mysql.jdbc.Driver");
			
			//step 3
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection to database succeeded");
			
			//step 4
			connection.setAutoCommit(false);
			
			
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

			//e.printStackTrace();
		}
		
		return connection;
	}
	
	public void commitTransaction() {
		
		if(connection != null) {
			try {
				connection.commit();
				
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Transaction commited");
		} else {
			System.out.println("No connection to database");
		}
		
		
	}
	
	public void rollbackTransaction() {
		if(connection != null) {
			try {
				connection.rollback();
				
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Rollback complete");
		} else {
			System.out.println("No connection to database");
		}
	}
	
	public void beginTransaction() {
		if(connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No connection to database");
		}
		
	}
	
	
	
	
}
