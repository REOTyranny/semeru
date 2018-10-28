package com.reotyranny.semeru.Model;

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
