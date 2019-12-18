package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.class19sepapplication.adapter.StudentAdapter;
import com.example.class19sepapplication.database.DbHelper;
import com.example.class19sepapplication.model.Student;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    EditText editTextId,editTextN,editTextE,editTextP;
    Button buttonAdd,buttonUpdate;
    RecyclerView recyclerView;
    DbHelper dbHelper;
    List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        editTextId = findViewById(R.id.stId);
        editTextN = findViewById(R.id.stName);
        editTextE = findViewById(R.id.stEmail);
        editTextP = findViewById(R.id.stPhone);
        buttonAdd = findViewById(R.id.stAdd);
        buttonUpdate = findViewById(R.id.stUpdate);
        recyclerView = findViewById(R.id.rvDbStudents);

        dbHelper = new DbHelper(this);

        getStudents();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = editTextN.getText().toString();
                String e = editTextE.getText().toString();
                String p = editTextP.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.addStudent(student))
                {
                    Toast.makeText(DatabaseActivity.this,
                            "Student Added", Toast.LENGTH_SHORT).show();
                    editTextN.getText().clear();
                    editTextE.getText().clear();
                    editTextP.getText().clear();
                }
                getStudents();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(editTextId.getText().toString());
                String n = editTextN.getText().toString();
                String e = editTextE.getText().toString();
                String p = editTextP.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.updateStudentcv(student,id))
                {
                    Toast.makeText(DatabaseActivity.this,
                            "Student Updated", Toast.LENGTH_SHORT).show();
                    editTextN.getText().clear();
                    editTextE.getText().clear();
                    editTextP.getText().clear();
                    editTextId.getText().clear();
                }
                getStudents();
            }
        });
    }

    private void getStudents(){
        students = dbHelper.getStudents();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter adapter = new StudentAdapter(students,this);
        adapter.setTvN(editTextN);
        adapter.setTvE(editTextE);
        adapter.setTvP(editTextP);
        adapter.setTvId(editTextId);
        recyclerView.setAdapter(adapter);
    }
}
