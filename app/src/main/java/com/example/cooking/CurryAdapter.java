package com.example.cooking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CurryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CurryItem> curryList;

    public CurryAdapter(Context context, ArrayList<CurryItem> curryList) {
        this.context = context;
        this.curryList = curryList;
    }

    @Override
    public int getCount() {
        return curryList.size();
    }

    @Override
    public Object getItem(int position) {
        return curryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_curry, parent, false);
        }

        CurryItem currentItem = (CurryItem) getItem(position);

        ImageView curryImage = convertView.findViewById(R.id.curry_image);
        TextView curryName = convertView.findViewById(R.id.curry_name);

        curryImage.setImageResource(currentItem.getImageResourceId());
        curryName.setText(currentItem.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDescriptionActivity.class);
                intent.putExtra("curryName", currentItem.getName());
                intent.putExtra("curryImage", currentItem.getImageResourceId());

                // Pass descriptions as a serializable map
                intent.putExtra("curryDescriptions", (Serializable) currentItem.getDescriptions());

                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
