package com.rm.myapp.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class AppConfig {
    private static String BASE_URL = "https://chatwithsupport.us/tracker/";
    public static String Key = "9787svdsdca";
    public static String Token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDYzODg0MDYsImNoayI6NjEwNTU0MDM2LCJzdGFydCI6MTYwNjM4ODQwNiwiZXhwaXJlIjoxNjA2Mzk1NjA2LCJwaG9uZSI6Ijk4NzY1NDMyMTAiLCJpZCI6IjEiLCJyb2xlIjoiMiIsInN0YXR1cyI6IjEifQ.tNwXPFY_cNm-YuL1D4Ieiqj0XCkLlt-WD7IEjFd0K-0";
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