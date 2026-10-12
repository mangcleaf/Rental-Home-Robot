package Entity;

public class Customer {
    private final int customerId;
    private String Fname;
    private String Lname;
    private String address;
    private String phone;
    private String email;

    public Customer(int customerId, String Fname, String Lname, String address, String phone, String email){
        this.customerId = customerId;
        this.Fname = Fname;
        this.Lname = Lname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getCustomerID(){
        return customerId;
    }

    public void setFname(String Fname){
        this.Fname = Fname;
    }

    public String getFname(){
        return Fname;
    }


    public void setLname(String Lname){
        this.Lname = Lname;
    }

    public String getLname(){
        return Lname;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    @Override 
    public String toString(){
        return "CustomerId: " + customerId + 
            "\nName: " + Fname + " " + Lname + 
            "\nAddress: " + address + 
            "\nPhone: " + phone + 
            "\nEmail: " + email;
    }
}
