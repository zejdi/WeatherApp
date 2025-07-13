package com.trackforce.example.weatherapp.domain.usecase;

import android.location.Location;

import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.domain.repository.WeatherForecastRepository;

import javax.inject.Inject;

public class GetWeatherUseCase {
    private WeatherForecastRepository weatherForecastRepository;

    @Inject
    public GetWeatherUseCase(WeatherForecastRepository weatherForecastRepository) {
        this.weatherForecastRepository = weatherForecastRepository;
    }

    public WeatherForecast execute(Location location) {
        return weatherForecastRepository.getWeatherForecast(location);
    }
}
