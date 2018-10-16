package com.reotyranny.semeru.Model;

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

}
