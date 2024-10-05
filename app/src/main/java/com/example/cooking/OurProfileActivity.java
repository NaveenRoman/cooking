package com.example.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OurProfileActivity extends AppCompatActivity {


    private ImageView profileImageView;
    private TextView nameTextView;
    private TextView description1TextView;
    private TextView description2TextView;
    private TextView description3TextView;
    private TextView description4TextView;
    private TextView description5TextView;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Button editProfileButton;
    private Button postImageButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_our_profile);


        profileImageView = findViewById(R.id.profileImageView);
        nameTextView = findViewById(R.id.nameTextView);
        description1TextView = findViewById(R.id.description1TextView);
        description2TextView = findViewById(R.id.description2TextView);
        description3TextView = findViewById(R.id.description3TextView);
        description4TextView = findViewById(R.id.description4TextView);
        description5TextView = findViewById(R.id.description5TextView);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        editProfileButton = findViewById(R.id.editProfileButton);
        postImageButton = findViewById(R.id.postImageButton);
        logoutButton = findViewById(R.id.logoutButton);

        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(OurProfileActivity.this, ProfileDetailsActivity.class);
            startActivity(intent);
        });

        postImageButton.setOnClickListener(v -> {
            // Handle image posting
        });

        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(OurProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        loadUserProfile();
    }
    private void loadUserProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            mDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String name = snapshot.child("name").getValue(String.class);
                        String description1 = snapshot.child("description1").getValue(String.class);
                        String description2 = snapshot.child("description2").getValue(String.class);
                        String description3 = snapshot.child("description3").getValue(String.class);
                        String description4 = snapshot.child("description4").getValue(String.class);
                        String description5 = snapshot.child("description5").getValue(String.class);
                        String profileImageUrl = snapshot.child("profileImageUrl").getValue(String.class);

                        nameTextView.setText(name);
                        description1TextView.setText(description1);
                        description2TextView.setText(description2);
                        description3TextView.setText(description3);
                        description4TextView.setText(description4);
                        description5TextView.setText(description5);

                        // Load profile image
                        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
                            Glide.with(OurProfileActivity.this)
                                    .load(profileImageUrl)
                                    .error(R.drawable.n) // Fallback image
                                    .into(profileImageView);
                        } else {
                            profileImageView.setImageResource(R.drawable.n); // Default image
                        }
                    } else {
                        Toast.makeText(OurProfileActivity.this, "Profile not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(OurProfileActivity.this, "Failed to load profile: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "User not authenticated.", Toast.LENGTH_SHORT).show();
        }
    }
}