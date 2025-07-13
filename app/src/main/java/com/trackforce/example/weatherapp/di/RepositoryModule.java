package com.trackforce.example.weatherapp.di;

import com.trackforce.example.weatherapp.data.respository.GeocodeRepositoryImpl;
import com.trackforce.example.weatherapp.data.respository.WeatherForecastRepositoryImpl;
import com.trackforce.example.weatherapp.domain.repository.GeocodeRepository;
import com.trackforce.example.weatherapp.domain.repository.WeatherForecastRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    public abstract WeatherForecastRepository bindWeatherRepository(WeatherForecastRepositoryImpl impl);

    @Binds
    public abstract GeocodeRepository bindGeocodeRepository(GeocodeRepositoryImpl impl);
}
