package com.example.cooking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodVideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_video);

        videoView = findViewById(R.id.video_view);

        // Get the intent extras
        String curryName = getIntent().getStringExtra("curryName");

        // Set video URI based on curry name
        int videoResId = getVideoResId(curryName);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + videoResId);
        videoView.start();
    }

    private int getVideoResId(String curryName) {
        switch (curryName) {
            case "Egg Masala":
                return R.raw.cc;
            case "Anda Curry":
                return R.raw.eg;
            case "Thai Egg Curry":
                return R.raw.jcc;
            case "Japanese Egg Curry":
                return R.raw.m;
            // Add cases for other curries as needed
            default:
                return R.raw.tc; // Provide a default video or handle accordingly
        }

    }
}