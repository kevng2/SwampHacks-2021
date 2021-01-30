package com.android.kevng2.freestuff;

public class Item {
    private String name;
    private String description;
    private String condition;
    private String imageFileName;
    private String status;

    public Item(String name, String description, String condition,
                String imageFileName, String status) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.imageFileName = imageFileName;
        this.status = status;
    }

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

    public String getImageFileName() {
        return imageFileName;
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
}
