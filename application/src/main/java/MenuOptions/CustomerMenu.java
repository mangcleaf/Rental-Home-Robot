package MenuOptions;

import java.util.Scanner;
import java.time.LocalDate;
import Entity.Customer;
import EntityManager.CustomerManager;

public class CustomerMenu {
    private Scanner scanner;
    private CustomerManager customerManager;

    public CustomerMenu(Scanner scanner, CustomerManager customerManager){
        this.scanner = scanner;
        this.customerManager = customerManager;
    }

    private void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause(){
        System.out.println("");
        System.out.println("Press [ENTER] to go back.");
        scanner.nextLine();
        clearConsole();
    }

    private Integer readInt(String prompt){
        System.out.print(prompt);

        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalid Number!!!");
            return null;
        }
    }

    private void addCustomerMenu(){
        clearConsole();

        System.out.println("Add Customer");
        System.out.println("");

        System.out.print("Please Enter Customer's First Name: ");
        String fName = scanner.nextLine();
        
        System.out.print("Please Enter Customer's Last Name: ");
        String lName = scanner.nextLine();

        System.out.print("Please Enter Customer's Address: ");
        String address = scanner.nextLine();

        System.out.print("Please Enter Customer's Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Please Enter Customer's Email: ");
        String email = scanner.nextLine();

        System.out.print("Please Enter Customer's Facility ID: ");
        int facilityId = scanner.nextInt();
        scanner.nextLine();

        LocalDate startDate = LocalDate.now();

        Customer customer = customerManager.addCustomer(fName, lName, address, phone, email, startDate, facilityId);

        System.out.println("");
        System.out.println("Customer Added!!!");
        System.out.println("User ID: " + customer.getUserId());

        pause();
    }

    private void editCustomerMenu(){
        clearConsole();

        System.out.println("Edit Customer");
        System.out.println("");

        Integer id = readInt("Please enter User ID to edit: ");

        Customer customer = customerManager.getCustomerById(id);

        if (customer == null){
            System.out.println("Customer Not Found!!!");
            pause();
            return;
        }

        while (true) {
            clearConsole();

            System.out.println("Edit Customer");
            System.out.println("");
            System.out.println(customer);
            System.out.println("");

            System.out.println("1. Edit First Name");
            System.out.println("2. Edit Last Name");
            System.out.println("3. Edit Address");
            System.out.println("4. Edit Phone");
            System.out.println("5. Edit Email");
            System.out.println("0. Back");
            System.out.println("");

            Integer choice = readInt("Please enter your choice: ");

            switch (choice) {
                case 0:
                    return;
            
                case 1, 2, 3, 4, 5:
                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();
            
                    boolean updated = customerManager.editCustomer(
                        id, choice, newValue);
            
                    if (updated) {
                        System.out.println("Customer Updated!!!");
                    } else {
                        System.out.println("Error Updating Customer!!!");
                    }
                    break;
            
                default:
                    System.out.println("Invalid Choice!!!");
                    break;
            }
            
            pause();
        }
    }

    private void deleteCustomerMenu(){
        clearConsole();

        System.out.println("Delete Customer");
        System.out.println("");

        Integer id = readInt("Please enter Customer to delete: ");

        boolean deleteResult = customerManager.deleteCustomer(id);

        System.out.println("");
        if(deleteResult){
            System.out.println("Customer Deleted!!!");
        } else {
            System.out.println("Error Deleting Customer!!!");
        }

        pause();
    } 

    private void searchCustomerMenu(){
        clearConsole();

        System.out.println("Search Customers");
        System.out.println("");

        Integer id = readInt("Please enter the User ID to find: ");

        Customer customer = customerManager.getCustomerById(id);

        System.out.println("");
        if(customer != null){
            System.out.println(customer);
        } else {
            System.out.println("Error Finding Customer!!!");
        }

        pause();
    } 

    private void displayCustomersMenu(){
        clearConsole();

        System.out.println("All Customers");
        System.out.println("");

        if (customerManager.getCustomers().isEmpty()){
            System.out.println(("No Customers Found!!!"));
        } else{
            for (Customer customer : customerManager.getCustomers()) {
                System.out.println(customer);
                System.out.println();
            }
        }

        pause();
    }

    private void printMenu(){
        clearConsole();

        System.out.println("Customer Management");
        System.out.println("");

        System.out.println("1. Add Customer");
        System.out.println("2. Edit Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Search for Customer");
        System.out.println("5. Display Customers");
        System.out.println("0. Back");

        System.out.println("");
    }

    public void display() {
        while (true){
            printMenu();
            
            Integer choice = readInt("Please enter your choice: ");

            switch (choice) {
                case 1:
                    addCustomerMenu();
                    break;
                case 2:
                    editCustomerMenu();
                    break;
                case 3:
                    deleteCustomerMenu();
                    break;
                case 4:
                    searchCustomerMenu();
                    break;
                case 5:
                    displayCustomersMenu();
                    break;
                case 0:
                    return;
            }
        }
    }
}
