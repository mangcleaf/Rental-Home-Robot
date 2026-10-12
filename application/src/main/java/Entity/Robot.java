package Entity;
import java.time.LocalDate;

public class Robot extends Equipment {
    private String name;
    private int batteryAutonomy;
    private String sensorSuite;
    private String primaryFunction;

    public Robot(String serialNumber, String model, String status,
                int year, LocalDate warrantyExpDate, int facilityId, String name,
                int batteryAutonomy, String sensorSuite, String primaryFunction) {

        super(serialNumber, model, status, year, warrantyExpDate, facilityId);
        this.name = name;
        this.batteryAutonomy = batteryAutonomy;
        this.sensorSuite = sensorSuite;
        this.primaryFunction = primaryFunction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatteryAutonomy() {
        return batteryAutonomy;
    }

    public void setBatteryAutonomy(int batteryAutonomy) {
        this.batteryAutonomy = batteryAutonomy;
    }

    public String getSensorSuite() {
        return sensorSuite;
    }

    public void setSensorSuite(String sensorSuite) {
        this.sensorSuite = sensorSuite;
    }

    public String getPrimaryFunction() {
        return primaryFunction;
    }

    public void setPrimaryFunction(String primaryFunction) {
        this.primaryFunction = primaryFunction;
    }

    @Override
    public String toString() {
        return super.toString() 
        + "\nName: " + name
        + "\nBattery Autonomy: " + batteryAutonomy
        + "\nSensor Suite: " + sensorSuite
        + "\nPrimary Function: " + primaryFunction;
    }
}
