package com.example.cooking;

import java.io.Serializable;

public class User implements Serializable {
    private String id; // Unique identifier for the user
    private String name;
    private int imageResourceId;
    private boolean isFollowing;

    public User(String id, String name, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.isFollowing = false; // Default value
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }
}
