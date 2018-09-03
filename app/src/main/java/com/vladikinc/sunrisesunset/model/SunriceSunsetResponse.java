package com.vladikinc.sunrisesunset.model;

import com.google.gson.annotations.SerializedName;

public class SunriceSunsetResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("results")
    private SunriseSunsetModel results;

    public SunriceSunsetResponse() {
    }

    public SunriceSunsetResponse(String status, SunriseSunsetModel results) {
        this.status = status;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SunriseSunsetModel getResults() {
        return results;
    }

    public void setResults(SunriseSunsetModel results) {
        this.results = results;
    }
}
