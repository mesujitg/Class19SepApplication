package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class WordActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    Button buttonOk,buttonClear;
    String[] words = {"apple","orange","grapes","banana"};
    int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        listView = findViewById(R.id.wordList);
        textView = findViewById(R.id.ans);
        buttonOk = findViewById(R.id.btnOk);
        buttonClear = findViewById(R.id.btnClear);

        showWord(level);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr_word = textView.getText().toString();
                if(usr_word.equals(words[level])){
                    level++;
                    showWord(level);
                    textView.setText("");
                }
                else {
                    Toast.makeText(WordActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
            }
        });

    }

    public void showWord(int i){
        Character[] word = shuffleWord(words[i]);

        final ArrayAdapter<Character> adapter =
                new ArrayAdapter<Character>(WordActivity.this,
                        R.layout.spinner_values,word);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String letter = parent.getItemAtPosition(position).toString();
                textView.append(letter);
            }
        });
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
