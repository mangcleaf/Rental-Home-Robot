package EntityManager;

import java.util.ArrayList;

import Entity.Customer;


public class CustomerManager {
        private ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
}
