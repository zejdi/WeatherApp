package com.trackforce.example.weatherapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class FeelsLike {

    @SerializedName("day")
    private Double day;

    @SerializedName("night")
    private Double night;

    @SerializedName("eve")
    private Double eve;

    @SerializedName("morn")
    private Double morn;

    // Getters and Setters
    public Double getDay() { return day; }
    public void setDay(Double day) { this.day = day; }

    public Double getNight() { return night; }
    public void setNight(Double night) { this.night = night; }

    public Double getEve() { return eve; }
    public void setEve(Double eve) { this.eve = eve; }

    public Double getMorn() { return morn; }
    public void setMorn(Double morn) { this.morn = morn; }
}
