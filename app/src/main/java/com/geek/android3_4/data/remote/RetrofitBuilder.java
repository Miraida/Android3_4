package com.geek.android3_4.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitBuilder {
    private static OpenWeatherApi instance;

    private RetrofitBuilder() {
    }

    public static OpenWeatherApi getInstance() {
        if (instance == null) {
            instance = createRetrofit();
        }
        return instance;
    }

    protected static OpenWeatherApi createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherApi.class);
    }

}
