package com.android.kevng2.freestuff;

public class Item {

    int image;
    String itemName;
    String description;
    String Condition;
    String status;

    public Item() {

    }

    public Item(int image, String itemName, String description, String condition, String status) {
        this.image = image;
        this.itemName = itemName;
        this.description = description;
        Condition = condition;
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
