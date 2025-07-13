package com.trackforce.example.weatherapp.data.respository;

import android.location.Location;

import com.trackforce.example.weatherapp.data.local.WeatherForecastLocalDataSource;
import com.trackforce.example.weatherapp.data.remote.WeatherForecastRemoteDataSource;
import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.domain.repository.WeatherForecastRepository;
import com.trackforce.example.weatherapp.manager.WeatherConnectivityManager;
import com.trackforce.example.weatherapp.util.MeasurementSystemManager;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class WeatherForecastRepositoryImpl implements WeatherForecastRepository {
    private WeatherForecastRemoteDataSource weatherForecastRemoteDataSource;
    private WeatherForecastLocalDataSource weatherForecastLocalDataSource;
    private WeatherConnectivityManager weatherConnectivityManager;
    private MeasurementSystemManager measurementSystemManager;

    @Inject
    public WeatherForecastRepositoryImpl(WeatherForecastRemoteDataSource weatherForecastRemoteDataSource, WeatherConnectivityManager weatherConnectivityManager, MeasurementSystemManager measurementSystemManager, WeatherForecastLocalDataSource weatherForecastLocalDataSource) {
        this.weatherForecastRemoteDataSource = weatherForecastRemoteDataSource;
        this.weatherConnectivityManager = weatherConnectivityManager;
        this.measurementSystemManager = measurementSystemManager;
        this.weatherForecastLocalDataSource = weatherForecastLocalDataSource;
    }

    @Override
    public WeatherForecast getWeatherForecast(Location location) {
        if (location == null) {
            return weatherForecastLocalDataSource.getWeatherForecast();
        }
        if (weatherConnectivityManager.isOnline()) {
            try {
                Response<WeatherForecast> response = weatherForecastRemoteDataSource.getWeatherForecast(location.getLatitude(), location.getLongitude(), "minutely,hourly,alerts", measurementSystemManager.getSystem().getValue()).execute();
                if (response.isSuccessful()) {
                    WeatherForecast body = response.body();
                    if (body != null) {
                        body.getDaily().remove(0);
                    }
                    weatherForecastLocalDataSource.saveWeatherForecast(body);
                    return body;
                }
            } catch (IOException ignored) {}
        } else {
            return weatherForecastLocalDataSource.getWeatherForecast();
        }
        return null;
    }
}
