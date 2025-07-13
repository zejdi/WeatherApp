package com.trackforce.example.weatherapp.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {
    @Provides
    public Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    public Gson provideRetrofit() {

    }
}
