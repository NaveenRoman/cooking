package com.example.cooking;

public class CountryItem {

    private String name;
    private int flagResourceId;

    public CountryItem(String name, int flagResourceId) {
        this.name = name;
        this.flagResourceId = flagResourceId;
    }

    public String getName() {
        return name;
    }

    public int getFlagResourceId() {
        return flagResourceId;
    }
}
