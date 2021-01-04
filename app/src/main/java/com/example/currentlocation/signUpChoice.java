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
                radioGroup = (RadioGroup) findViewById(R.id.usertyperadio);
                int selected = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selected);
                /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch(checkedId){
                            case R.id.userradio:
                                // do operations specific to this selection
                                startActivity(new Intent(signUpChoice.this,signUpActivity.class));
                                break;
                            case R.id.volunteerradio:
                                // do operations specific to this selection
                                startActivity(new Intent(signUpChoice.this,volunteerSignUP.class));
                                break;

                        }
                    }
                });*/
                if(radioButton.getText() == "User")
                {
                    startActivity(new Intent(signUpChoice.this, signUpActivity.class));
                }
                else
                {
                    //startActivity(new Intent(signUpChoice.this, volunteerSignUP.class));
                    Toast.makeText(signUpChoice.this,
                            radioButton.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}