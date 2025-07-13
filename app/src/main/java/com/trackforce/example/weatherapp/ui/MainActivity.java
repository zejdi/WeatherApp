package com.trackforce.example.weatherapp.ui;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.trackforce.example.weatherapp.databinding.ActivityMainBinding;
import com.trackforce.example.weatherapp.domain.model.Current;
import com.trackforce.example.weatherapp.domain.model.Daily;
import com.trackforce.example.weatherapp.manager.WeatherConnectivityManager;
import com.trackforce.example.weatherapp.ui.adapter.WeeklyForecastAdapter;
import com.trackforce.example.weatherapp.util.PermissionHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WeatherForecastViewModel viewModel;
    private PermissionHelper permissionHelper;
    private LocationManager locationManager;
    private WeeklyForecastAdapter adapter;

    @Inject
    WeatherConnectivityManager weatherConnectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        viewModel = new ViewModelProvider(this).get(WeatherForecastViewModel.class);

        viewModel.getWeatherForecast(null);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupLocationUpdates();

        setupWeatherForecast();

        setupPlace();

        setupUnits();

        setupInternetConnection();

        setupWeekForecast();

        checkLocationPermission();
    }

    private int times = 0;

    private void setupLocationUpdates() {
        viewModel.getRequestLocationUpdates().observe(this, aBoolean -> {
            String[] locationPermissions = {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            if (permissionHelper.hasPermissions(locationPermissions)) {
                @SuppressLint("MissingPermission")
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                viewModel.getWeatherForecast(location);
                Toast.makeText(this, times++ + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupWeatherForecast() {
        viewModel.getWeatherForecastLiveData().observe(this, weatherForecast -> {
            Current today = weatherForecast.getCurrent();
            Daily tomorrow = weatherForecast.getDaily().get(0);
            binding.date.setVisibility(VISIBLE);
            binding.icon.setVisibility(VISIBLE);
            if (today.getWeather() != null && !today.getWeather().isEmpty()) {
                binding.icon.setImageResource(today.getWeather().get(0).getWeatherIcon().getIcon());
            }

            binding.todayTempContainer.setVisibility(VISIBLE);
            binding.todayTemp.setText(String.valueOf(today.getTemp().intValue()));
            binding.tomorrowTemp.setText(String.valueOf(tomorrow.getTemp().getDay().intValue()));
            binding.tomorrow.setVisibility(VISIBLE);
            binding.tomorrowIcon.setVisibility(VISIBLE);
            if (tomorrow.getWeather() != null && !tomorrow.getWeather().isEmpty()) {
                binding.tomorrowIcon.setImageResource(tomorrow.getWeather().get(0).getWeatherIcon().getIcon());
            }

            adapter.setDailyForecasts(weatherForecast.getDaily());
            binding.overlay.setVisibility(GONE);
        });
    }

    private void setupPlace() {
        viewModel.getPlaceLiveData().observe(this, place -> {
            binding.place.setVisibility(VISIBLE);
            binding.place.setText(place);
        });
    }

    private void setupUnits() {
        viewModel.getUnit().observe(this, unit -> {
            binding.unit.setText(unit);
            binding.tomorrowTempUnit.setText(unit);
            adapter.setUnit(unit);
        });
    }

    private void setupInternetConnection() {
        weatherConnectivityManager.getHasInternetConnection().observe(this, isOnline -> {
            if (isOnline) {
                viewModel.connectedToInternet();
            }
        });
    }

    private void setupWeekForecast() {
        adapter = new WeeklyForecastAdapter(new ArrayList<>(1));
        binding.weekForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.weekForecast.setAdapter(adapter);
    }

    private void checkLocationPermission() {
        locationManager = getSystemService(LocationManager.class);
        permissionHelper = new PermissionHelper(this, 100);

        String[] locationPermissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        if (!permissionHelper.hasPermissions(locationPermissions)) {
            permissionHelper.requestPermissions(locationPermissions);
        } else {
            viewModel.locationPermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        permissionHelper.handlePermissionResult(permissions, grantResults, new PermissionHelper.PermissionCallback() {
            @Override
            public void onGranted() {
                viewModel.locationPermissionGranted();
            }

            @Override
            public void onDenied() {
                Toast.makeText(MainActivity.this, "Please enable you location services to continue", Toast.LENGTH_SHORT).show();
            }
        });
    }

}