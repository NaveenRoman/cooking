package com.example.cooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CountryItem> countryList;
    private int foodImageResourceId;
    private String foodName;  // Add foodName

    public CountryAdapter(Context context, ArrayList<CountryItem> countryList, int foodImageResourceId, String foodName) {
        this.context = context;
        this.countryList = countryList;
        this.foodImageResourceId = foodImageResourceId;
        this.foodName = foodName;  // Initialize foodName
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int position) {
        return countryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        }

        CountryItem currentItem = (CountryItem) getItem(position);

        ImageView countryFlag = convertView.findViewById(R.id.country_flag);
        TextView countryName = convertView.findViewById(R.id.country_name);

        countryFlag.setImageResource(currentItem.getFlagResourceId());
        countryName.setText(currentItem.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryDetailActivity.class);
                intent.putExtra("countryName", currentItem.getName());
                intent.putExtra("countryFlag", currentItem.getFlagResourceId());
                intent.putExtra("foodImage", foodImageResourceId);
                intent.putExtra("foodName", foodName);  // Pass foodName
                context.startActivity(intent);
            }
        });

        return convertView;


    }


}
