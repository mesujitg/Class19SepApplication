package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileActivity extends AppCompatActivity {

    EditText editText;
    Button buttonSave, buttonLoad;
    HashMap<String,String> myhm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        editText = findViewById(R.id.fileContent);
        buttonSave = findViewById(R.id.btnSave);
        buttonLoad = findViewById(R.id.btnLoad);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                try{
                    FileOutputStream fos = openFileOutput
                            ("myfile.txt",MODE_PRIVATE);
                    fos.write(data.getBytes());
                    editText.getText().clear();
                    Toast.makeText(FileActivity.this,
                            "Saved to"+ getFilesDir(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d("Exc",e.toString());
                }
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fis =
                            openFileInput("myfile.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    String data;
                    String alldata = "";
                    while((data = br.readLine()) != null){
                        alldata += data + "\n";
//                        String[] wm = data.split("=");
//                        myhm.put(wm[0],wm[1]);
                    }
                    editText.setText(alldata);
                }
                catch (Exception e){
                    Log.d("Exc",e.toString());
                }
            }
        });
    }
}
