package com.geek.android3_4.data.storage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.geek.android3_4.data.model.currentweather.CurrentWeather;
import com.geek.android3_4.data.remote.RetrofitBuilder;
import com.geek.android3_4.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherStorage {
    public void getWeather(String city_name, WeatherCallback<CurrentWeather> callback) {
        if (city_name.equals("")) city_name = Constants.default_city_name;
        Log.d("TAG", "getWeather: ");
        RetrofitBuilder.getInstance().getCityWeather(city_name, Constants.getAPIKey()).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                if (response != null && response.body() != null) {
                    callback.onResponse(response.body());
                } else Log.d("TAG", "onResponse: Request error!");
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                callback.onFailure("getDefaultWeather() | " + t.getLocalizedMessage());
            }
        });
    }

    public interface WeatherCallback<ContentData> {
        void onResponse(ContentData data);

        default void onFailure(String errorMsg) {
            Log.d("TAG", "onFailure: " + errorMsg);
        }
    }
}
