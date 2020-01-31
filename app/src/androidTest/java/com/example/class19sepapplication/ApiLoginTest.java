package com.example.class19sepapplication;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(JUnit4.class)
public class ApiLoginTest {
    @Rule
    public ActivityTestRule<ApiLoginActivity> rule =
            new ActivityTestRule<>(ApiLoginActivity.class);
    String un = "sujit2";
    String pw = "sujit2";


    @Test
    public void testApiLogin(){
        onView(withId(R.id.apiUn)).perform(typeText(un));
        onView(withId(R.id.apiPw)).perform(typeText(pw));
        closeSoftKeyboard();
        onView(withId(R.id.apiLogin)).perform(click());
        onView(withId(R.id.tvDashboard))
                .check(matches(isDisplayed()));
    }
}
