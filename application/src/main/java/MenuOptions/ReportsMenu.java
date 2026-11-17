
package MenuOptions;

import DataAccess.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

public class ReportsMenu {

    private final Scanner scanner;

    public ReportsMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // Execute a report and print its results.
    private void runReport(String sql, Integer parameter) {

        Connection conn = DatabaseConnection.conn;

        if (conn == null) {
            System.out.println("No database connection.");
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (parameter != null) {
                stmt.setInt(1, parameter);
            }

            try (ResultSet rs = stmt.executeQuery()) {

                ResultSetMetaData metadata = rs.getMetaData();
                int columns = metadata.getColumnCount();

                // Print column headings
                for (int i = 1; i <= columns; i++) {
                    System.out.print(metadata.getColumnLabel(i));
                    System.out.print(i == columns ? "\n" : " | ");
                }

                int rows = 0;

                // Print records
                while (rs.next()) {
                    for (int i = 1; i <= columns; i++) {
                        System.out.print(rs.getString(i));
                        System.out.print(i == columns ? "\n" : " | ");
                    }
                    rows++;
                }

                if (rows == 0) {
                    System.out.println("No matching records found.");
                }

                System.out.println("Rows returned: " + rows);
            }

        } catch (SQLException e) {
            System.out.println("Report failed: " + e.getMessage());
        }
    }

    // Report 1: Total rentals for a specified customer
    private void rentingCheckouts() {

        int customerID = readInt("Enter Customer ID: ");

        String sql =
            "SELECT C.UserID, C.FirstName, C.LastName, " +
            "COUNT(R.RentalID) AS TotalRentals " +
            "FROM Customer C " +
            "LEFT JOIN Rentals R ON C.UserID = R.CustomerID " +
            "WHERE C.UserID = ? " +
            "GROUP BY C.UserID, C.FirstName, C.LastName";

        runReport(sql, customerID);
    }

    // Report 2: Robots ranked by rental duration and count
    private void popularRobot() {

        String sql =
            "SELECT B.EquipmentID, B.Name, " +
            "COUNT(R.RentalID) AS TotalRentals, " +
            "COALESCE(SUM(CASE " +
            "WHEN R.ReturnDate IS NOT NULL " +
            "THEN MAX(0, julianday(R.ReturnDate) " +
            "- julianday(R.CheckoutDate)) " +
            "ELSE 0 END), 0) AS TotalDays " +
            "FROM Robot B " +
            "LEFT JOIN Rentals R " +
            "ON R.RobotEquipmentID = B.EquipmentID " +
            "GROUP BY B.EquipmentID, B.Name " +
            "ORDER BY TotalDays DESC, TotalRentals DESC";

        runReport(sql, null);
    }

    // Report 3: Manufacturer with most rented units
    private void popularManufacturer() {

        String sql =
            "SELECT EM.Manufacturer, " +
            "COUNT(DISTINCT E.EquipmentID) AS RentedUnits, " +
            "COUNT(R.RentalID) AS TotalRentals " +
            "FROM Rentals R " +
            "JOIN Equipment E " +
            "ON R.RobotEquipmentID = E.EquipmentID " +
            "JOIN EquipmentModel EM ON E.Model = EM.Model " +
            "GROUP BY EM.Manufacturer " +
            "ORDER BY RentedUnits DESC, TotalRentals DESC " +
            "LIMIT 1";

        runReport(sql, null);
    }

    // Report 4: Driverless cars ranked by deliveries and distance
    private void popularDriverlessCar() {

        String sql =
            "SELECT D.EquipmentID AS CarID, D.LicensePlate, " +
            "COUNT(DP.DeliveryPickupID) AS TotalDeliveries, " +
            "COALESCE(SUM(CFD.FacilityDistance), 0) AS TotalMiles " +
            "FROM DriverlessCar D " +
            "LEFT JOIN DeliveryPickup DP " +
            "ON D.EquipmentID = DP.CarEquipmentID " +
            "AND DP.Type = 'Delivery' " +
            "LEFT JOIN Rentals R ON DP.RentalID = R.RentalID " +
            "LEFT JOIN Customer C ON R.CustomerID = C.UserID " +
            "LEFT JOIN CustomerFacilityDistance CFD " +
            "ON C.Address = CFD.Address " +
            "AND C.FacilityID = CFD.FacilityID " +
            "GROUP BY D.EquipmentID, D.LicensePlate " +
            "ORDER BY TotalMiles DESC, TotalDeliveries DESC";

        runReport(sql, null);
    }

    // Report 5: Customer(s) with most robot rentals
    private void robotsCheckedOut() {

        String sql =
            "SELECT C.UserID, C.FirstName, C.LastName, " +
            "COUNT(R.RentalID) AS TotalRentals " +
            "FROM Customer C " +
            "JOIN Rentals R ON C.UserID = R.CustomerID " +
            "GROUP BY C.UserID, C.FirstName, C.LastName " +
            "HAVING COUNT(R.RentalID) = (" +
            "SELECT MAX(cnt) FROM (" +
            "SELECT COUNT(*) AS cnt " +
            "FROM Rentals GROUP BY CustomerID))";

        runReport(sql, null);
    }

    // Report 6: Robots released before a specified year
    private void robotsByManufacturer() {

        int year = readInt("Released before year: ");

        String sql =
            "SELECT DISTINCT EM.Manufacturer, " +
            "R.Name AS RobotName, E.Model, E.Year " +
            "FROM Robot R " +
            "JOIN Equipment E ON R.EquipmentID = E.EquipmentID " +
            "JOIN EquipmentModel EM ON E.Model = EM.Model " +
            "WHERE E.Year < ? " +
            "ORDER BY EM.Manufacturer, R.Name";

        runReport(sql, year);
    }

    public void display() {

        while (true) {

            System.out.println("\nUseful Reports");
            System.out.println("1. Renting Checkouts");
            System.out.println("2. Popular Robot");
            System.out.println("3. Popular Manufacturer");
            System.out.println("4. Popular Driverless Car");
            System.out.println("5. Robots Checked Out");
            System.out.println("6. Robots by Manufacturer");
            System.out.println("0. Back");

            int choice = readInt("Please enter your choice: ");

            if (choice == 0) {
                return;
            }

            switch (choice) {
                case 1:
                    rentingCheckouts();
                    break;
                case 2:
                    popularRobot();
                    break;
                case 3:
                    popularManufacturer();
                    break;
                case 4:
                    popularDriverlessCar();
                    break;
                case 5:
                    robotsCheckedOut();
                    break;
                case 6:
                    robotsByManufacturer();
                    break;
                default:
                    System.out.println("Invalid selection.");
                    continue;
            }

            System.out.println("\nPress ENTER to continue...");
            scanner.nextLine();
        }
    }
}
