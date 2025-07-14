package com.trackforce.example.weatherapp.ui;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.domain.usecase.GetPlaceUseCase;
import com.trackforce.example.weatherapp.domain.usecase.GetUnitUseCase;
import com.trackforce.example.weatherapp.domain.usecase.GetWeatherUseCase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherForecastViewModel extends ViewModel {
    private GetWeatherUseCase getWeatherUseCase;
    private GetUnitUseCase getUnitUseCase;
    private GetPlaceUseCase getPlaceUseCase;

    private MutableLiveData<String> placeLiveData = new MutableLiveData<>();
    private MutableLiveData<WeatherForecast> weatherForecastLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> requestLocationUpdates = new MutableLiveData<>();
    private MutableLiveData<String> unit = new MutableLiveData<>();
    private final Executor executor = Executors.newCachedThreadPool();

    @Inject
    public WeatherForecastViewModel(GetWeatherUseCase getWeatherUseCase, GetUnitUseCase getUnitUseCase, GetPlaceUseCase getPlaceUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.getUnitUseCase = getUnitUseCase;
        this.getPlaceUseCase = getPlaceUseCase;
    }

    public void getWeatherForecast(Location location) {
        executor.execute(() -> {
            placeLiveData.postValue(getPlaceUseCase.execute(location));
            WeatherForecast response = getWeatherUseCase.execute(location);
            if (response != null) weatherForecastLiveData.postValue(response);
            unit.postValue(getUnitUseCase.execute());
        });
    }

    public void locationPermissionGranted() {
        requestLocationUpdates.setValue(true);
    }

    public void connectedToInternet() {
        requestLocationUpdates.setValue(true);
    }

    public LiveData<WeatherForecast> getWeatherForecastLiveData() {
        return weatherForecastLiveData;
    }

    public LiveData<Boolean> getRequestLocationUpdates() {
        return requestLocationUpdates;
    }

    public LiveData<String> getUnit() {
        return unit;
    }

    public LiveData<String> getPlaceLiveData() {
        return placeLiveData;
    }
}
