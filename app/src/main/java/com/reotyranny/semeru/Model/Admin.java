package com.reotyranny.semeru.Model;

public class Admin extends User{
    public Admin(String name, String username, String password, String email){
        super(name, username, password, email,1);
    }
    public void lock(User user){
        user.role = 0;
    }
    public void unlock(User user){
        user.role = 1;
    }
}
