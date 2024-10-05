package com.example.cooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {


    private ImageView foodImage;
    private TextView foodName;
    private ListView countryListView;
    private CountryAdapter countryAdapter;
    private ArrayList<CountryItem> countryItemList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        foodImage = findViewById(R.id.food_image);
        foodName = findViewById(R.id.food_name);
        countryListView = findViewById(R.id.country_list_view);

        // Get the intent extras
        String name = getIntent().getStringExtra("foodName");
        int image = getIntent().getIntExtra("foodImage", -1);

        // Set the food details
        foodName.setText(name);
        foodImage.setImageResource(image);

        // Initialize the country item list
        countryItemList = new ArrayList<>();
        populateCountryList();

        // Set the adapter
        countryAdapter = new CountryAdapter(this, countryItemList, image, name);
        countryListView.setAdapter(countryAdapter);

        countryListView.setOnItemClickListener((parent, view, position, id) -> {
            CountryItem selectedCountry = (CountryItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(DetailActivity.this, FoodDescriptionActivity.class);
            intent.putExtra("foodName", name);
            intent.putExtra("foodImage", image);
            intent.putExtra("countryName", selectedCountry.getName());
            intent.putExtra("countryFlag", selectedCountry.getFlagResourceId());

            // Sample data for ingredients, preparation, and precautions
            String ingredients = "Ingredients: Eggs, Spices, Onion, Tomato...";
            String preparation = "Preparation: 1. Boil eggs... 2. Fry onions...";
            String precaution = "Precaution: Handle hot oil carefully...";

            intent.putExtra("foodIngredients", ingredients);
            intent.putExtra("foodPreparation", preparation);
            intent.putExtra("foodPrecaution", precaution);

            startActivity(intent);
        });
    }

    private void populateCountryList() {
        // Add country items to the list
        countryItemList.add(new CountryItem("India", R.drawable.i));
        countryItemList.add(new CountryItem("Thailand", R.drawable.t ));
        countryItemList.add(new CountryItem("Japan", R.drawable.j));
        countryItemList.add(new CountryItem("Chinese", R.drawable.chi ));
        countryItemList.add(new CountryItem("North America", R.drawable.na));
        countryItemList.add(new CountryItem("South America", R.drawable.sa));
        countryItemList.add(new CountryItem("Russia", R.drawable.r));
        countryItemList.add(new CountryItem("Australia", R.drawable.au));





        // Add more items as needed





    }
}