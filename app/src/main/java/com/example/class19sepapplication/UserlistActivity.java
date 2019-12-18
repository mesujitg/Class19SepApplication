package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.class19sepapplication.model.User;

import java.util.List;

public class UserlistActivity extends AppCompatActivity {

    ListView listView;
    String[] test = {"a","b","c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        listView = findViewById(R.id.lvUsers);

        Intent intent = getIntent();
        final List<User> userList = (List<User>) intent.getSerializableExtra("allusers");
        String[] userNames = new String[userList.size()];

        int i =0;
        for(User user:userList){
            userNames[i] = user.getName();
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner_values,userNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(UserlistActivity.this,UserDetailActivity.class);
                intent1.putExtra("Name",userList.get(i).getName());
                intent1.putExtra("Gender",userList.get(i).getGender());
                intent1.putExtra("DoB",userList.get(i).getDob());
                intent1.putExtra("Flag",userList.get(i).getCountry());
                intent1.putExtra("Phone",userList.get(i).getPhone());
                intent1.putExtra("Email",userList.get(i).getEmail());
                startActivity(intent1);
            }
        });
    }
}
