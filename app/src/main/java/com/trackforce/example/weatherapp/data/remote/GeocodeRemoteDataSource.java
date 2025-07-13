package com.trackforce.example.weatherapp.data.remote;

import com.trackforce.example.weatherapp.domain.model.Geocode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodeRemoteDataSource {
    @GET("geo/1.0/reverse?")
    Call<List<Geocode>> getPlace(
            @Query("lat") double lat,
            @Query("lon") double lng
    );
}
