package com.example.cooking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfileDetailsActivity extends AppCompatActivity {


    private static final int PICK_IMAGE = 100;
    private ImageView profileImageView;
    private EditText nameEditText;
    private EditText description1EditText;
    private EditText description2EditText;
    private EditText description3EditText;
    private EditText description4EditText;
    private EditText description5EditText;
    private EditText emailEditText; // Email field added
    private EditText passwordEditText;
    private Button saveButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_details);

        profileImageView = findViewById(R.id.profileImageView);
        nameEditText = findViewById(R.id.nameEditText);
        description1EditText = findViewById(R.id.description1EditText);
        description2EditText = findViewById(R.id.description2EditText);
        description3EditText = findViewById(R.id.description3EditText);
        description4EditText = findViewById(R.id.description4EditText);
        description5EditText = findViewById(R.id.description5EditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        saveButton = findViewById(R.id.saveButton);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        profileImageView.setOnClickListener(v -> openGallery());

        saveButton.setOnClickListener(v -> saveProfileDetails());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                profileImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProfileDetails() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String description1 = description1EditText.getText().toString();
        String description2 = description2EditText.getText().toString();
        String description3 = description3EditText.getText().toString();
        String description4 = description4EditText.getText().toString();
        String description5 = description5EditText.getText().toString();

        // Get the image from the ImageView
        profileImageView.setDrawingCacheEnabled(true);
        profileImageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) profileImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        // Upload image to Firebase Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileImageRef = storageReference.child("profile_images/" + email);

        profileImageRef.putBytes(data).addOnSuccessListener(taskSnapshot -> {
            profileImageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();
                createUserInFirebase(email, password, name, description1, description2, description3, description4, description5, imageUrl);
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Failed to get image URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void createUserInFirebase(String email, String password, String name, String description1, String description2, String description3, String description4, String description5, String imageUrl) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            mDatabase.child(userId).child("name").setValue(name);
                            mDatabase.child(userId).child("description1").setValue(description1);
                            mDatabase.child(userId).child("description2").setValue(description2);
                            mDatabase.child(userId).child("description3").setValue(description3);
                            mDatabase.child(userId).child("description4").setValue(description4);
                            mDatabase.child(userId).child("description5").setValue(description5);
                            mDatabase.child(userId).child("profileImageUrl").setValue(imageUrl);

                            // Navigate to OurProfileActivity
                            Intent intent = new Intent(this, OurProfileActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}