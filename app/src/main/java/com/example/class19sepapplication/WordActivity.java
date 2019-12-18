package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class19sepapplication.adapter.WordGameAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListView listView;
    TextView textView,tvLvl;
    Button buttonOk,buttonClear;
    String[] words = {"apple","orange","grapes","banana"};
    List<String> allwords = new ArrayList<>();
    int level = 0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        recyclerView = findViewById(R.id.rvWord);
        //listView = findViewById(R.id.wordList);
        textView = findViewById(R.id.ans);
        tvLvl = findViewById(R.id.lvlTxt);
        //buttonOk = findViewById(R.id.btnOk);
        buttonClear = findViewById(R.id.btnClear);

        sp = getSharedPreferences("wg_level",MODE_PRIVATE);
        editor = sp.edit();

        loadWords();

        level = sp.getInt("lvl",0);

        tvLvl.setText("Level: "+ (level+1));
        //showWord(level);
        showWordrv(level);

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String usr_word = textView.getText().toString();
                if(usr_word.length() == allwords.get(level).length())
                {
                    if(usr_word.equals(allwords.get(level).toUpperCase())){
                        Toast.makeText(WordActivity.this, "Correct: "+ allwords.get(level).toUpperCase(), Toast.LENGTH_LONG).show();
                        level++;
                        tvLvl.setText("Level: "+ (level+1));
                        showWordrv(level);
                        textView.setText("");
                        editor.putInt("lvl",level);
                        editor.commit();
                     }
                    else {
                        Toast.makeText(WordActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                        showWordrv(level);
                        textView.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        buttonOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String usr_word = textView.getText().toString();
//                if(usr_word.equals(allwords.get(level).toUpperCase())){
//                    level++;
//                    //showWord(level);
//                    showWordrv(level);
//                    textView.setText("");
//                    editor.putInt("lvl",level);
//                    editor.commit();
//                }
//                else {
//                    Toast.makeText(WordActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
//                    showWordrv(level);
//                    textView.setText("");
//                }
//            }
//        });
//
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                showWordrv(level);
            }
        });

    }

    public void showWord(int i){
        Character[] word = shuffleWord(allwords.get(i));

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

    public void showWordrv(int i){
        Character[] word = shuffleWord(allwords.get(i).toUpperCase());

        WordGameAdapter adapter = new WordGameAdapter(word,textView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);
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

    public void loadWords(){
        try {
            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.words);

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while((text = br.readLine()) != null){
                allwords.add(text);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
