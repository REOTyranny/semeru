package com.reotyranny.semeru.Model;
import java.util.LinkedList;
import java.util.List;

public class Employee extends Account{
    private Location location;

    public Employee(String name, String username, String password, String email, Location location){
        super(name, username, password, email,1,false);
        this.location = location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return location;
    }

    public void addStock(){} // placeholder
    public void removeStock(){}// placeholder

    public List<Donation> getDonationList(){
        return location.getDonationHistory();
    }


}
