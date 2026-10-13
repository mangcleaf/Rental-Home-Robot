import java.util.Scanner;

import EntityManager.CustomerManager;
import EntityManager.RobotManager;
import MenuOptions.CustomerMenu;
import MenuOptions.RentalMenu;
import MenuOptions.RobotMenu;

public class MainMenu {
    private Scanner scanner;
    private CustomerManager customerManager;
    private RobotManager robotManager;


    public MainMenu(Scanner scanner, CustomerManager customerManager, RobotManager robotManager){
        this.scanner = scanner;
        this.customerManager = customerManager;
        this.robotManager = robotManager;
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

    private void printMenu(){
        clearConsole();

        System.out.println("Rental Home Robot System");
        System.out.println("");

        System.out.println("1. Manage Customer");
        System.out.println("2. Manage Robot");
        System.out.println("3. Manage Driverless Car");
        System.out.println("4. Manage Rental");
        System.out.println("5. Useful Reports");
        System.out.println("0. Exit");

        System.out.println("");
    }

    public void display() {
        while (true){
            printMenu();

            Integer choice = readInt("Please enter your choice: ");

            switch (choice) {
                case 1:
                    CustomerMenu customerMenu = new CustomerMenu(scanner, customerManager);
                    customerMenu.display();
                    break;
                case 2:
                    RobotMenu robotMenu = new RobotMenu(scanner, robotManager);
                    robotMenu.display();
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Driverless Car Menu Underdevelopment");
                    pause();
                    break;
                case 4:
                    RentalMenu rentalMenu = new RentalMenu(scanner);
                    rentalMenu.display();
                    break;
                case 5:
                    System.out.println("** This section will be implemented later **");
                    break;
                case 0:
                    System.out.println("");
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
