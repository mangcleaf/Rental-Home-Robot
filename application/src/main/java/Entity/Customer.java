package Entity;

public class Customer {
    private String Fname;
    private String Lname;
    private String address;
    private String phone;
    private String email;

    public Customer(String Fname, String Lname, String address, String phone, String email){
        this.Fname = Fname;
        this.Lname = Lname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String toString(){
        return "Name: " + Fname + " " + Lname + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email;
    }
}
