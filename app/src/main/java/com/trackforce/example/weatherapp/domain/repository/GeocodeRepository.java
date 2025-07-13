package com.trackforce.example.weatherapp.domain.repository;

import android.location.Location;

import com.trackforce.example.weatherapp.domain.model.Geocode;

import java.util.List;

public interface GeocodeRepository {

    List<Geocode> getPlace(Location location);

}
