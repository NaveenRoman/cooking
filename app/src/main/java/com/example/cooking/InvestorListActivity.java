package com.example.cooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InvestorListActivity extends AppCompatActivity {

    private ListView investorListView;
    private UserAdapter userAdapter;
    private UserUpdate userUpdate;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_investor_list);

        investorListView = findViewById(R.id.investorListView);

        // Initialize the user list and adapter
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        investorListView.setAdapter(userAdapter);

        // Set item click listener
        investorListView.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = userList.get(position);
            Intent intent = new Intent(InvestorListActivity.this, OurProfileActivity.class);
            intent.putExtra("USER_ID", selectedUser.getId()); // Pass user ID
            startActivity(intent);
        });


        // Fetch user data from Firebase
        fetchUsersFromFirebase();


    }

    private void fetchUsersFromFirebase() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();

                String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                User currentUser = null;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String userId = snapshot.getKey();
                    String name = snapshot.child("name").getValue(String.class);
                    int imageResId = R.drawable.n; // Placeholder image

                    User user = new User(userId, name, imageResId);
                    if (userId.equals(currentUserId)) {
                        currentUser = user;
                    } else {
                        userList.add(user);
                    }
                }

                if (currentUser != null) {
                    userList.add(0, currentUser);
                }

                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(InvestorListActivity.this, "Failed to load users.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}