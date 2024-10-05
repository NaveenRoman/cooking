package com.example.cooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.item_user, parent, false);
        }

        User user = userList.get(position);

        ImageView userImageView = convertView.findViewById(R.id.user_image);
        TextView userNameTextView = convertView.findViewById(R.id.user_name);
        Button followButton = convertView.findViewById(R.id.follow_button);

        // Set user name
        userNameTextView.setText(user.getName());

        // Set user image (optional: use an image loading library if needed)
        // userImageView.setImageResource(R.drawable.placeholder); // Replace with actual image handling

        // Handle follow button click
        followButton.setOnClickListener(v -> {
            // Implement follow functionality here
        });

        return convertView;
    }
}