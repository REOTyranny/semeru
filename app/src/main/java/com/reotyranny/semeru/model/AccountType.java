package com.reotyranny.semeru.model;

import android.support.annotation.NonNull;

public enum AccountType {
    EMPLOYEE,
    MANAGER,
    ADMINISTRATOR,
    DONOR;

    @NonNull
    @Override
    public String toString() {
        return (this.name());
    }
}
