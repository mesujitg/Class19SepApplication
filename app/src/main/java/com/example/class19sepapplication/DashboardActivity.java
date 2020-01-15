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
import com.example.class19sepapplication.api.Retro;
import com.example.class19sepapplication.model.Task;
import com.example.class19sepapplication.model.Test;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
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
//        mytoken = "Bearer "+
//                i.getStringExtra("token");

        getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTasks(Retro.token);
                //getTasks(mytoken);
            }
        });

    }

    private void getInstance(){
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:3000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        facebook = Retro.getInstance().create(Facebook.class);
    }

    private void getTasks(String token){
        //Call<List<Task>> listCall = facebook.getTasks(token);
//        StrictMode();
//        try {
//            Response<List<Task>> listResponse = listCall.execute();
//            if (listResponse.isSuccessful()){
//                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            //e.printStackTrace();
//            Log.d("MyEx: ", e.getMessage());
//        }
    }

    private void loadTasks(String token){
        Call<List<Test>> listCall = facebook.getTasks(token);
        listCall.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                List<Test> tests = response.body();
                Toast.makeText(DashboardActivity.this,
                        String.valueOf(response.code()) +"-"+
                        String.valueOf(tests.size()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {
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
