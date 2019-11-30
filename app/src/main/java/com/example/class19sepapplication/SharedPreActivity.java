package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreActivity extends AppCompatActivity {

    EditText editTextUn,editTextPw;
    CheckBox checkBoxRem;
    Button buttonLogin;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String un,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pre);

        editTextUn = findViewById(R.id.etUsername);
        editTextPw = findViewById(R.id.etPassword);
        checkBoxRem = findViewById(R.id.cbRemember);
        buttonLogin = findViewById(R.id.btnLogin);

        sp = getApplicationContext().getSharedPreferences("mysp",MODE_PRIVATE);
        editor = sp.edit();

        un = sp.getString("un","");
        pw = sp.getString("pw","");

        if(un.equals("admin") && pw.equals("admin")){
            Intent intent = new Intent(SharedPreActivity.this,DashboardActivity.class);
            startActivity(intent);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un = editTextUn.getText().toString();
                pw = editTextPw.getText().toString();

                if(un.equals("admin") && pw.equals("admin")){

                    if(checkBoxRem.isChecked()){
                        editor.putString("un",editTextUn.getText().toString());
                        editor.putString("pw",editTextPw.getText().toString());
                        editor.commit();
                    }

                    Intent intent = new Intent(SharedPreActivity.this,DashboardActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(SharedPreActivity.this, "Wrong id or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
