package EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entity.Customer;


public class CustomerManager {
        private final ArrayList<Customer> customers = new ArrayList<>();
        private int nextID = 1;

    public Customer addCustomer(String Fname, String Lname, String address, String phone, String email){

        Customer customer = new Customer(nextID, Fname, Lname, address, phone, email);

        customers.add(customer);
        nextID++;

        return customer;
    }

    public boolean editCustomer(int ID, int choice, String newValue) {
        Customer customer = getCustomerByID(ID);
    
        if (customer == null) {
            return false;
        }
    
        switch (choice) {
            case 1:
                customer.setFname(newValue);
                break;
            case 2:
                customer.setLname(newValue);
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

    public boolean deleteCustomer(int ID){
        return customers.removeIf(customer -> customer.getCustomerID() == ID);
    }

    public Customer getCustomerByID(int ID){
        for(Customer customer: customers){
            if (customer.getCustomerID() == ID){
                return customer;
            }
        }

        return null;
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }
}
