package com.trackforce.example.weatherapp.data.local;

import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.manager.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WeatherForecastLocalDataSource {
    private PreferenceManager preferenceManager;

    @Inject
    public WeatherForecastLocalDataSource(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
    }

    public void saveWeatherForecast(WeatherForecast weatherForecast) {
        preferenceManager.saveObject(PreferenceManager.PreferenceKey.WEATHER_FORECAST, weatherForecast);
    }

    public WeatherForecast getWeatherForecast() {
        return preferenceManager.getObject(PreferenceManager.PreferenceKey.WEATHER_FORECAST, WeatherForecast.class);
    }

}
