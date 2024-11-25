package com.GUESST.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.GUESST.bean.Owner;
import com.GUESST.bean.Tenant;

public class OwnerDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "jayesh0310";
	
	
	private static final String INSERT_USERS_SQL = "INSERT INTO owner" + "  (name, email, password, building) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id, name, email, password, building from owner where id =?";
	private static final String SELECT_USER_BY_EMAILID = "select id, name, email, password, building from owner where email =?";
	private static final String SELECT_ALL_USERS = "select * from owner";
	private static final String DELETE_USERS_SQL = "delete from owner where id = ?;";
	private static final String UPDATE_USERS_SQL = "update owner set name = ?, email= ?, password=?, building=? where id = ?;";
	
	
	
	protected Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	// CRUD Operation methods
	
	// insert User
	
	public void insertOwner(Owner owner) {
		
		System.out.println(INSERT_USERS_SQL);
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			
			preparedStatement.setString(1, owner.getName());
			preparedStatement.setString(2, owner.getEmail());
			preparedStatement.setString(3, owner.getPassword());
			preparedStatement.setString(4, owner.getBuildingName());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			printSQLException(e);
		}
		
	}
	
	// Select User by ID
	
		public Owner selectOwner(int id) {
			Owner owner = null;
			
			// Step 1: Establishing a connection
			try(Connection connection = getConnection();
					//Step 2: Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);){
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				
				// Step 3: Execute the query or update query
				
				ResultSet rs = preparedStatement.executeQuery();
				
				// in insert we used updatequery as it doesn't return anything after inserting object and executeQuery returns the object of resultset
				
				// Step 4: Process the ResultSet object
				
				while(rs.next()) {
					String name = rs.getString("name");
					String password = rs.getString("password");
					String email = rs.getString("email");	
					
					String building = rs.getString("building");
				
					
					owner = new Owner(id, name, email, password, building);
				}
				
			} catch(SQLException e) {
				printSQLException(e);
			}
			return owner;
		}
		
		// Select user by email id --- for login
		
		
		public Owner selectOwnerByEmail(String email) {
			Owner owner = null;
			
			// Step 1: Establishing a connection
			try(Connection connection = getConnection();
					//Step 2: Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAILID);){
				preparedStatement.setString(1, email);
				System.out.println(preparedStatement);
				
				// Step 3: Execute the query or update query
				
				ResultSet rs = preparedStatement.executeQuery();
				
				// in insert we used updatequery as it doesn't return anything after inserting object and executeQuery returns the object of resultset
				
				// Step 4: Process the ResultSet object
				
				while(rs.next()) {
					int id = rs.getInt("id");	
					String name = rs.getString("name");
					String password = rs.getString("password");
					
					String building = rs.getString("building");
				
					
					owner = new Owner(id, name, email, building, password);
				}
				
				
			} catch(SQLException e) {
				printSQLException(e);
			}
			return owner;
		}
		
		// Update user Method
		
		public boolean updateOwner(Owner owner) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				System.out.println("updated USer:"+preparedStatement);
				
				preparedStatement.setString(1, owner.getName());
				preparedStatement.setString(2, owner.getEmail());
				preparedStatement.setString(3, owner.getPassword());
				preparedStatement.setString(4, owner.getBuildingName());

				rowUpdated = preparedStatement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		
		
		private void printSQLException(SQLException ex) {
			// TODO Auto-generated method stub
			for(Throwable e: ex) {
				if(e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage() );
					Throwable t = e.getCause();
					while( t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
}
