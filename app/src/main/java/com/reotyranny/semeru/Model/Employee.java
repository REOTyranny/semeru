package com.reotyranny.semeru.Model;

public class Employee extends User{
    private String location;
    public Employee(String name, String username, String password, String email, String location){
        super(name, username, password, email,1);
        this.location = location;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }

}
