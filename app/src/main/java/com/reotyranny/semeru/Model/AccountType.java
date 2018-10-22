package com.reotyranny.semeru.Model;

public enum AccountType {
    EMPLOYEE,
    MANAGER,
    ADMINISTRATOR,
    DONOR;

    @Override
    public String toString() {
        return(this.name());
    }
}
