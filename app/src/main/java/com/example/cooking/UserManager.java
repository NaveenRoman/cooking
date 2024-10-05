package com.example.cooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserManager{
    private static UserManager instance;
    private User currentUser;
    private ArrayList<User> userList; // List of all users

    private UserManager() {
        userList = new ArrayList<>();
        // Initialize with some users if needed
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Method to get the current logged-in user
    public User getCurrentUser() {
        return currentUser;
    }

    // Method to set the current logged-in user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Method to get the list of all users
    public ArrayList<User> getUserList() {
        return userList;
    }

    // Method to add a user to the list
    public void addUser(User user) {
        userList.add(user);
    }
}
