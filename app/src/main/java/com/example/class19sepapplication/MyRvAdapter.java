package com.example.class19sepapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.class19sepapplication.model.Food;

import java.util.ArrayList;
import java.util.List;

public class MyRvAdapter
        extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {

    private List<Food> foodList = new ArrayList<>();
    private Context context;

    public MyRvAdapter(List<Food> foodList,Context context)
    {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_food_rv,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Food food = foodList.get(position);
        holder.tvName.setText(food.getName());
        holder.tvCat.setText(food.getCateg());
        holder.tvRate.setText(food.getRate());
        holder.imageView.setImageResource(food.getImg());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, food.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName,tvCat,tvRate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.foodImg);
            tvName = itemView.findViewById(R.id.foodName);
            tvCat = itemView.findViewById(R.id.foodCat);
            tvRate = itemView.findViewById(R.id.foodRate);
        }
    }

}
