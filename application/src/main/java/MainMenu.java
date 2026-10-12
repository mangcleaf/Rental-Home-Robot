import java.util.Scanner;

public class MainMenu {
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getResponse(Scanner in, int validChoice) {
        int return_value;
        String response = null;

        while(true){
            response = in.nextLine();

            try {
                return_value = Integer.parseInt(response);

                if (return_value < 0 || return_value > validChoice) {
                    System.out.print("Please enter a valid choice: ");
                } else {
                    break;
                }

            } catch (NumberFormatException e){
                System.out.print("Please enter a valid choice: ");
            }
        }

        return return_value;
    }

    public static void printMenuOptions() {
        clearConsole();

        System.out.println("Rental Home Robot System");
        System.out.println("");

        System.out.println("1. Manage Customer");
        System.out.println("2. Manage Robot");
        System.out.println("3. Manage Driverless Car");
        System.out.println("4. Manage Rental");
        System.out.println("0. Exit");

        System.out.println("");
        System.out.print("Please enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        printMenuOptions();
        getResponse(in, 4);


        System.out.println("");

        in.close();
    }
}
