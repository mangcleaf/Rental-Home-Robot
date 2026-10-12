package EntityManager;

import java.util.ArrayList;
import Entity.Customer;


public class CustomerManager {
        private ArrayList<Customer> customers = new ArrayList<>();
        private int nextId = 1;

    public void addCustomer(Customer customer){
        customer.setUserId(nextId++);
        customers.add(customer);
    }
}
