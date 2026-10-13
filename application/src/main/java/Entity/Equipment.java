package Entity;
import java.time.LocalDate;

public abstract class Equipment {
    private int equipmentId;
    private String serialNumber;
    private String model;
    private String status;
    private int year;
    private LocalDate warrantyExpDate;
    private int facilityId;

    public Equipment(String serialNumber, String model, String status, int year, LocalDate warrantyExpDate, int facilityId) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.status = status;
        this.year = year;
        this.warrantyExpDate = warrantyExpDate;
        this.facilityId = facilityId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    } 

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getWarrantyExpDate() {
        return warrantyExpDate;
    }

    public void setWarrantyExpDate(LocalDate date) {
        this.warrantyExpDate = date;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String toString() {
        return "ID: " + equipmentId
        + "\nSerial Number: " + serialNumber
        + "\nModel: " + model
        + "\nStatus: " + status
        + "\nYear: " + year
        + "\nWarranty Expiration Date: " + warrantyExpDate
        + "\nFacility ID: " + facilityId;
    }
}
