package com.example.class19sepapplication.api;

import com.example.class19sepapplication.model.ApiUser;
import com.example.class19sepapplication.model.Task;
import com.example.class19sepapplication.model.Test;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Facebook {

    @POST("users/login")
    Call<ApiUser> userLogin(@Body ApiUser apiUser);

    @GET("tasks")
    Call<List<Test>> getTasks(@Header("Authorization") String authHeader);

    @GET("tasks")
    Call<ResponseBody> getAlltasks(@Header("Authorization") String authHeader);
}
