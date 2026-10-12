package EntityManager;

import java.util.ArrayList;
import Entity.Customer;


public class CustomerManager {
        private ArrayList<Customer> customers = new ArrayList<>();
        private int nextId = 1;

    // Searches for one customer record based on their userId
    public Customer SearchForCustomer(int userId) {
        for (Customer customer : customers) {
            if (customer.getUserId() == userId) {
                return customer;
            }
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    // Adds a customer to the customer ArrayList
    public void addCustomer(Customer customer){
        customer.setUserId(nextId++);
        customers.add(customer);
    }

    // Removes a customer from the customer ArrayList
    public boolean deleteCustomer(int userId) {
        Customer customer = SearchForCustomer(userId);
        boolean foundCustomer = false;

        if (customer != null) {
            customers.remove(customer);
            foundCustomer = true;
        }

        return foundCustomer;
    }
    
}
