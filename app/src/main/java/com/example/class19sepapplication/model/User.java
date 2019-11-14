package com.example.class19sepapplication.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String gender;
    private String dob;
    private String country;
    private String phone;
    private String email;

    public User(String name, String gender, String dob, String country, String phone, String email) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
