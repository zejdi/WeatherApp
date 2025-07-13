package com.trackforce.example.weatherapp.manager;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class WeatherConnectivityManager {

    private final Application application;
    private final MutableLiveData<Boolean> hasInternetConnection = new MutableLiveData<>();

    @Inject
    public WeatherConnectivityManager(Application application, Retrofit retrofit) {
        this.application = application;

        ConnectivityManager connectivityManager = (ConnectivityManager)
                application.getSystemService(Context.CONNECTIVITY_SERVICE);

        hasInternetConnection.setValue(isOnline());

        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                if (Boolean.FALSE.equals(hasInternetConnection.getValue())) {
                    hasInternetConnection.postValue(true);
                }
            }

            @Override
            public void onLost(@NonNull Network network) {
                    hasInternetConnection.postValue(false);
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                application.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkCapabilities capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true;
            }
        }
        return false;
    }

    // Accessors
    public LiveData<Boolean> getHasInternetConnection() {
        return hasInternetConnection;
    }

}
