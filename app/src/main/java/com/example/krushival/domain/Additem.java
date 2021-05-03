package com.example.krushival.domain;

public class Additem {
    String img_url, name, description, type;
    int rating;
    double price;

    Additem() {
    }

    public String getType() {
        return type;
    }

    public static void setType(String type) {
        type = type;
    }

    public Additem(String img_url, String name, double price, String description, int rating, String type) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
