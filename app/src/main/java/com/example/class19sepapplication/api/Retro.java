package com.example.class19sepapplication.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    private static final String BASE_URL = "http://10.0.2.2:3000/";
//    for testing
//    private static final String BASE_URL = "http://192.168.56.1:3000/";
    private static final String IMG_URL = "http://10.0.2.2:3000/images/";
    public static String token = "Bearer ";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
