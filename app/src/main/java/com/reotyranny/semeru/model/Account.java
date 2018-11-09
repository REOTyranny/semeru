package com.reotyranny.semeru.model;

/**
 * Represents a Firebase account.
 */
public class Account {

    private AccountType acctType;

    private String email;

    private String location;

    private boolean locked;

    private String name;

    private String username;

    /**
     * Creates a new Account object
     */
    public Account() {
    }

    /**
     * Creates a new Account object
     *
     * @param name     the user's name
     * @param email    the user's email address
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
     * @param name     the user's name
     * @param email    the user's email address
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
     *
     * @return the Account type
     */
    public AccountType getAcctType() {
        return acctType;
    }

    /**
     * Sets the type for the Account object
     *
     * @param acctType the account type
     */
    public void setAcctType(AccountType acctType) {
        this.acctType = acctType;
    }

    /**
     * Gets the email of the Account object
     *
     * @return the Account's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the Account object
     *
     * @param email the given email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the location where the user who's account this is works
     *
     * @return the location where the user who's account this is works
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location where the user who's account this is will work
     *
     * @param location a given location where an employee can work
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get's the name associated with the Account object
     *
     * @return the name associated with the Account object
     */
    public String getName() {
        return name;
    }

    /**
     * Set's the name associated with the Account object
     *
     * @param name the name associated with the Account object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get's the username associated with the Account object
     *
     * @return the username associated with the Account object
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set's the username associated with the Account object
     *
     * @param username the username associated with the Account object
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
