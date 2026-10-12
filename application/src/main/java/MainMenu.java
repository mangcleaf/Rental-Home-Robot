import java.util.Scanner;

import EntityManager.CustomerManager;
import MenuOptions.CustomerMenu;

public class MainMenu {
    private Scanner scanner;
    private CustomerManager customerManager;


    public MainMenu(Scanner scanner, CustomerManager customerManager){
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

    private void printMenu(){
        clearConsole();

        System.out.println("Rental Home Robot System");
        System.out.println("");

        System.out.println("1. Manage Customer");
        System.out.println("2. Manage Robot");
        System.out.println("3. Manage Driverless Car");
        System.out.println("4. Manage Rental");
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
                    CustomerMenu customerMenu = new CustomerMenu(scanner, customerManager);
                    customerMenu.display();
                    break;
                case 2:
                    System.out.println("");
                    System.out.println("Robot Menu Underdevelopment");
                    pause();
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Driverless Car Menu Underdevelopment");
                    pause();
                    break;
                case 4:
                    System.out.println("");
                    System.out.println("Rental Underdevelopment");
                    pause();
                    break;
                case 0:
                    System.out.println("");
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
