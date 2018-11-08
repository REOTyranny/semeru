package com.reotyranny.semeru.Model;

public class Account {

    private AccountType acctType;

    private String email;

    private String location;

    private boolean locked;

    private String name;

    private String username;

    public Account() {
    }

    /**
     * Creates a new Account object
     *
     * @param name the user's name
     * @param email the user's email address
     * @param acctType the account type
     */
    public Account(String name, String email, AccountType acctType) {
        this.name = name;
        this.email = email;
        this.acctType = acctType;
        this.locked = false;
    }

    /**
     * Creates a new Account object
     *
     * @param name the user's name
     * @param email the user's email address
     * @param acctType the account type
     * @param location the location where the user works
     */
    public Account(String name, String email, AccountType acctType, String location) {
        this.name = name;
        this.email = email;
        this.acctType = acctType;
        this.location = location;
        this.locked = false;
    }

    /**
     * Gets the type of the Account object
     * @return the Account type
     */
    public AccountType getAcctType() {
        return acctType;
    }

    /**
     * Sets the type for the Account object
     * @param acctType the account type
     */
    public void setAcctType(AccountType acctType) {
        this.acctType = acctType;
    }

    /**
     * Gets the email of the Account object
     * @return the Account's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the Account object
     * @param email the given email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the location where the user who's account this is works
     * @return the
     */
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
