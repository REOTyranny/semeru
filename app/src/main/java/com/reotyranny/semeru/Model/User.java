package com.reotyranny.semeru.Model;

/**
 * Represents a User.
 * @author rpaulucci3
 */
public class User {

    private String name;
    private String password;
    private String email;
    private int role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

//    public User(String name, String password, String email, int role){
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.role = role;
//        }

}

