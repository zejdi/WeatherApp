package com.trackforce.example.weatherapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.location.Location;
import android.location.LocationManager;

import com.trackforce.example.weatherapp.data.local.GeocodeLocalDataSource;
import com.trackforce.example.weatherapp.data.remote.GeocodeRemoteDataSource;
import com.trackforce.example.weatherapp.data.respository.GeocodeRepositoryImpl;
import com.trackforce.example.weatherapp.domain.model.Geocode;
import com.trackforce.example.weatherapp.domain.model.WeatherForecast;
import com.trackforce.example.weatherapp.domain.repository.GeocodeRepository;
import com.trackforce.example.weatherapp.domain.repository.WeatherForecastRepository;
import com.trackforce.example.weatherapp.domain.usecase.GetPlaceUseCase;
import com.trackforce.example.weatherapp.domain.usecase.GetWeatherUseCase;
import com.trackforce.example.weatherapp.manager.WeatherConnectivityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

@RunWith(RobolectricTestRunner.class)
public class GetPlaceUseCaseTest {

    private GetPlaceUseCase useCase;
    private GeocodeRemoteDataSource geocodeRemoteDataSource;
    private GeocodeLocalDataSource geocodeLocalDataSource;
    private WeatherConnectivityManager weatherConnectivityManager;
    private Call<List<Geocode>> call;

    @Before
    public void setUp() {
        geocodeLocalDataSource = mock(GeocodeLocalDataSource.class);
        geocodeRemoteDataSource = mock(GeocodeRemoteDataSource.class);
        weatherConnectivityManager = mock(WeatherConnectivityManager.class);
        call = mock(Call.class);
        GeocodeRepository repository = new GeocodeRepositoryImpl(geocodeRemoteDataSource, geocodeLocalDataSource, weatherConnectivityManager);
        useCase = new GetPlaceUseCase(repository);
    }

    @Test
    public void execute_checkIfGetFromOffline() throws IOException {
        double lat = 42.3;
        double lng = 21.4;

        Geocode remoteGeocode = new Geocode();
        remoteGeocode.setName("North");
        remoteGeocode.setLat(lat);
        remoteGeocode.setLon(lng);

        Geocode localGeocode = new Geocode();
        localGeocode.setName("South");
        localGeocode.setLat(41.23);
        localGeocode.setLon(21.1);

        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(lat);
        location.setLongitude(lng);
        when(call.execute()).thenReturn(Response.success(List.of(remoteGeocode)));
        when(geocodeRemoteDataSource.getPlace(lat, lng)).thenReturn(call);
        when(geocodeLocalDataSource.getGeocode()).thenReturn(localGeocode);
        when(weatherConnectivityManager.isOnline()).thenReturn(false);

        String name = useCase.execute(location);

        assertEquals(name, localGeocode.getName());
    }

    @Test
    public void execute_checkIfGetFromOfflineWhenNoLocation() throws IOException {
        double lat = 42.3;
        double lng = 21.4;

        Geocode remoteGeocode = new Geocode();
        remoteGeocode.setName("North");
        remoteGeocode.setLat(lat);
        remoteGeocode.setLon(lng);

        Geocode localGeocode = new Geocode();
        localGeocode.setName("South");
        localGeocode.setLat(41.23);
        localGeocode.setLon(21.1);

        when(call.execute()).thenReturn(Response.success(List.of(remoteGeocode)));
        when(geocodeRemoteDataSource.getPlace(lat, lng)).thenReturn(call);
        when(geocodeLocalDataSource.getGeocode()).thenReturn(localGeocode);

        String name = useCase.execute(null);

        assertEquals(name, localGeocode.getName());
    }
}
