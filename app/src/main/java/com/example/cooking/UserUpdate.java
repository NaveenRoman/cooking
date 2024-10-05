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

public class UserUpdate  extends BaseAdapter {

    private Context context;
    private ArrayList<User> userList;

    public UserUpdate(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        }

        User currentUser = (User) getItem(position);

        ImageView userImage = convertView.findViewById(R.id.user_image);
        TextView userName = convertView.findViewById(R.id.user_name);
        Button followButton = convertView.findViewById(R.id.follow_button);

        userImage.setImageResource(currentUser.getImageResourceId());
        userName.setText(currentUser.getName());
        followButton.setText(currentUser.isFollowing() ? "Unfollow" : "Follow");

        followButton.setOnClickListener(v -> {
            currentUser.setFollowing(!currentUser.isFollowing());
            notifyDataSetChanged();
        });

        return convertView;
    }
}
