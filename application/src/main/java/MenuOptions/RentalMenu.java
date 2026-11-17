package MenuOptions;

import java.util.Scanner;

public class RentalMenu {
    private Scanner scanner;

    public RentalMenu(Scanner scanner){
        this.scanner = scanner;
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
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Number!!! Try again");
            }
        }
    }


    private void rentRobot() {
        clearConsole();

        System.out.println("Rent Robot");
        System.out.println("");

        Integer customerID = readInt("Please enter Customer ID: ");
        Integer robotID = readInt("Please enter Robot Equipment ID: ");

        System.out.print("Please enter Checkout Date (YYYY-MM-DD): ");
        String checkoutDate = scanner.nextLine();

        System.out.print("Please enter Due Date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        System.out.println("");
        System.out.println("Rental information entered successfully!");
        System.out.println("Customer ID: " + customerID);
        System.out.println("Robot ID: " + robotID);
        System.out.println("Checkout Date: " + checkoutDate);
        System.out.println("Due Date: " + dueDate);
    }

    private void returnEquipment() {
        clearConsole();

        System.out.println("Return Equipment");
        System.out.println("");

        Integer customerID = readInt("Please enter Customer ID: ");
        Integer rentalID = readInt("Please enter Rental ID: ");

        System.out.print("Please enter Return Date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        System.out.println("");
        System.out.println("Return information entered successfully!");
        System.out.println("Customer ID: " + customerID);
        System.out.println("Rental ID: " + rentalID);
        System.out.println("Return Date: " + returnDate);
    }

    private void scheduleDelivery() {
        clearConsole();

        System.out.println("Schedule Robot Delivery");
        System.out.println("");

        Integer customerID = readInt("Please enter Customer ID: ");
        Integer rentalID = readInt("Please enter Rental ID: ");
        Integer carID = readInt("Please enter Driverless Car Equipment ID: ");

        System.out.print("Please enter Delivery Date (YYYY-MM-DD): ");
        String deliveryDate = scanner.nextLine();

        System.out.print("Please enter Delivery Time (HH:MM): ");
        String deliveryTime = scanner.nextLine();

        System.out.println("");
        System.out.println("Delivery information entered successfully!");
        System.out.println("Customer ID: " + customerID);
        System.out.println("Rental ID: " + rentalID);
        System.out.println("Car Equipment ID: " + carID);
        System.out.println("Delivery Date: " + deliveryDate);
        System.out.println("Delivery Time: " + deliveryTime);
    }


    private void schedulePickup(){
        clearConsole();

        System.out.println("Schedule Robot Pickup");
        System.out.println("");

        Integer id = readInt("Please enter Customer ID: ");

        System.out.println("");
        System.out.println("Active Rentals: ");
        System.out.println("...");
        System.out.println("");

        Integer selectedRental = readInt("Please enter Rental ID: ");

        System.out.println("");
        System.out.println("Available Driverless Cars: ");
        System.out.println("...");
        System.out.println("");

        Integer selectedCar = readInt("Please enter Car Equipment ID: ");

        System.out.println("");
        System.out.print("Please enter Pickup Date: ");
        String pickupDate = scanner.nextLine();
        System.out.print("Please enter Pickup Time: ");
        String pickupTime = scanner.nextLine();
        System.out.println("");

        System.out.println("Pickup Scheduled Successfully!!!");
    }

    private void printMenu(){
        clearConsole();

        System.out.println("Rental Menu");
        System.out.println("");

        System.out.println("1. Rent Robot");
        System.out.println("2. Return Equipment");
        System.out.println("3. Schedule Robot Delivery");
        System.out.println("4. Schedule Robot Pickup");
        System.out.println("0. Back");

        System.out.println("");
    }

    public void display(){
        while (true) {
            printMenu();
            
            Integer choice = readInt("Please enter your choice: ");

            
            switch (choice) {
                case 1:
                    rentRobot();
                    pause();
                    break;

                case 2:
                    returnEquipment();
                    pause();
                    break;

                case 3:
                    scheduleDelivery();
                    pause();
                    break;

                case 4:
                    schedulePickup();
                    pause();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid selection.");
                    pause();
                    break;
            }

        }
    }
}
