package com.example.class19sepapplication.api;

import com.example.class19sepapplication.model.ApiUser;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UserApi {
    Facebook fb = Retro.getInstance().create(Facebook.class);
    boolean isloggedIn = false;

    public boolean userLogin(ApiUser apiUser){
        Call<ApiUser> userCall = fb.userLogin(apiUser);
        //Strict.StrictMode();
        try {
            Response<ApiUser> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                isloggedIn = true;
                Retro.token += loginResponse.body().getToken();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return isloggedIn;
    }

    public void userReg(){

    }
}
