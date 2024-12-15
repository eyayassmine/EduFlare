package com.example.complaintmanagement.Entities;

public enum ComplaintCategory {
    TECHNICAL_ISSUE("Technical Issue"),
    COURSE_CONTENT("Course Content"),
    EVALUATION("Evaluation"),
    PAYMENT_ISSUES("Payment Issues"),
    Bullying("Bullying"),

    OTHER("Other");

    private final String displayName;
    ComplaintCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
