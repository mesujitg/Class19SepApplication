package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class WordActivity extends AppCompatActivity {

    ListView listView;
    String[] words = {"apple","orange","grapes","banana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        listView = findViewById(R.id.wordList);

        int i = 0;
        Character[] word = shuffleWord(words[i]);

        ArrayAdapter<Character> adapter =
                new ArrayAdapter<Character>(WordActivity.this,
                        R.layout.spinner_values,word);
        listView.setAdapter(adapter);
    }

    private Character[] shuffleWord(String word)
    {
        ArrayList<Character> chars = new ArrayList<>(word.length());
        for (char c : word.toCharArray())
        {
            chars.add(c);
        }
        Collections.shuffle(chars);
        Character[] shuffeled = new Character[chars.size()];
        for (int i=0;i<shuffeled.length;i++)
        {
            shuffeled[i] = chars.get(i);
        }
        //String shuffeledword = new String(shuffeled);
        return shuffeled;
    }
}
