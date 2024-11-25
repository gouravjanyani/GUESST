package com.GUESST.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.Date; 
import java.util.List;

import com.GUESST.bean.Tenant;

public class TenantDao {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "jayesh0310";
    
    private static final String INSERT_USERS_SQL = "INSERT INTO tenant (name, email, floor, roomNumber, password, building, DOJ, rent, rentStatus, payVerification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_PAYMENT_PROOF = "UPDATE tenant SET proof = ?, rentStatus=true WHERE id = ?;";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, floor, roomNumber, building, DOJ, rent, rentStatus, payVerification FROM tenant WHERE id = ?";
    private static final String SELECT_USER_BY_EMAILID = "SELECT id, name, password, email, floor, roomNumber, building, DOJ, rent, rentStatus, payVerification, proof FROM tenant WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM tenant WHERE building = ?";
    private static final String SELECT_USER_WITH_PROOF = "SELECT * FROM tenant WHERE building = ? AND id=? AND proof IS NOT NULL";
    private static final String DELETE_USERS_SQL = "DELETE FROM tenant WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE tenant SET name = ?, email = ?, floor = ?, roomNumber = ?, rent = ?, rentStatus = ?, payVerification = ? WHERE id = ?;";
    private static final String UPDATE_PAYMENT_VERIFICATION = "UPDATE tenant SET rentStatus = ?, payVerification = ? WHERE id = ?;";
    private static final String COUNT_SUM_RENT = "SELECT SUM(rent) AS total_rent FROM tenant WHERE building = ?";
    private static final String COUNT_SUM_RENT_COLLECTED = "SELECT SUM(rent) AS total_rent FROM tenant WHERE payVerification = true AND building = ?";
    private static final String COUNT_SUM_RENT_PENDING = "SELECT SUM(rent) AS total_rent FROM tenant WHERE payVerification = false AND building = ?";
    private static final String COUNT_SUM_RENT_VERIFICATION_PENDING = "SELECT SUM(rent) AS total_rent FROM tenant WHERE building = ? AND rentStatus=true AND payVerification=false AND proof IS NOT NULL";
    
