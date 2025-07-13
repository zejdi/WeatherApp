package com.trackforce.example.weatherapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Daily {

    @SerializedName("dt")
    private Integer dt;

    @SerializedName("sunrise")
    private Integer sunrise;

    @SerializedName("sunset")
    private Integer sunset;

    @SerializedName("moonrise")
    private Integer moonrise;

    @SerializedName("moonset")
    private Integer moonset;

    @SerializedName("moon_phase")
    private Double moonPhase;

    @SerializedName("summary")
    private String summary;

    @SerializedName("temp")
    private Temp temp = new Temp();

    @SerializedName("feels_like")
    private FeelsLike feelsLike;

    @SerializedName("pressure")
    private Integer pressure;

    @SerializedName("humidity")
    private Integer humidity;

    @SerializedName("dew_point")
    private Double dewPoint;

    @SerializedName("wind_speed")
    private Double windSpeed;

    @SerializedName("wind_deg")
    private Integer windDeg;

    @SerializedName("wind_gust")
    private Double windGust;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("clouds")
    private Integer clouds;

    @SerializedName("pop")
    private Double pop;

    @SerializedName("rain")
    private Double rain;

    @SerializedName("uvi")
    private Double uvi;


    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public Integer getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(Integer moonrise) {
        this.moonrise = moonrise;
    }

    public Integer getMoonset() {
        return moonset;
    }

    public void setMoonset(Integer moonset) {
        this.moonset = moonset;
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(FeelsLike feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public Double getUvi() {
        return uvi;
    }

    public void setUvi(Double uvi) {
        this.uvi = uvi;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(dt),
                ZoneId.systemDefault()
        );
    }
}
