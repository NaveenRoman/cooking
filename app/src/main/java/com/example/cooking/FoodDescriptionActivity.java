package com.example.cooking;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.translation.TranslationResponse;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.mlkit.nl.translate.TranslateLanguage;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;


public class FoodDescriptionActivity extends AppCompatActivity {


    private TextView curryName;
    private ImageView curryImage;
    private TextView ingredientsText;
    private TextView preparationText;
    private TextView precautionText;
    private Spinner languageSpinner;
    private Button videoButton;
    private Button readButton;
    private TextToSpeech textToSpeech;

    private String selectedLanguageCode = "en"; // Default language code
    private Map<String, String[]> descriptions; // To hold descriptions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_description);

        curryName = findViewById(R.id.curry_name);
        curryImage = findViewById(R.id.curry_image);
        ingredientsText = findViewById(R.id.ingredients_text);
        preparationText = findViewById(R.id.preparation_text);
        precautionText = findViewById(R.id.precaution_text);
        languageSpinner = findViewById(R.id.language_spinner);
        videoButton = findViewById(R.id.video_button);
        readButton = findViewById(R.id.read_button);

        // Get data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("curryName");
        int image = intent.getIntExtra("curryImage", -1);
        descriptions = (Map<String, String[]>) intent.getSerializableExtra("curryDescriptions");

        curryName.setText(name);
        curryImage.setImageResource(image);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US); // Default language
            }
        });

        // Set up language spinner
        setupLanguageSpinner();

        // Set up button listeners
        videoButton.setOnClickListener(v -> {
            Intent videoIntent = new Intent(FoodDescriptionActivity.this, FoodVideoActivity.class);
            videoIntent.putExtra("curryName", name);
            startActivity(videoIntent);
        });

        readButton.setOnClickListener(v -> {
            if (descriptions != null) {
                String[] description = getDescriptionForLanguage(selectedLanguageCode);
                String textToRead = "Ingredients: " + (description.length > 0 ? description[0] : "Not available") +
                        ". Preparation: " + (description.length > 1 ? description[1] : "Not available") +
                        ". Precautions: " + (description.length > 2 ? description[2] : "Not available");
                textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                textToSpeech.speak("No details available", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        updateContent();
    }

    private void setupLanguageSpinner() {
        String[] languages = {"English", "Hindi", "Bengali", "Telugu", "Marathi", "Tamil", "Urdu", "Gujarati", "Malayalam", "Kannada"};
        String[] languageCodes = {"en", "hi", "bn", "te", "mr", "ta", "ur", "gu", "ml", "kn"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguageCode = languageCodes[position];
                updateContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void updateContent() {
        if (descriptions != null) {
            String[] description = getDescriptionForLanguage(selectedLanguageCode);

            if (description.length > 0) {
                ingredientsText.setText("Ingredients: " + description[0]);
            }
            if (description.length > 1) {
                preparationText.setText("Preparation: " + description[1]);
            }
            if (description.length > 2) {
                precautionText.setText("Precautions: " + description[2]);
            }
        } else {
            ingredientsText.setText("Ingredients: Not available");
            preparationText.setText("Preparation: Not available");
            precautionText.setText("Precautions: Not available");
        }
    }

    private String[] getDescriptionForLanguage(String languageCode) {
        return descriptions.getOrDefault(languageCode, new String[]{"Not available", "Not available", "Not available"});
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}