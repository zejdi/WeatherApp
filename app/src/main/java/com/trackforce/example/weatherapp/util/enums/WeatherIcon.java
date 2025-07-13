package com.trackforce.example.weatherapp.util.enums;

import androidx.annotation.DrawableRes;

import com.trackforce.example.weatherapp.R;

public enum WeatherIcon {
    CLEAR_SKY("01d", R.drawable.clear_sky),
    FEW_CLOUDS("02d", R.drawable.few_clouds),
    SCATTERED_CLOUDS("03d", R.drawable.scatterd_cloud),
    BROKEN_CLOUDS("04d", R.drawable.broken_clouds),
    SHOWER_RAIN("09d", R.drawable.shower_rain),
    RAIN("10d", R.drawable.rain),
    THUNDERSTORM("11d", R.drawable.thunderstorm),
    SNOW("13d", R.drawable.snow),
    MIST("50d", R.drawable.mist),
    CLEAR_SKY_NIGHT("01n", R.drawable.clear_sky),
    FEW_CLOUDS_NIGHT("02n", R.drawable.few_clouds),
    SCATTERED_CLOUDS_NIGHT("03n", R.drawable.scatterd_cloud),
    BROKEN_CLOUDS_NIGHT("04n", R.drawable.broken_clouds),
    SHOWER_RAIN_NIGHT("09n", R.drawable.shower_rain),
    RAIN_NIGHT("10n", R.drawable.rain),
    THUNDERSTORM_NIGHT("11n", R.drawable.thunderstorm),
    SNOW_NIGHT("13n", R.drawable.snow),
    MIST_NIGHT("50n", R.drawable.mist);

    private final String id;
    private final int icon;

    WeatherIcon(String id, @DrawableRes int icon) {
        this.id = id;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public static WeatherIcon findById(String id) {
        for (WeatherIcon icon : values()) {
            if (icon.id.equals(id)) {
                return icon;
            }
        }
        return null; // or throw an exception if you prefer
    }
}
