package com.example.inoutlog;

public class ApprovalInfo {
    private String status;


    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public ApprovalInfo() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
