package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.class19sepapplication.adapter.MyRvAdapter;
import com.example.class19sepapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class RvActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        recyclerView = findViewById(R.id.rvFoods);

        getData();
        MyRvAdapter adapter = new MyRvAdapter(foods,this);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        foods.add(new Food("Mo:Mo","Chicken","Rs. 150",
                R.drawable.momo));
        foods.add(new Food("Chowmein","Chicken","Rs. 130",
                R.drawable.chowmein));
        foods.add(new Food("Burger","Chicken","Rs. 180",
                R.drawable.burger));
    }
}
