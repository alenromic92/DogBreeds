package com.dogbreeds.network.models;

import java.util.Map;

public class BreedResponse {

    private Map<String, String[]> message;
    private String status;

    public BreedResponse() {
    }

    public Map<String, String[]> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String[]> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
