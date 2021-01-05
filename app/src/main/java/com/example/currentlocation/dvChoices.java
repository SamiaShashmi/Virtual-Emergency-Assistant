package com.example.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class dvChoices extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dv_choices);
        Button button = findViewById(R.id.dvButtonID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton map = findViewById(R.id.mapradio);
                RadioButton graph = findViewById(R.id.graphradio);
                if (map.isChecked()){
                    startActivity(new Intent(dvChoices.this,MapsActivity.class));
                }
                else{
                    startActivity(new Intent(dvChoices.this,barChart.class));
                }
            }
        });
    }

}