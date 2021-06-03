package com.geek.android3_4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geek.android3_4.R;
import com.geek.android3_4.data.model.currentweather.CurrentWeather;
import com.geek.android3_4.data.storage.WeatherStorage;
import com.geek.android3_4.databinding.FragmentHomeBinding;
import com.geek.android3_4.utils.Constants;
import com.geek.android3_4.utils.DayTime;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Date;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding ui;
    private final WeatherStorage storage = new WeatherStorage();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = FragmentHomeBinding.inflate(getLayoutInflater());
        return ui.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWeather();
    }

    private void getWeather() {
        storage.getWeather(Constants.default_city_name, this::setData);
    }

    private void setData(CurrentWeather currentWeather) {
        String imageUri, pressure, humidity, speedWind;
        long date = currentWeather.getDt(),
                sunrise = currentWeather.getSys().getSunrise(),
                sunset = currentWeather.getSys().getSunset();
        ui.mainImage.setImageResource(getImageId(date, sunrise, sunset));
        ui.progressBar.setVisibility(View.GONE);
        ui.dateTextView.setText(DayTime.getDate(requireContext(), date));
        ui.locationTextView.setText(currentWeather.getName());
        imageUri = "http://openweathermap.org/img/wn/" + currentWeather.getWeather().get(0).getIcon() + "@2x.png";
        Picasso.get().load(imageUri).resize(70, 70).into(ui.weatherImageView);
        ui.weatherTextView.setText(currentWeather.getWeather().get(0).getMain());
        ui.weatherImageView.setContentDescription(currentWeather.getWeather().get(0).getDescription());
        ui.degTextView.setText(getCelsius(currentWeather.getTemp().getTemp()));
        ui.degUpTextView.setText(getCelsius(currentWeather.getTemp().getTempMax()));
        ui.degDownTextView.setText(getCelsius(currentWeather.getTemp().getTempMin()));
        humidity = currentWeather.getTemp().getHumidity() + getResources().getString(R.string.percentage);
        ui.humidityTextView.setText(humidity);
        pressure = currentWeather.getTemp().getPressure() + getResources().getString(R.string.mbar);
        ui.pressureTextView.setText(pressure);
        speedWind = currentWeather.getWind().getSpeed() + getResources().getString(R.string.m_s);
        ui.windTextView.setText(speedWind);
        ui.sunriseTextView.setText(DayTime.getTime(requireContext(), sunrise));
        ui.sunsetTextView.setText(DayTime.getTime(requireContext(), sunset));
        ui.dayTimeTextView.setText(DayTime.getDayTime(requireContext(), currentWeather.getSys().getSunrise(), currentWeather.getSys().getSunset()));
    }

    private int getImageId(long date, long sunrise, long sunset) {
        Date current = new Date(date * 1000),
                before = new Date(sunrise * 1000),
                after = new Date(sunset * 1000);
        if (current.compareTo(after) < 0)
            return R.drawable.ic_day;
        else if (current.compareTo(before) > 0)
            return R.drawable.ic_night;
        else return R.drawable.ic_day;
    }


    private String getCelsius(Double kelvin) {
        return new DecimalFormat("##").format(kelvin - 273.15);
    }
}