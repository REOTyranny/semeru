package com.reotyranny.semeru.Model;

public class Account {
    protected String name;
    protected String username;
    protected String password;
    protected String email;
    protected int role;
    protected boolean locked ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Account(String name, String username, String password, String email ){
        this(name,username,password,email,1,false);
    }
    public Account(String name, String username, String password, String email, int role, boolean locked)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.locked = locked;
    }
}
