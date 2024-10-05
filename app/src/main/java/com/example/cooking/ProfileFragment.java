package com.example.cooking;



import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileFragment extends Fragment {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView signupText;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        signupText = view.findViewById(R.id.signupText);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        loginButton.setOnClickListener(v -> {
            String usernameInput = username.getText().toString();
            String passwordInput = password.getText().toString();
            loginUser(usernameInput, passwordInput);
        });

        signupText.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileDetailsActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void loginUser(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getActivity(), OurProfileActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Login Failed! Check username or password.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is already authenticated
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // If authenticated, navigate to OurProfileActivity
            Intent intent = new Intent(getActivity(), OurProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            // Optionally finish the current activity/fragment
            getActivity().finish();
        }
    }
}