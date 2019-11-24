package com.example.class19sepapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ToobarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toobar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean
    onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuOuter){
            Toast.makeText(this, "MenuOuter",
                    Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.menuFirst){
            Toast.makeText(this, "First",
                    Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.sub1){
            Toast.makeText(this, "Sub menu 1",
                    Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.sub2){
            Toast.makeText(this, "Sub Menu 2",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
