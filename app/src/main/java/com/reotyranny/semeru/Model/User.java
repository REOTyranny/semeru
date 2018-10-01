package com.reotyranny.semeru.Model;

/**
 * Represents a User.
 * @author rpaulucci3
 */
public class User extends Account {
    public User(String name, String username, String password, String email){
        super(name,username,password,email, 1,false);
    }

    public User(String name, String username, String password, String email, int role){
        super(name, username, password, email,1,false);
    }

}

