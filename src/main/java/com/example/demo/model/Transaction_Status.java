package com.example.demo.model;

public enum Transaction_Status {
    COMPLETED("completed"),
    REFUNDED("refunded"),
    FAILED ("failed"),
    IN_PROGRESS("in progress");

    private String description;

    Transaction_Status(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
