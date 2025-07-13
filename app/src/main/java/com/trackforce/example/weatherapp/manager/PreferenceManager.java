package com.trackforce.example.weatherapp.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class PreferenceManager {

    private static final String PREF_NAME = "app_preferences";
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public enum PreferenceKey {
        WEATHER_FORECAST("weather_forecast"),
        GEOCODE("geocode");
        private final String key;

        PreferenceKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    @Inject
    public PreferenceManager(@ApplicationContext Context context, Gson gson) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public <T> void saveObject(PreferenceKey key, T object) {
        String json = gson.toJson(object);
        sharedPreferences.edit().putString(key.getKey(), json).apply();
    }

    public <T> T getObject(PreferenceKey key, Class<T> type) {
        String json = sharedPreferences.getString(key.getKey(), null);
        return json != null ? gson.fromJson(json, type) : null;
    }

    public void remove(PreferenceKey key) {
        sharedPreferences.edit().remove(key.getKey()).apply();
    }

    public void clearAll() {
        sharedPreferences.edit().clear().apply();
    }
}
