package com.example.class19sepapplication.model;

public class ApiUser {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String token;
    private String status;

    public ApiUser(String firstname, String lastname, String username, String password, String token, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.token = token;
        this.status = status;
    }

    public ApiUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getStatus() {
        return status;
    }
}
