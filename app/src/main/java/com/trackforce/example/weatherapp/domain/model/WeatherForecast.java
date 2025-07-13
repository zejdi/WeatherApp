package com.trackforce.example.weatherapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherForecast {

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lon")
    private Double lon;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("timezone_offset")
    private Integer timezoneOffset;

    @SerializedName("current")
    private Current current;

    @SerializedName("daily")
    private ArrayList<Daily> daily;

    // Getters and Setters
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public ArrayList<Daily> getDaily() {
        return daily;
    }

    public void setDaily(ArrayList<Daily> daily) {
        this.daily = daily;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
