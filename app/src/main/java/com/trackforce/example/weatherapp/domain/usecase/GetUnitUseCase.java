package com.trackforce.example.weatherapp.domain.usecase;

import com.trackforce.example.weatherapp.util.MeasurementSystemManager;

import javax.inject.Inject;

public class GetUnitUseCase {
    private final MeasurementSystemManager measurementSystemManager;

    @Inject
    public GetUnitUseCase(MeasurementSystemManager measurementSystemManager) {
        this.measurementSystemManager = measurementSystemManager;
    }

    public String execute() {
        return measurementSystemManager.getSystem().getTempUnit();
    }
}
