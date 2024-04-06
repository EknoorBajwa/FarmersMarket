package com.example.farmersmarket;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int imageResourceId;
    private double price;

    public Product(String name, int imageResourceId, double price) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public double getPrice() {
        return price;
    }
}
