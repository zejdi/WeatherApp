package com.trackforce.example.weatherapp.domain.usecase;

import android.location.Location;

import com.trackforce.example.weatherapp.domain.model.Geocode;
import com.trackforce.example.weatherapp.domain.repository.GeocodeRepository;

import java.util.List;

import javax.inject.Inject;

public class GetCityUseCase {
    private final GeocodeRepository geocodeRepository;

    @Inject
    public GetCityUseCase(GeocodeRepository geocodeRepository) {
        this.geocodeRepository = geocodeRepository;
    }

    public String execute(Location location) {
        List<Geocode> places = geocodeRepository.getPlace(location);
        if (!places.isEmpty()) {
            return places.get(0).getName();
        }
        return "N/A";
    }
}
