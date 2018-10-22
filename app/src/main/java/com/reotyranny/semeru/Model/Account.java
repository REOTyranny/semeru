package com.reotyranny.semeru.Model;



public abstract class Account {
    protected String name;
    protected String username;
    protected String password;
    protected String email;
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

    public Account(String name, String username, String password, String email ){
        this(name,username,password,email,false);
    }
    public Account(String name, String username, String password, String email, boolean locked)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.locked = locked;
    }
}
