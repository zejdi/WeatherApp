package com.trackforce.example.weatherapp.domain.model;

import com.google.gson.annotations.SerializedName;
import com.trackforce.example.weatherapp.util.enums.WeatherIcon;

public class Weather {

    @SerializedName("id")
    private Integer id;

    @SerializedName("main")
    private String main;

    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String icon;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public WeatherIcon getWeatherIcon() {
        return WeatherIcon.findById(icon);
    }
}