    public TenantDao() {
        
    }
    
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
    public void insertTenant(Tenant tenant) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            
            preparedStatement.setString(1, tenant.getName());
            preparedStatement.setString(2, tenant.getEmail());
            preparedStatement.setInt(3, tenant.getFloor());
            preparedStatement.setInt(4, tenant.getRoomNumber());
            preparedStatement.setString(5, tenant.getPassword());
            preparedStatement.setString(6, tenant.getBuilding());
//            Date sqlDate = new Date(tenant.getDOJ().getTime());
            preparedStatement.setString(7, tenant.getDOJ());
            preparedStatement.setInt(8, tenant.getRent());
            preparedStatement.setBoolean(9, tenant.isRentStatus());
            preparedStatement.setBoolean(10, tenant.isPayVerification());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            printSQLException(e);
        }
    }
    
    // insert Payment Proof
    public void updatePaymentProof(Tenant tenant) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT_PROOF)) {
            
            preparedStatement.setString(1, tenant.getProof());
            preparedStatement.setInt(2, tenant.getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            printSQLException(e);
        }
    }

    // Select User by ID
    public Tenant selectTenant(int id) {
        Tenant tenant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");    
                int floor = rs.getInt("floor");
                int roomNumber = rs.getInt("roomNumber");
                String building = rs.getString("building");
                String doj = rs.getString("DOJ");
                int rent = rs.getInt("rent");
                boolean rentStatus = rs.getBoolean("rentStatus");
                boolean payVerification = rs.getBoolean("payVerification");
                tenant = new Tenant(id, name, floor, email, roomNumber, building, doj, rent, rentStatus, payVerification);
            }
        } catch(SQLException e) {
            printSQLException(e);
        }
        return tenant;
    }
    
    // Select User by Email ID
    public Tenant selectTenantByEmail(String email) {
        Tenant tenant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAILID)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int floor = rs.getInt("floor");
                int roomNumber = rs.getInt("roomNumber");
                String building = rs.getString("building");
                String doj = rs.getString("DOJ");
                int rent = rs.getInt("rent");
                String proof = rs.getString("proof");
                boolean rentStatus = rs.getBoolean("rentStatus");
                boolean payVerification = rs.getBoolean("payVerification");
                tenant = new Tenant(id, name, password, floor, email, roomNumber, building, doj, rent, rentStatus, payVerification, proof);
            }
        } catch(SQLException e) {
            printSQLException(e);
        }
        return tenant;
    }
    
    // Select All Tenants
    public List<Tenant> selectAllTenants(String building) {
        List<Tenant> tenants = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            preparedStatement.setString(1, building);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int floor = rs.getInt("floor");
                int roomNumber = rs.getInt("roomNumber");
                String doj = rs.getString("DOJ");
                int rent = rs.getInt("rent");
                boolean rentStatus = rs.getBoolean("rentStatus");
                boolean payVerification = rs.getBoolean("payVerification");
                String proof = rs.getString("proof");
                tenants.add(new Tenant(id, name, password, floor, email, roomNumber, building, doj, rent, rentStatus, payVerification, proof));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tenants;
    }
    
    // Select All Tenants with Proof
    public Tenant selectTenantWithProof(String building, int id) {
//        List<Tenant> tenants = new ArrayList<>();
    	Tenant tenant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_WITH_PROOF)) {
            preparedStatement.setString(1, building);
            preparedStatement.setInt(2, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int floor = rs.getInt("floor");
                int roomNumber = rs.getInt("roomNumber");
                String doj = rs.getString("DOJ");
                int rent = rs.getInt("rent");
                boolean rentStatus = rs.getBoolean("rentStatus");
                boolean payVerification = rs.getBoolean("payVerification");
                String proof = rs.getString("proof");
                tenant = new Tenant(id, name, password, floor, email, roomNumber, building, doj, rent, rentStatus, payVerification, proof);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return tenant;
    }
    
    // Calculate Expected Amount 
    public long expectedAmount(String building) {
        long expectedAmount = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUM_RENT)) {
            preparedStatement.setString(1, building);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                expectedAmount = rs.getLong("total_rent");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return expectedAmount;
    }
    
    // Calculate Rent Collected
    public long rentCollected(String building) {
        long rentCollected = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUM_RENT_COLLECTED)) {
            preparedStatement.setString(1, building);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rentCollected = rs.getLong("total_rent");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rentCollected;
    }
    
    // Calculate Pending Collection
    public long pendingCollection(String building) {
        long rentCollected = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUM_RENT_PENDING)) {
            preparedStatement.setString(1, building);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rentCollected = rs.getLong("total_rent");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rentCollected;
    }
    
    // Calculate Pending Collection
    public long pendingRentVerification(String building) {
        long rentCollected = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUM_RENT_VERIFICATION_PENDING)) {
            preparedStatement.setString(1, building);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rentCollected = rs.getLong("total_rent");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rentCollected;
    }
    
    // Owner verified the payment
    public boolean paymentVerified(int id) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PAYMENT_VERIFICATION)) {
            
            statement.setBoolean(1, true);
            statement.setBoolean(2, true);
            statement.setInt(3, id);
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    // Update Tenant Method
    public boolean updateTenant(Tenant tenant) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, tenant.getName());
            statement.setString(2, tenant.getEmail());
            statement.setInt(3, tenant.getFloor());
            statement.setInt(4, tenant.getRoomNumber());
            statement.setInt(5, tenant.getRent());
            statement.setBoolean(6, tenant.isRentStatus());
            statement.setBoolean(7, tenant.isPayVerification());
            statement.setInt(8, tenant.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    // Delete Tenant
    public boolean deleteTenant(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    private void printSQLException(SQLException ex) {
        for(Throwable e: ex) {
            if(e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = e.getCause();
                while(t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
