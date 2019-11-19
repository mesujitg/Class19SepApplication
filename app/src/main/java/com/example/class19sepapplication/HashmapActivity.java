package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class HashmapActivity extends AppCompatActivity {

    ListView listView;
    HashMap<String, String> myhm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashmap);

        listView = findViewById(R.id.wordsList);
        myhm.put("hero", "actor");
        myhm.put("cameraman", "holds camera");
        myhm.put("Lightsman", "holds lights");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_values, new ArrayList<String>(myhm.keySet()));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String key = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HashmapActivity.this,myhm.get(key), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
