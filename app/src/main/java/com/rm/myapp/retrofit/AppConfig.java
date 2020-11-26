package com.rm.myapp.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class AppConfig {
    private static String BASE_URL = "https://chatwithsupport.us/tracker/";
    public static String Key = "9787svdsdca";
    public static String Token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDYzMDMxMzAsImNoayI6MTI1ODg5NTkwMSwic3RhcnQiOjE2MDYzMDMxMzAsImV4cGlyZSI6MTYwNjMxMDMzMCwicGhvbmUiOiI5ODc2NTQzMjEwIiwiaWQiOiIxIiwicm9sZSI6IjIiLCJzdGF0dXMiOiIxIn0.HTt3Zt549G6GIHIjM7oZCT1GhopariUVPSJiHOZ0yFY";

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