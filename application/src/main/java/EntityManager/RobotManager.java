package EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Entity.Robot;
import java.time.LocalDate;

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
        for (Robot robot: robots) {
            if (robot.getEquipmentId() == id) {
                return robot;
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
