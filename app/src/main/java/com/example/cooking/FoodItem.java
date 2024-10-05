package com.example.cooking;

public class FoodItem {
    private String name;
    private String description;
    private int imageResourceId;
    private int varietyCount;

    public FoodItem(String name, String description, int imageResourceId, int varietyCount) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.varietyCount = varietyCount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getVarietyCount() {
        return varietyCount;
    }
}
