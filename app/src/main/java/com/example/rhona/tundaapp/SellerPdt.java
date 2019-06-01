package com.example.rhona.tundaapp;

/**
 * Created by Rhona on 1/18/2019.
 */
public class SellerPdt {

    private String Name;
    private String category;
    private String Description;
    private int Thumbnail;
    private String phone;

    public SellerPdt() {

    }

    public SellerPdt(String name, String category, String description, int thumbnail, String phone) {
        Name = name;
        this.category = category;
        this.phone = phone;
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

    public String getPhone (String phone) {
        return phone;
    }

    public void setPhone (String phone) {
        phone = phone;
    }
}
