package com.example.cooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FoodItem> foodList;

    public FoodAdapter(Context context, ArrayList<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        }

        FoodItem currentItem = (FoodItem) getItem(position);

        ImageView foodImage = convertView.findViewById(R.id.food_image);
        TextView foodName = convertView.findViewById(R.id.food_name);
        TextView foodDescription = convertView.findViewById(R.id.food_description);
        TextView varietyCount = convertView.findViewById(R.id.variety_count);


        foodImage.setImageResource(currentItem.getImageResourceId());
        foodName.setText(currentItem.getName());
        foodDescription.setText(currentItem.getDescription());
        varietyCount.setText(String.valueOf(currentItem.getVarietyCount()) + " varieties");


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("foodName", currentItem.getName());
                intent.putExtra("foodImage", currentItem.getImageResourceId());
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}