package EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Entity.Robot;
import java.time.LocalDate;
import java.sql.*;
import DataAccess.DatabaseConnection;

public class RobotManager {
    private final ArrayList<Robot> robots = new ArrayList<>();
    private int nextId = 1;

    public Robot addRobot(String serialNumber, String model, String status, int year, LocalDate warranty, 
        int facilityId, String name, int batteryAutonomy, String sensorSuite, String primaryFunction) {

            Robot robot = new Robot(serialNumber, model, status, year, warranty, facilityId, name, batteryAutonomy, sensorSuite, primaryFunction);

            robot.setEquipmentId(nextId++);
            robots.add(robot);

            return robot;
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
