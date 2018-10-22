package com.reotyranny.semeru.Model;

public class Account {

    private String name;
    private String username;
    private String email;
    private AccountType acctType;
    boolean locked;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getAcctType() {
        return acctType;
    }

    public void setAcctType(AccountType acctType) {
        this.acctType = acctType;
    }

    public Account(String name, String email, AccountType acctType) {
        this.name = name;
        this.email = email;
        this.acctType = acctType;
        this.locked = false;
    }

    public Account(String name, String username, String email) {
        this(name, username, email, false);
    }

    public Account(String name, String username, String email, boolean locked)
    {
        this.name = name;
        this.username = username;
        this.email = email;
        this.locked = locked;
    }
}
