package com.example.cooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private ListView listView;
    private FoodAdapter foodAdapter;
    private ArrayList<FoodItem> foodItemList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.list_view);

        // Initialize the food item list
        foodItemList = new ArrayList<>();
        populateFoodList();

        // Set the adapter
        foodAdapter = new FoodAdapter(getContext(), foodItemList);
        listView.setAdapter(foodAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodItem clickedItem = foodItemList.get(position);

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("foodName", clickedItem.getName());
                intent.putExtra("foodImage", clickedItem.getImageResourceId());
                startActivity(intent);
            }
        });

        return view;
    }

    private void populateFoodList() {
        // Add food items to the list
        foodItemList.add(new FoodItem("Egg Curry", "Various types of egg curry", R.drawable.e, 50));
        foodItemList.add(new FoodItem("Chicken Curry", "Various types of chicken curry", R.drawable.ch, 100));
        foodItemList.add(new FoodItem("Vegetable Curry", "Various types of vegetable curry", R.drawable.ve, 75));
        foodItemList.add(new FoodItem("Fish curry", "Various type of fish curry", R.drawable.f, 35));
        foodItemList.add(new FoodItem("Brinjal curry", "Various type of Brinjal curry", R.drawable.b, 45));
        foodItemList.add(new FoodItem("Lady's Fingere curry", "Various type of Lady's Finger curry", R.drawable.la, 35));
        foodItemList.add(new FoodItem("Potato curry", "Various type of Potato curry", R.drawable.p, 35));
        foodItemList.add(new FoodItem("Beans curry", "Various type of Beans curry", R.drawable.be, 35));
        foodItemList.add(new FoodItem("Mutton curry", "Various type of Mutton curry", R.drawable.mu, 35));
        foodItemList.add(new FoodItem("Crabs curry", "Various type of Crabs curry", R.drawable.cr, 35));
        foodItemList.add(new FoodItem("pig curry", "Various type of Pig curry", R.drawable.pi, 35));

        // Add more items as needed
    }
}