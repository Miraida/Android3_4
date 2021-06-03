package com.geek.android3_4.data.remote;

import com.geek.android3_4.data.model.currentweather.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {
    @GET("weather")
    Call<CurrentWeather> getCityWeather(
            @Query("q") String city_name,
            @Query("appid") String appid
    );
// NOTE! Difference between path and query!!!
//    @GET("data/{id}/weather")
//    Call<CurrentWeather> getnumber(
//            @Path("id") int id,
//            @Query("q") String city_name,
//            @Query("appid") String appid
//    );
}
