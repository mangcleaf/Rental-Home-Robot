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
        private int nextId = 1;

    public Customer addCustomer(String fName, String lName, String address, String phone, String email, LocalDate startDate, int facilityId){

        Customer customer = new Customer(fName, lName, address, phone, email, startDate, facilityId);

        customer.setUserId(nextId++);
        customers.add(customer);

        return customer;
    }

    public boolean editCustomer(int id, int choice, String newValue) {
        Customer customer = getCustomerById(id);
    
        if (customer == null) {
            return false;
        }
    
        switch (choice) {
            case 1:
                customer.setFirstName(newValue);
                break;
            case 2:
                customer.setLastName(newValue);
                break;
            case 3:
                customer.setAddress(newValue);
                break;
            case 4:
                customer.setPhone(newValue);
                break;
            case 5:
                customer.setEmail(newValue);
                break;
            default:
                return false;
        }
    
        return true;
    }

    public boolean deleteCustomer(int id){
        return customers.removeIf(customer -> customer.getUserId() == id);
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
