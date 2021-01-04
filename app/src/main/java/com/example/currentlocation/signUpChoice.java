package com.example.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signUpChoice extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_choice);
        Button button = findViewById(R.id.signUpButtonID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton user = findViewById(R.id.userradio);
                RadioButton volunteer = findViewById(R.id.volunteerradioid);
                if (user.isChecked()){
                    startActivity(new Intent(signUpChoice.this,signUpActivity.class));
                }
                else{
                    startActivity(new Intent(signUpChoice.this,volunteerSignUP.class));
                }
            }
        });
    }

}