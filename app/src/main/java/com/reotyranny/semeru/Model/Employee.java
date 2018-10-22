package com.reotyranny.semeru.Model;
import java.util.LinkedList;
import java.util.List;

public class Employee extends Account{
    private Location location;

    public Employee(String name, String username, String password, String email, Location location){
        super(name, username, password, email,false);
        this.location = location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return location;
    }

    private void addStock(Donation stuffers){
        this.location.addDonation((stuffers));
    } // placeholder

    private void removeStock(Donation stuffers){
        this.location.removeDonation(stuffers);
    }// placeholder

    public List<Donation> getDonationList(){
        return location.getDonationHistory();
    }
}
