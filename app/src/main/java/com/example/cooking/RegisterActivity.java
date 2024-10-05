package com.example.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private Button registerGoogle;
    private Button registerEmail;
    private Button registerCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);

        registerGoogle = findViewById(R.id.register_google);
        registerEmail = findViewById(R.id.register_email);
        registerCustom = findViewById(R.id.register_custom);

        registerGoogle.setOnClickListener(v -> {
            // Implement Google Sign-In
            Intent intent = new Intent(RegisterActivity.this, GoogleSignInActivity.class);
            startActivity(intent);
        });

        registerEmail.setOnClickListener(v -> {
            // Implement Email Registration
            Intent intent = new Intent(RegisterActivity.this, EmailRegistrationActivity.class);
            startActivity(intent);
        });

        registerCustom.setOnClickListener(v -> {
            // Implement Custom Registration
            Intent intent = new Intent(RegisterActivity.this, CustomRegistrationActivity.class);
            startActivity(intent);
        });

    }
}