package EntityManager;

import java.util.ArrayList;
import java.util.List;
import Entity.Robot;
import java.time.LocalDate;
import java.sql.*;
import DataAccess.DatabaseConnection;
import java.time.format.DateTimeParseException;

public class RobotManager {

    public Robot addRobot(String serialNumber, String model, String status,
                      int year, LocalDate warranty, int facilityId,
                      String name, int batteryAutonomy,
                      String sensorSuite, String primaryFunction) {

        Connection conn = DatabaseConnection.conn;

        if (conn == null) {
            System.out.println("No database connection.");
            return null;
        }

        try {
            conn.setAutoCommit(false);

            int equipmentId = 1;

            String idSQL = "SELECT COALESCE(MAX(EquipmentID), 0) + 1 FROM Equipment";

            try (PreparedStatement stmt = conn.prepareStatement(idSQL);
                ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    equipmentId = rs.getInt(1);
                }
            }

            String equipmentSQL =
                "INSERT INTO Equipment " +
                "(EquipmentID, SerialNumber, Model, Status, Year, WarrantyExpirationDate, FacilityID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(equipmentSQL)) {
                stmt.setInt(1, equipmentId);
                stmt.setString(2, serialNumber);
                stmt.setString(3, model);
                stmt.setString(4, status);
                stmt.setInt(5, year);
                stmt.setString(6, warranty.toString());
                stmt.setInt(7, facilityId);

                stmt.executeUpdate();
            }

            String robotSQL =
                "INSERT INTO Robot " +
                "(EquipmentID, Name, BatteryAutonomy, SensorSuite, PrimaryFunction) " +
                "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(robotSQL)) {
                stmt.setInt(1, equipmentId);
                stmt.setString(2, name);
                stmt.setInt(3, batteryAutonomy);
                stmt.setString(4, sensorSuite);
                stmt.setString(5, primaryFunction);

