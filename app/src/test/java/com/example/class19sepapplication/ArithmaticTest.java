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

    @Test
    public void testLogin(){
        UserApi ua = new UserApi();
        ApiUser usr = new ApiUser("sujit2", "sujit2");
        //remove strict mode from method while testing
        assertTrue(ua.userLogin(usr));
    }

    @Test
    public void testLogini(){
        Facebook fb = Retro.getInstance()
                .create(Facebook.class);
        Call<ApiUser> userCall =
                fb.userLogin(new ApiUser("sujit2",
                "sujit2"));
        try {
            Response<ApiUser> response = userCall.execute();
            assertTrue(response.isSuccessful());
//            int ac = response.code();
//            int ex = 200;
//            assertEquals(ex,ac);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
