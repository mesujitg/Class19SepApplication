package com.example.class19sepapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.class19sepapplication.R;

public class WordGameAdapter extends RecyclerView.Adapter<WordGameAdapter.WordHolder> {
    //List<Character> letterList = new ArrayList<>();
    Character[] letters ;
    TextView tv;


    public WordGameAdapter(Character[] letters,TextView textView) {
        this.letters = letters;
        this.tv = textView;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wordgame_layout,parent,false);
        WordGameAdapter.WordHolder wordHolder = new WordGameAdapter.WordHolder(view);
        return wordHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final WordHolder holder, int position) {
        char c = letters[position];
        holder.textViewL.setText(String.valueOf(c));

        holder.textViewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textViewL.setVisibility(View.INVISIBLE);
                 String ltr = holder.textViewL.getText().toString();
                 tv.append(ltr);
            }
        });
    }

    @Override
    public int getItemCount() {
        return letters.length;
    }

    public class WordHolder extends RecyclerView.ViewHolder{

        TextView textViewL;

        public WordHolder(@NonNull View itemView) {
            super(itemView);
            textViewL = itemView.findViewById(R.id.letters);
        }
    }
}