                stmt.executeUpdate();
            }

            conn.commit();

            Robot robot = new Robot(
                serialNumber, model, status, year, warranty,
                facilityId, name, batteryAutonomy,
                sensorSuite, primaryFunction
            );

            robot.setEquipmentId(equipmentId);
            return robot;

        } catch (SQLException e) {
            System.out.println("Error adding robot: " + e.getMessage());

            try {
                conn.rollback();
            } catch (SQLException rollbackError) {
                System.out.println("Rollback failed: " + rollbackError.getMessage());
            }

            return null;

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error restoring auto-commit: " + e.getMessage());
            }
        }
    }

    public Robot getRobotById(int id) {
        String sql = "SELECT * FROM Equipment AS E" + 
                    " JOIN Robot AS R ON E.EquipmentID = R.EquipmentID" + 
                    " WHERE E.EquipmentID = ?";
        
        PreparedStatement stmt = null;
        ResultSet rSet = null;
        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rSet = stmt.executeQuery();

            // only one row could be found
            if (rSet.next()) {
                String serialNumber = rSet.getString("SerialNumber");
                String model = rSet.getString("Model");
                String status = rSet.getString("Status");
                int year = rSet.getInt("Year");

                String warranty = rSet.getString("WarrantyExpirationDate");
                LocalDate warrantyDate = null;
                if (warranty != null) {
                    warrantyDate = LocalDate.parse(warranty);
                }
                int facilityId = rSet.getInt("FacilityID");
                String name = rSet.getString("Name");
                int battery = rSet.getInt("BatteryAutonomy");
                String sensorSuite = rSet.getString("SensorSuite");
                String function = rSet.getString("PrimaryFunction");

                Robot robot = new Robot(serialNumber, model, status, year, warrantyDate, facilityId, name, battery, sensorSuite, function);

                robot.setEquipmentId(rSet.getInt("EquipmentID"));
                return robot;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rSet != null) { rSet.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public boolean editRobot(int id, int choice, String newValue) {
        Robot robot = getRobotById(id);

        if (robot == null) {
            return false;
        }
        
        String sql;
        switch (choice) {
            case 1:
                sql = "UPDATE Equipment SET Status = ? WHERE EquipmentID = ?";
                break;
            case 2:
                sql = "UPDATE Equipment SET WarrantyExpirationDate = ? WHERE EquipmentID = ?";
                break;
            case 3:
                sql = "UPDATE Equipment SET FacilityID = ? WHERE EquipmentID = ?";
                break;
            case 4:
                sql = "UPDATE Robot SET Name = ? WHERE EquipmentID = ?";
                break;
            case 5:
                sql = "UPDATE Robot SET BatteryAutonomy = ? WHERE EquipmentID = ?";
                break;
            case 6:
                sql = "UPDATE Robot SET SensorSuite = ? WHERE EquipmentID = ?";
                break;
            case 7:
                sql = "UPDATE Robot SET PrimaryFunction = ? WHERE EquipmentID = ?";
                break;
            default:
                return false;
        }

        PreparedStatement stmt = null;
        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);
            stmt.setInt(2, id);

            if (choice == 1 || choice == 4 || choice == 6 || choice == 7) {
                stmt.setString(1, newValue);
            } else if (choice == 3 || choice == 5) {
                stmt.setInt(1, Integer.parseInt(newValue));
            } else if (choice == 2) {
                stmt.setString(1, LocalDate.parse(newValue).toString());
            }
            stmt.executeUpdate();
            return true;

        } catch (SQLException | NumberFormatException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

    public boolean deleteRobot(int id) {
         Robot robot = getRobotById(id);

        if (robot == null) {
            return false;
        }

        String robotSQL = "DELETE FROM Robot WHERE EquipmentID = ?";
        String equipmentSQL = "DELETE FROM Equipment WHERE EquipmentID = ?";

        PreparedStatement robotStmt = null;
        PreparedStatement equipmentStmt = null;

        try {

            // Robot depends on Equipment, so it must be deleted first
            robotStmt = DatabaseConnection.conn.prepareStatement(robotSQL);
            robotStmt.setInt(1, id);
            robotStmt.executeUpdate();

            equipmentStmt = DatabaseConnection.conn.prepareStatement(equipmentSQL);
            equipmentStmt.setInt(1, id);
            equipmentStmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (robotStmt != null) { robotStmt.close(); }
                if (equipmentStmt != null) { equipmentStmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return false;
    }


    public List<Robot> getRobots() {

        List<Robot> robots = new ArrayList<>();
        
        String sql = "SELECT * FROM Equipment AS E" +
                    " JOIN Robot AS R ON E.EquipmentID = R.EquipmentID";

        PreparedStatement stmt = null;
        ResultSet rSet = null;
        
        try {
            stmt = DatabaseConnection.conn.prepareStatement(sql);
            rSet = stmt.executeQuery();

            while (rSet.next()) {
                // building a Robot object is the same as getRobotById()
                String serialNumber = rSet.getString("SerialNumber");
                String model = rSet.getString("Model");
                String status = rSet.getString("Status");
                int year = rSet.getInt("Year");

                String warranty = rSet.getString("WarrantyExpirationDate");
                LocalDate warrantyDate = null;
                if (warranty != null) {
                    warrantyDate = LocalDate.parse(warranty);
                }
                int facilityId = rSet.getInt("FacilityID");
                String name = rSet.getString("Name");
                int battery = rSet.getInt("BatteryAutonomy");
                String sensorSuite = rSet.getString("SensorSuite");
                String function = rSet.getString("PrimaryFunction");

                Robot robot = new Robot(serialNumber, model, status, year, warrantyDate, facilityId, name, battery, sensorSuite, function);

                robot.setEquipmentId(rSet.getInt("EquipmentID"));

                robots.add(robot);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rSet != null) { rSet.close(); }
                if (stmt != null) { stmt.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return robots;
        
    }


}
