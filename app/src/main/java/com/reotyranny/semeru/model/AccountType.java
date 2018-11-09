package com.reotyranny.semeru.model;

import android.support.annotation.NonNull;

/**
 * Enumerates possible account types.
 */
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
