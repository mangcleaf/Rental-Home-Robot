package MenuOptions;

import java.util.Scanner;
import java.time.LocalDate;
import Entity.Robot;
import EntityManager.RobotManager;


public class RobotMenu {
    private Scanner scanner;
    private RobotManager robotManager;

    public RobotMenu(Scanner scanner, RobotManager robotManager) {
        this.scanner = scanner;
        this.robotManager = robotManager;
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.println("Press [ENTER] to go back.");
        scanner.nextLine();
        clearConsole();
    }

    private void addRobot() {
        clearConsole();
        System.out.println("Add Robot");
        System.out.println("");

        System.out.print("Please Enter Serial Number: ");
        String serialNumber = scanner.nextLine();

        System.out.print("Please Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Please Enter the Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please Enter Warranty Expiration Date (YYYY-MM-DD): ");
        LocalDate warrantyExpDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Please Enter Facility ID: ");
        int facilityId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please Enter Robot Name: ");
        String name = scanner.nextLine();

        System.out.print("Please Enter Battery Autonomy: ");
        int battery = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please Enter Sensor Suite: ");
        String sensorSuite = scanner.nextLine();

        System.out.print("Please Enter Primary Function: ");
        String primaryFunction = scanner.nextLine();

        Robot robot = new Robot(serialNumber, model, "Available", year, warrantyExpDate, facilityId, name, battery, sensorSuite, primaryFunction);

        robotManager.addRobot(robot);

        System.out.println("Robot Added!");
        pause();
    }

    private void printMenu() {
        System.out.println("Robot Management");
        System.out.println("");

        System.out.println("1. Add Robot");
        System.out.println("2. Edit Robot");
        System.out.println("3. Delete Robot");
        System.out.println("4. Search for Robot");

        System.out.println("0. Exit");
        System.out.println("");
    }

    public void display() {
        int choice = -1;
        String response = null;

        while (true) {
            printMenu();

            System.out.print("Please enter your choice: ");
            response = scanner.nextLine();

            try {
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Add Robot");
                    addRobot();
                    break;
                case 2:
                    System.out.println("Edit Robot");
                    break;
                case 3:
                    System.out.println("Delete Robot");
                    break;
                case 4:
                    System.out.println("Search for Robot");
                    break;
                case 0:
                    return;
            }
        }
    }
}
