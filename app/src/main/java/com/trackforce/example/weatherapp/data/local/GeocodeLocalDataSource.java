package com.trackforce.example.weatherapp.data.local;

import com.trackforce.example.weatherapp.domain.model.Geocode;
import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.manager.PreferenceManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Singleton
public class GeocodeLocalDataSource {
    private PreferenceManager preferenceManager;

    @Inject
    public GeocodeLocalDataSource(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
    }

    public void saveGeocode(Geocode geocode) {
        preferenceManager.saveObject(PreferenceManager.PreferenceKey.GEOCODE, geocode);
    }

    public Geocode getGeocode() {
        return preferenceManager.getObject(PreferenceManager.PreferenceKey.GEOCODE, Geocode.class);
    }

}

