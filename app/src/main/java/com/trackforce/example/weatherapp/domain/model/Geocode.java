package com.trackforce.example.weatherapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class Geocode {

    @SerializedName("name")
    private String name;

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lon")
    private Double lon;

    @SerializedName("country")
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
