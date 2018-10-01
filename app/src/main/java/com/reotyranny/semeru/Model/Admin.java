package com.reotyranny.semeru.Model;

public class Admin extends Account{
    public Admin(String name, String username, String password, String email){
        super(name, username, password, email,1,false);
    }
    public void lock(User user){
        user.locked = true;
    }
    public void unlock(User user){
        user.locked = false;
    }
    public void removeUser(User user){
        user = null;
    }
    public void addUser(User user){ } // placeholder
}
