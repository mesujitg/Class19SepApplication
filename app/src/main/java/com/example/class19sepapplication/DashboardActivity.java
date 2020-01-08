package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.class19sepapplication.api.Facebook;
import com.example.class19sepapplication.model.Task;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    String mytoken;
    Retrofit retrofit;
    Facebook facebook;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        button = findViewById(R.id.btnTasks);

        Intent i = getIntent();
        mytoken = "Bearer "+
                i.getStringExtra("token");

        getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadTasks(mytoken);
                getTasks(mytoken);
            }
        });

    }

    private void getInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        facebook = retrofit.create(Facebook.class);
    }

    private void getTasks(String token){
        Call<List<Task>> listCall = facebook.getTasks(token);
        StrictMode();
        try {
            Response<List<Task>> listResponse = listCall.execute();
            if (listResponse.isSuccessful()){
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks(String token){
        Call<List<Task>> listCall = facebook.getTasks(token);
        listCall.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                List<Task> taskList = response.body();
                Toast.makeText(DashboardActivity.this, String.valueOf(response.code()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.d("MyEx: ", t.getMessage());
            }
        });
    }




    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy =
                new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);

    }
}
