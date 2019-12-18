package com.example.class19sepapplication.api;

import com.example.class19sepapplication.model.Employee;
import com.example.class19sepapplication.model.Flag;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface EmpInter {

    @GET("employees")
    Call<List<Employee>> getEmployees();

    @GET("employee/{id}")
    Call<Employee> getEmployeeById(@Path("id") int id);

    @POST("create")
    Call<Void> addEmployee(@Body Employee employee);

    @PUT("update/{id}")
    Call<Void> updateEmployee(@Path("id") int id,
                              @Body Employee employee);

    @DELETE("delete/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);

    @GET("singleflag/{id}")
    Call<Flag> getFlagById(@Path("id") int id);

    @Multipart
    @POST("upload")
    Call<Flag>
    uploadFlag(@Part MultipartBody.Part img);

}
