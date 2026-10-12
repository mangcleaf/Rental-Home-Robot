package Entity;
import java.time.LocalDate;

public class Customer {
    private int userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private LocalDate startDate;
    private int facilityId;


    public Customer(int userId, String firstName, String lastName, String address, String phone, 
        String email, LocalDate startDate, int facilityId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.startDate = startDate;
            this.facilityId = facilityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fname) {
        this.firstName = fname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lname) {
        this.lastName = lname;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int id) {
        this.facilityId = id;
    }

    public String toString() {
        return "User ID: " + userId
             + "\nName: " + firstName + " " + lastName
             + "\nAddress: " + address
             + "\nPhone Number: " + phone
             + "\nEmail: " + email
             + "\nStart Date: " + startDate
             + "\nFacility ID: " + facilityId;
    }
    
}
