import java.util.Scanner;

import EntityManager.CustomerManager;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
                
        CustomerManager customerManager = new CustomerManager();


        MainMenu menu = new MainMenu(scanner, customerManager);
        menu.display();

        scanner.close();
    }
}
