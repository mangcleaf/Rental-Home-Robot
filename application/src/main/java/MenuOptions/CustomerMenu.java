package MenuOptions;

import java.util.Scanner;

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
        System.out.println("Press [ENTER] to go back.");
        scanner.nextLine();
        clearConsole();
    }

    private void addCustomer(){
        clearConsole();

        System.out.println("Add Customer");
        System.out.println("");

        System.out.print("Please Enter Customer's First Name: ");
        String Fname = scanner.nextLine();
        
        System.out.print("Please Enter Customer's Last Name: ");
        String Lname = scanner.nextLine();

        System.out.print("Please Enter Customer's Address: ");
        String address = scanner.nextLine();

        System.out.print("Please Enter Customer's Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Please Enter Customer's Email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(Fname, Lname, address, phone, email);
        customerManager.addCustomer(customer);

        System.out.println("Customer Added!!!");
        pause();
    }


    private void printMenu(){
        clearConsole();

        System.out.println("Customer Managment");
        System.out.println("");

        System.out.println("1. Add Customer");
        System.out.println("2. Edit Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Display Customers");
        System.out.println("5. Display Customers");

        System.out.println("0. Exit");

        System.out.println("");
    }

    public void display() {
        int choice = -1;
        String response = null;

        while (true){
            printMenu();

            System.out.print("Please enter your choice: ");
            response = scanner.nextLine();

            try{
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Add Customer");
                    addCustomer();
                    break;
                case 2:
                    System.out.println("Edit Customer");
                    break;
                case 3:
                    System.out.println("Delete Customer");
                    break;
                case 0:
                    return;
            }
        }
    }
}
