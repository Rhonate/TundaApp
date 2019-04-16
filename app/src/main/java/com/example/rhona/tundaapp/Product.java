package com.example.rhona.tundaapp;

/**
 * Created by Rhona on 1/11/2019.
 */
public class Product {

    private String name, price;
    private String category;
    private String description;
    private String thumbnail;

    public Product() {


    }

    public Product(String name, String price, String category, String description, String thumbnail) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
