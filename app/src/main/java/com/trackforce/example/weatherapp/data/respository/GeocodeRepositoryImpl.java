package com.trackforce.example.weatherapp.data.respository;

import android.location.Location;

import com.trackforce.example.weatherapp.data.local.GeocodeLocalDataSource;
import com.trackforce.example.weatherapp.data.remote.GeocodeRemoteDataSource;
import com.trackforce.example.weatherapp.domain.model.Geocode;
import com.trackforce.example.weatherapp.domain.repository.GeocodeRepository;
import com.trackforce.example.weatherapp.manager.WeatherConnectivityManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class GeocodeRepositoryImpl implements GeocodeRepository {
    private GeocodeRemoteDataSource geocodeRemoteDataSource;
    private GeocodeLocalDataSource geocodeLocalDataSource;
    private WeatherConnectivityManager weatherConnectivityManager;

    @Inject
    public GeocodeRepositoryImpl(GeocodeRemoteDataSource geocodeRemoteDataSource, GeocodeLocalDataSource geocodeLocalDataSource, WeatherConnectivityManager weatherConnectivityManager) {
        this.geocodeRemoteDataSource = geocodeRemoteDataSource;
        this.geocodeLocalDataSource = geocodeLocalDataSource;
        this.weatherConnectivityManager = weatherConnectivityManager;
    }

    @Override
    public List<Geocode> getPlace(Location location) {
        if (location == null) {
            return getFromLocal();
        }
        if (weatherConnectivityManager.isOnline()) {
            try {
                Response<List<Geocode>> response = geocodeRemoteDataSource.getPlace(location.getLatitude(), location.getLongitude()).execute();
                if (response.isSuccessful()) {
                    List<Geocode> body = response.body();
                    if (body != null && !body.isEmpty()) {
                        geocodeLocalDataSource.saveGeocode(body.get(0));
                    }
                    return body;
                }
            } catch (IOException ignored) {}
        } else {
            return getFromLocal();
        }
        return new ArrayList<>();
    }

    private List<Geocode> getFromLocal() {
        Geocode geocode = geocodeLocalDataSource.getGeocode();
        if (geocode != null) {
            return Collections.singletonList(geocode);
        }
        return Collections.emptyList();
    }
}
