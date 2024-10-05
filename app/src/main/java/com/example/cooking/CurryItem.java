package com.example.cooking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CurryItem implements Serializable {
    private String name;
    private int imageResourceId;
    private Map<String, String[]> descriptions; // Map languageCode to description array

    public CurryItem(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.descriptions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // Method to add descriptions
    public void addDescription(String languageCode, String[] description) {
        descriptions.put(languageCode, description);
    }

    public Map<String, String[]> getDescriptions() {
        return descriptions;
    }

    public String[] getDescription(String languageCode) {
        return descriptions.getOrDefault(languageCode, new String[]{"Not available", "Not available", "Not available"});
    }
}