package com.android.kevng2.freestuff;

import android.graphics.drawable.Drawable;

public class Item {
    private int id;
    private String name;
    private String description;
    private String condition;
    private Drawable image;
    private String status;
    private double lat;
    private double lng;

    public Item(int id, String name, String description, String condition,
                Drawable image, String status, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.image = image;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getDescription() {
        return description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getCondition() {
        return condition;
    }

//    public void setCondition(String condition) {
//        this.condition = condition;
//    }

    public Drawable getImage() {
        return image;
    }

//    public void setImageFileName(String imageFileName) {
//        this.imageFileName = imageFileName;
//    }

    public String getStatus() {
        return status;
    }

//    public void setStatus(String status) {
//        this.status = status;
//    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
