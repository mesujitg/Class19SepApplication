package com.example.class19sepapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.class19sepapplication.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.TBL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addStudent(Student student){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO students (name,email,phone) VALUES ('"+student.getName()+"','"+student.getEmail()+"','"+student.getPhone()+"')");
            return true;
        }
        catch (Exception e){
            Log.d("DbEx: ",e.toString());
            return false;
        }
    }

    public List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM students",null);

            if(cursor != null){
                while(cursor.moveToNext()){
                    studentList.add(new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                }
            }
        }
        catch (Exception e){
            Log.d("DbEx: ",e.toString());
        }
        return studentList;
    }

    public long addStudenti(Student student){
        try {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",student.getName());
            values.put("email",student.getEmail());
            values.put("phone",student.getPhone());
            long id = database.insert("students",null,values);
            return id;
        }
        catch (Exception e){
            Log.d("DbEx", e.toString());
            return -1;
        }
    }

}
