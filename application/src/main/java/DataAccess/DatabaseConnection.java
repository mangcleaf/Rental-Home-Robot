package DataAccess;
import java.sql.*;

/* Very similar implementation used from GRS.java in Lab 3 */
public class DatabaseConnection {
    private static final String DB_URL =
    "jdbc:sqlite:database/Rental-Home-Robot-CH4.db";
    public static Connection conn = null;

    
    public static Connection initializeDB() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL);
            try (Statement stmt = conn.createStatement()){
                stmt.execute("PRAGMA foreign_keys = ON");
            }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }


    public static void closeDatabase() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
