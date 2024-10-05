package com.example.cooking;

public class UserProfile {

    private String name;
    private String surname;
    private String village;
    private boolean[] foodPreferences;
    private String profileImageUrl;

    public UserProfile() {
        // Default constructor required for calls to DataSnapshot.getValue(UserProfile.class)
    }

    public UserProfile(String name, String surname, String village, boolean[] foodPreferences, String profileImageUrl) {
        this.name = name;
        this.surname = surname;
        this.village = village;
        this.foodPreferences = foodPreferences;
        this.profileImageUrl = profileImageUrl;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getVillage(){
        return village;
    }
    public String getProfileImageUrl(){
        return profileImageUrl;
    }
}
