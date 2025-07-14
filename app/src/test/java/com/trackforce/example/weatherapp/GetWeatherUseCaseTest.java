package com.trackforce.example.weatherapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.location.Location;
import android.location.LocationManager;

import com.trackforce.example.weatherapp.data.local.WeatherForecastLocalDataSource;
import com.trackforce.example.weatherapp.data.remote.WeatherForecastRemoteDataSource;
import com.trackforce.example.weatherapp.data.respository.WeatherForecastRepositoryImpl;
import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.domain.repository.WeatherForecastRepository;
import com.trackforce.example.weatherapp.domain.usecase.GetWeatherUseCase;
import com.trackforce.example.weatherapp.manager.WeatherConnectivityManager;
import com.trackforce.example.weatherapp.util.MeasurementSystemManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

@RunWith(RobolectricTestRunner.class)
public class GetWeatherUseCaseTest {

    private WeatherForecastRepository repository;
    private WeatherForecastRemoteDataSource weatherForecastRemoteDataSource;
    private WeatherForecastLocalDataSource weatherForecastLocalDataSource;
    private WeatherConnectivityManager weatherConnectivityManager;
    private MeasurementSystemManager measurementSystemManager;
    private GetWeatherUseCase useCase;
    private Call<WeatherForecast> call;


    @Before
    public void setUp() {
        weatherForecastRemoteDataSource = mock(WeatherForecastRemoteDataSource.class);
        weatherForecastLocalDataSource = mock(WeatherForecastLocalDataSource.class);
        weatherConnectivityManager = mock(WeatherConnectivityManager.class);
        measurementSystemManager = mock(MeasurementSystemManager.class);
        repository = new WeatherForecastRepositoryImpl(weatherForecastRemoteDataSource, weatherConnectivityManager, measurementSystemManager, weatherForecastLocalDataSource);
        call = mock(Call.class);
        useCase = new GetWeatherUseCase(repository);
    }

    @Test
    public void execute_checkIfGetFromOffline() throws IOException {
        double lat = 42.3;
        double lng = 21.4;

        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast.setLat(lat);
        weatherForecast.setLon(lng);

        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(lat);
        location.setLongitude(lng);

        WeatherForecast localWeatherForecast = new WeatherForecast();
        weatherForecast.setLat(44.12);
        weatherForecast.setLon(23.12);

        when(call.execute()).thenReturn(Response.success(weatherForecast));
        when(weatherForecastRemoteDataSource.getWeatherForecast(lat, lng, "", "")).thenReturn(call);
        when(weatherForecastLocalDataSource.getWeatherForecast()).thenReturn(localWeatherForecast);
        when(weatherConnectivityManager.isOnline()).thenReturn(false);

        WeatherForecast execute = useCase.execute(location);

        assertEquals(execute.getLat(), localWeatherForecast.getLat());
    }

    @Test
    public void execute_checkIfGetFromOfflineWhenNoLocation() throws IOException {
        double lat = 42.3;
        double lng = 21.4;

        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast.setLat(lat);
        weatherForecast.setLon(lng);

        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(lat);
        location.setLongitude(lng);

        WeatherForecast localWeatherForecast = new WeatherForecast();
        weatherForecast.setLat(44.12);
        weatherForecast.setLon(23.12);

        when(call.execute()).thenReturn(Response.success(weatherForecast));
        when(weatherForecastRemoteDataSource.getWeatherForecast(lat, lng, "", "")).thenReturn(call);
        when(weatherForecastLocalDataSource.getWeatherForecast()).thenReturn(localWeatherForecast);

        WeatherForecast execute = useCase.execute(null);

        assertEquals(execute.getLat(), localWeatherForecast.getLat());
    }
}
