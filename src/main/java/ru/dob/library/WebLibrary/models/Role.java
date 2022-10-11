package ru.dob.library.WebLibrary.models;

public enum Role {
    ROLE_STAFF("Staff"),
    ROLE_ADMIN("Admin");

    private final String displayText;

    Role(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
