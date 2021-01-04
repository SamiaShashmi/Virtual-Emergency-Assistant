package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class volunteerProfile extends AppCompatActivity {

    TextInputLayout fullName, phoneNumber, email, dob, gender, userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullName = findViewById(R.id.fullNamep);
        phoneNumber = findViewById(R.id.phoneNop);
        email = findViewById(R.id.emailp);
        dob = findViewById(R.id.dobp);
        gender = findViewById(R.id.genp);
        userName = findViewById(R.id.uNamep);
        showProfile();
    }
    public void backActivity(View view)
    {
        startActivity(new Intent(volunteerProfile.this,emergencyButton.class));
    }
    public void showProfile()
    {
        String username = getIntent().getStringExtra("username");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = reference.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String nameFromDB = snapshot.child(username).child("fullname").getValue(String.class);
                    String phoneNoFromDB = snapshot.child(username).child("mobilenumber").getValue(String.class);
                    String emailFromDB = snapshot.child(username).child("email2").getValue(String.class);
                    String dobFromDB = snapshot.child(username).child("dateofbirth").getValue(String.class);
                    String genFromDB = snapshot.child(username).child("gender").getValue(String.class);

                    fullName.getEditText().setText(nameFromDB);
                    phoneNumber.getEditText().setText(phoneNoFromDB);
                    email.getEditText().setText(emailFromDB);
                    dob.getEditText().setText(dobFromDB);
                    gender.getEditText().setText(genFromDB);
                    userName.getEditText().setText(username);
                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}