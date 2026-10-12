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

    private Integer readInt(String prompt){
        System.out.println(prompt);

        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalid Number!!!");
            return null;
        }
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
        while (true){
            printMenu();

            int choice = readInt("Please enter your choice: ");

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
