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

    private Integer readInt(String prompt) {
        System.out.println(prompt);

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number.");
            return null;
        }
    }

    private void addRobotMenu() {
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

        Robot robot = robotManager.addRobot(serialNumber, model, "Available", year, warrantyExpDate, facilityId, name, battery, sensorSuite, primaryFunction);

        System.out.println("");
        System.out.println("Robot Added!");
        System.out.println("Robot ID: " + robot.getEquipmentId());
        
        pause();
    }

    private void editRobotMenu() {
        clearConsole();

        System.out.println("Edit Robot\n");

        int id = readInt("Please enter Robot ID to edit: ");

        Robot robot = robotManager.getRobotById(id);

        if (robot == null) {
            System.out.println("Robot not found.");
            pause();
            return;
        }

        while (true) {
            clearConsole();

            System.out.println("Edit Robot\n");
            System.out.println(robot);
            System.out.println("");

            System.out.println("1. Edit status");
            System.out.println("2. Edit Warranty Expiration Date");
            System.out.println("3. Edit Facility ID");
            System.out.println("4. Edit Robot Name");
            System.out.println("5. Edit Battery Autonomy");
            System.out.println("6. Edit Sensor Suite");
            System.out.println("7. Edit Primary Function");
            System.out.println("0. Back\n");

            int choice = readInt("Please enter your choice: ");

            switch (choice) {
                case 0:
                    return;
                
                case 1, 2, 3, 4, 5, 6, 7:
                    System.out.println("Enter new value: ");
                    String newValue = scanner.nextLine();

                    boolean updated = robotManager.editRobot(id, choice, newValue);

                    if (updated) {
                        System.out.println("Robot Updated.");
                    } else {
                        System.out.println("Error Updating Robot.");
                    }
                    break;
                default:
                    System.out.println("Invalid Choice. Try again.");
                    break;
                        
            }

            pause();
        }

    }


    private void deleteRobotMenu() {
        clearConsole();

        System.out.println("Delete Robot\n");

        int id = readInt("Please enter the robot to delete: ");

        boolean deleteResult = robotManager.deleteRobot(id);

        System.out.println("");
        if (deleteResult) {
            System.out.println("Robot deleted.");
        } else {
            System.out.println("Error deleting robot.");
        }

        pause();
    }


    private void searchRobotMenu() {
        clearConsole();

        System.out.println("Search Robots\n");

        int id = readInt("Please enter the Robot ID to find: ");

        Robot robot = robotManager.getRobotById(id);

        System.out.println("");
        if (robot != null) {
            System.out.println(robot);
        } else {
            System.out.println("Error finding robot.");
        }

        pause();
    }


    private void displayRobotsMenu() {
        clearConsole();

        System.out.println("All Robots\n");

        if (robotManager.getRobots().isEmpty()) {
            System.out.println("No robots found.");
        } else {
            for (Robot robot : robotManager.getRobots()) {
                System.out.println(robot + "\n");
            }
        }

        pause();
    }


    private void printMenu() {
        System.out.println("Robot Management\n");

        System.out.println("1. Add Robot");
        System.out.println("2. Edit Robot");
        System.out.println("3. Delete Robot");
        System.out.println("4. Search for Robot");
        System.out.println("5. Display all Robots");
        System.out.println("0. Back\n");
    }

    public void display() {

        while (true) {
            printMenu();

            int choice = readInt("Please enter your choice: ");

            switch (choice) {
                case 1:
                    addRobotMenu();
                    break;
                case 2:
                    editRobotMenu();
                    break;
                case 3:
                    deleteRobotMenu();
                    break;
                case 4:
                    searchRobotMenu();
                    break;
                case 5:
                    displayRobotsMenu();
                    break;
                case 0:
                    return;
            }
        }
    }
}
