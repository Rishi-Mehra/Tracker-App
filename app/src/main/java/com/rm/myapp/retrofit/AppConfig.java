package com.rm.myapp.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class AppConfig {
    private static String BASE_URL = "https://chatwithsupport.us/tracker/User/";
    public static String Key = "9787svdsdca";
    public static String Token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDYyOTM5OTQsImNoayI6MTEzNzk2MzcxOSwic3RhcnQiOjE2MDYyOTM5OTQsImV4cGlyZSI6MTYwNjMwMTE5NCwicGhvbmUiOiI5ODc2NTQzMjEwIiwiaWQiOiIxIiwicm9sZSI6IjIiLCJzdGF0dXMiOiIxIn0.PJqRMRoRZ_MzEhU_IhffRVI4XX2BLbhk2uhi8h0EMMI";


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