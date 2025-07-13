package com.trackforce.example.weatherapp.domain.repository;

import android.location.Location;

import com.trackforce.example.weatherapp.domain.model.WeatherForecast;

public interface WeatherForecastRepository {
    WeatherForecast getWeatherForecast(Location location);
}
