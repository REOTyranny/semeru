package com.reotyranny.semeru.Model;

/**
 * Represents a User.
 * @author rpaulucci3
 */
public class User {

    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
        }
    public void setPass(String password){
        this.password = password;
    }
    public void setUserName(String name){ // sets username
    this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getName(){return name;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}

}

