import java.util.Scanner;
import EntityManager.CustomerManager;
import EntityManager.RobotManager;
import DataAccess.DatabaseConnection;

public class App 
{
    public static void main( String[] args )
    {
        DatabaseConnection.conn = DatabaseConnection.initializeDB();
        Scanner scanner = new Scanner(System.in);
                
        CustomerManager customerManager = new CustomerManager();
        RobotManager robotManager = new RobotManager();


        MainMenu menu = new MainMenu(scanner, customerManager, robotManager);
        menu.display();

        scanner.close();

        DatabaseConnection.closeDatabase();
    }
}
