package com.trackforce.example.weatherapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trackforce.example.weatherapp.databinding.ItemWeeklyForecastBinding;
import com.trackforce.example.weatherapp.domain.model.Daily;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class WeeklyForecastAdapter extends RecyclerView.Adapter<WeeklyForecastAdapter.WeeklyForecastViewHolder> {
    private ArrayList<Daily> dailyForecasts;
    private String unit;

    public WeeklyForecastAdapter(ArrayList<Daily> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

    public class WeeklyForecastViewHolder extends RecyclerView.ViewHolder {
        private final ItemWeeklyForecastBinding binding;

        public WeeklyForecastViewHolder(ItemWeeklyForecastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Daily daily) {
            binding.day.setText(getDayOFWeek(daily.getLocalDateTime().getDayOfWeek()));
            binding.temp.setText(String.valueOf(daily.getTemp().getDay().intValue()));
            binding.unit.setText(unit);
            if (daily.getWeather() != null && !daily.getWeather().isEmpty()) {
                binding.icon.setImageResource(daily.getWeather().get(0).getWeatherIcon().getIcon());
            }
        }

        private String getDayOFWeek(DayOfWeek dayOfWeek) {
            return dayOfWeek.toString().substring(0,1);
        }
    }

    @NonNull
    @Override
    public WeeklyForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeeklyForecastViewHolder(ItemWeeklyForecastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyForecastViewHolder holder, int position) {
        holder.bind(dailyForecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return dailyForecasts.size();
    }

    public void setDailyForecasts(ArrayList<Daily> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
        notifyDataSetChanged();
    }

    public void setUnit(String unit) {
        this.unit = unit;
        notifyDataSetChanged();
    }
}
