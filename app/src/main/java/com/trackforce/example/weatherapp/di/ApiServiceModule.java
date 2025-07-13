package com.trackforce.example.weatherapp.di;

import com.trackforce.example.weatherapp.data.remote.GeocodeRemoteDataSource;
import com.trackforce.example.weatherapp.data.remote.WeatherForecastRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class ApiServiceModule {

    @Provides
    public WeatherForecastRemoteDataSource provideWeatherRemoteDataSource(Retrofit retrofit) {
        return retrofit.create(WeatherForecastRemoteDataSource.class);
    }

    @Provides
    public GeocodeRemoteDataSource provideGeocodeRemoteDataSource(Retrofit retrofit) {
        return retrofit.create(GeocodeRemoteDataSource.class);
    }
}
