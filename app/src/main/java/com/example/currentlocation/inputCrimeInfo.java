package com.example.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inputCrimeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_crime_info);
    }
    public void backActivity(View view)
    {
        startActivity(new Intent(inputCrimeInfo.this,emergencyButton.class));
    }
}