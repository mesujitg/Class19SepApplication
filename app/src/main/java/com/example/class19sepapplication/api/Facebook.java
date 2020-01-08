package com.example.class19sepapplication.api;

import com.example.class19sepapplication.model.ApiUser;
import com.example.class19sepapplication.model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Facebook {

    @POST("users/login")
    Call<ApiUser> userLogin(@Body ApiUser apiUser);

    @GET("tasks")
    Call<List<Task>> getTasks(@Header("Authorization") String authHeader);
}
