package EntityManager;

import java.util.ArrayList;
import Entity.Robot;

public class RobotManager {
    private ArrayList<Robot> robots = new ArrayList<>();
    private int nextId = 1;

    public void addRobot(Robot robot) {
        robot.setEquipmentId(nextId++);
        robots.add(robot);
    }
}
