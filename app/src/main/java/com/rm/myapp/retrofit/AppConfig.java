package com.rm.myapp.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class AppConfig {
    private static String BASE_URL = "http://arthtechsolutions.com/geo/api/";

    public static Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS).build();
        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}