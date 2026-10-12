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
        System.out.print(prompt);

        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalid Number!!!");
            return null;
        }
    }

    private void schedulePickup(){
        clearConsole();

        System.out.println("Schedule Robot Pickup");
        System.out.println("");

        int ID = readInt("Please enter Customer ID: ");

        System.out.println("");
        System.out.println("Active Rentals: ");
        System.out.println("...");
        System.out.println("");

        int selectedRental = readInt("Please enter Rental ID: ");

        System.out.println("");
        System.out.println("Available Driverless Cars: ");
        System.out.println("...");
        System.out.println("");

        int selectedCar = readInt("Please enter Car Equipment ID: ");

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
            
            int choice = readInt("Please enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("Rent Robot Under Development");
                    pause();
                    break;
                case 2:
                    System.out.println("Return Equipment Under Development");
                    pause();
                    break;
                case 3:
                    System.out.println("Schedule Delivery Under Development");
                    pause();
                    break;
                case 4:
                    schedulePickup();
                    break;
                default:
                    break;
            }
        }
    }
}
