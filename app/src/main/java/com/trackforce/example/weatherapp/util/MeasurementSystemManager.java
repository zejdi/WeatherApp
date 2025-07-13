package com.trackforce.example.weatherapp.util;

import static android.icu.util.LocaleData.MeasurementSystem.UK;
import static android.icu.util.LocaleData.MeasurementSystem.US;

import android.content.Context;
import android.icu.util.LocaleData;
import android.icu.util.ULocale;
import android.os.Build;

import androidx.annotation.RequiresApi;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class MeasurementSystemManager {

    public enum MeasurementSystem {
        METRIC("metric", "°C"),
        IMPERIAL("imperial", "°F"),
        UK("standard", "K");

        private final String value;
        private final String tempUnit;

        MeasurementSystem(String name, String tempUnit) {
            this.value = name;
            this.tempUnit = tempUnit;
        }

        public String getValue() {
            return value;
        }

        public String getTempUnit() {
            return tempUnit;
        }
    }

    private final MeasurementSystem system;

    @Inject
    @RequiresApi(api = Build.VERSION_CODES.P)
    public MeasurementSystemManager(@ApplicationContext Context context) {
        system = resolveMeasurementSystem();
    }

    public MeasurementSystem getSystem() {
        return system;
    }

    public boolean isMetric() {
        return system == MeasurementSystem.METRIC;
    }

    public boolean isImperial() {
        return system == MeasurementSystem.IMPERIAL;
    }

    public boolean isUK() {
        return system == MeasurementSystem.UK;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private MeasurementSystem resolveMeasurementSystem() {
        ULocale uLocale = ULocale.getDefault(); // ICU locale
        LocaleData.MeasurementSystem icuSystem = LocaleData.getMeasurementSystem(uLocale);

        if (icuSystem.equals(US)) {
            return MeasurementSystem.IMPERIAL;
        } else if (icuSystem.equals(UK)) {
            return MeasurementSystem.UK;
        }
        return MeasurementSystem.METRIC;
    }
}
