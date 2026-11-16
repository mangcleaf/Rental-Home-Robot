package EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Entity.Robot;
import Entity.Equipment;
import java.time.LocalDate;
import java.sql.*;
import DataAccess.DatabaseConnection;

public class RobotManager {
    private final ArrayList<Robot> robots = new ArrayList<>();
    private int nextId = 1;

    public Robot addRobot(String serialNumber, String model, String status, int year, LocalDate warranty, 
        int facilityId, String name, int batteryAutonomy, String sensorSuite, String primaryFunction) {

            String idSQL = "SELECT MAX(EquipmentID) FROM Equipment";
            int equipmentId = 1;

            PreparedStatement idStmt = null;
            PreparedStatement equipmentStmt = null;
            PreparedStatement robotStmt = null;
            ResultSet rSet = null;

            try {
                idStmt = DatabaseConnection.conn.prepareStatement(idSQL);
                rSet = idStmt.executeQuery();
                if (rSet.next()) {
                    // grabs the highest id and increments by 1
                    equipmentId = rSet.getInt(1) + 1;
                }

                String equipmentSQL = "INSERT INTO Equipment VALUES (?, ?, ?, ?, ?, ?, ?)";
                equipmentStmt = DatabaseConnection.conn.prepareStatement(equipmentSQL);
                equipmentStmt.setInt(1, equipmentId);
                equipmentStmt.setString(2, serialNumber);
                equipmentStmt.setString(3, model);
                equipmentStmt.setString(4, status);
                equipmentStmt.setInt(5, year);
                equipmentStmt.setString(6, warranty.toString());
                equipmentStmt.setInt(7, facilityId);

                equipmentStmt.executeUpdate();

                String robotSQL = "INSERT INTO Robot VALUES (?, ?, ?, ?, ?)";
                robotStmt = DatabaseConnection.conn.prepareStatement(robotSQL);
                robotStmt.setInt(1, equipmentId);
                robotStmt.setString(2, name);
                robotStmt.setInt(3, batteryAutonomy);
                robotStmt.setString(4, sensorSuite);
                robotStmt.setString(5, primaryFunction);

                robotStmt.executeUpdate();

                Robot robot = new Robot(serialNumber, model, status, year, warranty, facilityId, name, batteryAutonomy, sensorSuite, primaryFunction);
                robot.setEquipmentId(equipmentId);
                return robot;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (rSet != null) { rSet.close(); }
                    if (idStmt != null) { idStmt.close(); }
                    if (equipmentStmt != null) { equipmentStmt.close(); }
                    if (robotStmt != null) { robotStmt.close(); }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            return null;
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
        
        switch (choice) {
            case 1:
                robot.setStatus(newValue);
                break;
            case 2:
                robot.setWarrantyExpDate(LocalDate.parse(newValue));
                break;
            case 3:
                robot.setFacilityId(Integer.parseInt(newValue));
                break;
            case 4:
                robot.setName(newValue);
                break;
            case 5:
                robot.setBatteryAutonomy(Integer.parseInt(newValue));
                break;
            case 6:
                robot.setSensorSuite(newValue);
                break;
            case 7:
                robot.setPrimaryFunction(newValue);
                break;
            default:
                return false;
        }

        return true;
    }

    public boolean deleteRobot(int id) {
        return robots.removeIf(robot -> robot.getEquipmentId() == id);
    }

    public List<Robot> getRobots() {
        return Collections.unmodifiableList(robots);
    }


}
