package com.example.class19sepapplication;

import android.util.Log;

import com.example.class19sepapplication.api.Facebook;
import com.example.class19sepapplication.api.Retro;
import com.example.class19sepapplication.api.UserApi;
import com.example.class19sepapplication.model.ApiUser;
import com.example.class19sepapplication.model.Arithmatic;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ArithmaticTest {

    @Test
    public void testAdd(){
        Arithmatic a = new Arithmatic();
        double actual_result =
                a.add(10,15);
        double expected_result = 25;
        assertEquals(expected_result,
                actual_result);
    }

    @Test
    public void testMulti(){
        Arithmatic a = new Arithmatic();
        double actual_result =
                a.multiply(10,15);
        double expected_result = 150;
        assertEquals(expected_result,
                actual_result);
    }

//    @Test
//    public void testLogin(){
//        UserApi ua = new UserApi();
//        boolean actual = ua.userLogin(new
//                ApiUser("sujit2",
//                "sujit2"));
//        boolean expected = true;
//        assertEquals(expected,actual);
//    }

    @Test
    public void testLogini(){
        Facebook fb = Retro.getInstance()
                .create(Facebook.class);
        Call<ApiUser> userCall =
                fb.userLogin(new ApiUser("sujit2",
                "asdf"));
        try {
            Response<ApiUser> response = userCall.execute();
            int ac = response.code();
            int ex = 200;
//            assertTrue(response.isSuccessful());
            assertEquals(ex,ac);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
