package com.trackforce.example.weatherapp.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trackforce.example.weatherapp.data.RetrofitClient;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {
    @Provides
    public Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        return RetrofitClient.getRetrofit(gson);
    }
}
