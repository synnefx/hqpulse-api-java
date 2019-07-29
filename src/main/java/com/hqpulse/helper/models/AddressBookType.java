package com.hqpulse.helper.models;

/**
 * @author Josekutty
 * 28-07-2019
 */
public enum AddressBookType {
    HOSPITAL_USER("Hospital User"), ACCOUNT_USER("Account User"), STAFF("Staff"), PATIENT("Patient"), CUSTOMER(
            "Customer"), THIRD_PARTY("Third Party"), OTHER("Other");

    private final String displayString;

    AddressBookType(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return displayString;
    }
}
