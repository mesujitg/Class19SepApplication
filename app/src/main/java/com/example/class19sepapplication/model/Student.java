package com.example.class19sepapplication.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int img;

    public static final String TBL_CREATE =
            "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,phone TEXT)";

    public Student(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
