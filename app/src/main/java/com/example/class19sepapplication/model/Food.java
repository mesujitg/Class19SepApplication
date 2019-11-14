package com.example.class19sepapplication.model;

public class Food {
    private String name;
    private String categ;
    private String rate;
    private int img;

    public Food(String name, String categ,
                String rate, int img) {
        this.name = name;
        this.categ = categ;
        this.rate = rate;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getCateg() {
        return categ;
    }

    public String getRate() {
        return rate;
    }

    public int getImg() {
        return img;
    }
}

