package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.class19sepapplication.api.EmpInter;
import com.example.class19sepapplication.api.Facebook;
import com.example.class19sepapplication.api.Retro;
import com.example.class19sepapplication.api.UserApi;
import com.example.class19sepapplication.model.ApiUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiLoginActivity extends AppCompatActivity {

    EditText un,pw;
    Button login;
    Retrofit retrofit;
    Facebook facebook;
    String mytoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_login);

        un = findViewById(R.id.apiUn);
        pw = findViewById(R.id.apiPw);
        login = findViewById(R.id.apiLogin);

        getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUser user = new ApiUser(un.getText().toString(),
                        pw.getText().toString());
                //userLogin(user);
                UserApi userApi = new UserApi();
                if(userApi.userLogin(user)){
                    // go to dashboard
                    Toast.makeText(ApiLoginActivity.this,
                            Retro.token, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ApiLoginActivity.this,
                            "wrong id or password", Toast.LENGTH_SHORT).show();
                }
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

    private void userLogin(ApiUser user){
        Call<ApiUser> userCall = facebook.userLogin(user);

        userCall.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                if(response.code() == 200){
                    mytoken = response.body().getToken();
                    Intent intent = new Intent(ApiLoginActivity.this,
                            DashboardActivity.class);
                    intent.putExtra("token",mytoken);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ApiLoginActivity.this, "Wrong Id or pw", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {
                Log.d("MyEx: ",t.getMessage());
            }
        });
    }
}
