import java.util.Scanner;

import EntityManager.CustomerManager;
import EntityManager.RobotManager;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
                
        CustomerManager customerManager = new CustomerManager();
        RobotManager robotManager = new RobotManager();


        MainMenu menu = new MainMenu(scanner, customerManager);
        menu.display();

        scanner.close();
    }
}
