package com.example.rhona.tundaapp;

/**
 * Created by Rhona on 1/18/2019.
 */
public class SellerPdt {

    private String Name;
    private String category;
    private String Description;
    private int Thumbnail;

    public SellerPdt() {

    }

    public SellerPdt(String name, String category, String description, int thumbnail) {
        Name = name;
        this.category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

    public String getName() {
        return Name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
