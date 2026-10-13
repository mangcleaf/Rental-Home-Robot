package EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import Entity.Customer;


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
        for(Customer customer: customers){
            if (customer.getUserId() == id){
                return customer;
            }
        }

        return null;
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }
    
}
