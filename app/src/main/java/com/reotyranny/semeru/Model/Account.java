package com.reotyranny.semeru.Model;

public class Account {

    private boolean locked;

    private AccountType acctType;

    private String email;

    private String location;

    private String name;

    private String username;

    public Account() {
    }

    public Account(String name, String email, AccountType acctType) {
        this.name = name;
        this.email = email;
        this.acctType = acctType;
        this.locked = false;
    }

    public Account(String name, String email, AccountType acctType, String location) {
        this.name = name;
        this.email = email;
        this.acctType = acctType;
        this.location = location;
        this.locked = false;
    }

    public AccountType getAcctType() {
        return acctType;
    }

    public void setAcctType(AccountType acctType) {
        this.acctType = acctType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

}
