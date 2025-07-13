package com.trackforce.example.weatherapp.data.remote;
import com.trackforce.example.weatherapp.domain.model.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherForecastRemoteDataSource {
    @GET("data/3.0/onecall")
    Call<WeatherForecast> getWeatherForecast(
            @Query("lat") double lat,
            @Query("lon") double lng,
            @Query("exclude") String exclude,
            @Query("units") String units
    );
}
