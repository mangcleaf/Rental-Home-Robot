package EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import Entity.Customer;
import java.sql.*;
import DataAccess.DatabaseConnection;

public class CustomerManager {
    private final ArrayList<Customer> customers = new ArrayList<>();


    public Customer addCustomer(String fName, String lName, String address, String phone, String email, LocalDate startDate, int facilityId, double facilityDistance) {
        

        String idSQL = "SELECT MAX(UserID) FROM Customer";
        String customerSQL = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // to add to the table CustomerFacilityDistance
        String customerFDSQL = "SELECT COUNT(*) FROM CustomerFacilityDistance WHERE Address = ? AND FacilityID = ?";
        String cfdInsertSQL = "INSERT INTO CustomerFacilityDistance VALUES (?, ?, ?)";

        int userId = 1;


        PreparedStatement idStmt = null;
        ResultSet rSet = null;

        PreparedStatement stmt = null;

        PreparedStatement cfdStmt = null;
        PreparedStatement cfdInsertStmt = null;

        try {
            idStmt = DatabaseConnection.conn.prepareStatement(idSQL);
            rSet = idStmt.executeQuery();

            if (rSet.next()) {
                // grabs the highest userID and increments it by 1
                userId = rSet.getInt(1) + 1;
            }

            cfdStmt = DatabaseConnection.conn.prepareStatement(customerFDSQL);
            cfdStmt.setString(1, address);
            cfdStmt.setInt(2, facilityId);
            rSet = cfdStmt.executeQuery();

            if (rSet.next()) {
                int cfdExists = rSet.getInt(1);

                if (cfdExists == 0) {
                    cfdInsertStmt = DatabaseConnection.conn.prepareStatement(cfdInsertSQL);
                    cfdInsertStmt.setString(1, address);
                    cfdInsertStmt.setInt(2, facilityId);
                    cfdInsertStmt.setDouble(3, facilityDistance);
                    cfdInsertStmt.executeUpdate();
                }
            }

            stmt = DatabaseConnection.conn.prepareStatement(customerSQL);
            stmt.setInt(1, userId);
            stmt.setString(2, fName);
            stmt.setString(3, lName);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.setString(6, email);
            stmt.setString(7, startDate.toString());
            stmt.setInt(8, facilityId);

            stmt.executeUpdate();

            Customer customer = new Customer(fName, lName, address, phone, email, startDate, facilityId);
            customer.setUserId(userId);

            return customer;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rSet != null) { rSet.close(); }
                if (idStmt != null) { idStmt.close(); }
                if (cfdStmt != null) { cfdStmt.close(); }
                if (cfdInsertStmt != null) { cfdInsertStmt.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public boolean editCustomer(int id, int choice, String newValue) {
        Customer customer = getCustomerById(id);
    
        if (customer == null) {
            return false;
        }
    
        String sql;
        switch (choice) {
            case 1:
                sql = "UPDATE Customer SET FirstName = ? WHERE UserID = ?";
                break;
            case 2:
                sql = "UPDATE Customer SET LastName = ? WHERE UserID = ?";
                break;
            case 3:
                sql = "UPDATE Customer SET Address = ? WHERE UserID = ?";
                break;
            case 4:
                sql = "UPDATE Customer SET Phone = ? WHERE UserID = ?";
                break;
            case 5:
                sql = "UPDATE Customer SET Email = ? WHERE UserID = ?";
                break;
            default:
                return false;
        }

        PreparedStatement stmt = null;

        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);
            stmt.setString(1, newValue);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    
        return false;
    }

    public boolean deleteCustomer(int id){
        Customer customer = getCustomerById(id);

        if (customer == null) {
            return false;
        }

        String sql = "DELETE FROM Customer WHERE UserID = ?";

        PreparedStatement stmt = null;

        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;

    }

    public Customer getCustomerById(int id){
        String sql = "SELECT * FROM Customer WHERE UserID = ?";

        PreparedStatement stmt = null;
        ResultSet rSet = null;

        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rSet = stmt.executeQuery();

            if (rSet.next()) {
                String first = rSet.getString("FirstName");
                String last = rSet.getString("LastName");
                String address = rSet.getString("Address");
                String phone = rSet.getString("Phone");
                String email = rSet.getString("Email");

                String start = rSet.getString("StartDate");

                LocalDate startDate = null;
                if (start != null) {
                    startDate = LocalDate.parse(start);
                }

                int facilityId = rSet.getInt("FacilityID");

                Customer customer = new Customer(first, last, address, phone, email, startDate, facilityId);

                customer.setUserId(rSet.getInt("UserID"));

                return customer;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rSet != null) { rSet.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;

    }

    public List<Customer> getCustomers() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM Customer";

        PreparedStatement stmt = null;
        ResultSet rSet = null;

        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);

            rSet = stmt.executeQuery();

            while (rSet.next()) {

                // building a customer object is the same as getCustomerById()
                String first = rSet.getString("FirstName");
                String last = rSet.getString("LastName");
                String address = rSet.getString("Address");
                String phone = rSet.getString("Phone");
                String email = rSet.getString("Email");

                String start = rSet.getString("StartDate");

                LocalDate startDate = null;
                if (start != null) {
                    startDate = LocalDate.parse(start);
                }

                int facilityId = rSet.getInt("FacilityID");

                Customer customer = new Customer(first, last, address, phone, email, startDate, facilityId);

                customer.setUserId(rSet.getInt("UserID"));

                customers.add(customer);
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rSet != null) { rSet.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return customers;

    }
    
}
