package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class volunteer_menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextInputLayout fullName, phoneNumber, email, dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_menu);
        drawLayout = (DrawerLayout) findViewById(R.id.volunteer_drawer);
        navigationView = findViewById(R.id.volunteer_navV);
        //toolbar = findViewById(R.id.hamburger);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawLayout, R.string.open, R.string.close);
        drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String username = getIntent().getStringExtra("username");
        switch (item.getItemId()) {
            case R.id.volunteer_dataView:
                Intent intent = new Intent(volunteer_menu.this, dvChoices.class);
                startActivity(intent);
                break;
            case R.id.volunteer_profile:
                Intent intent1 = new Intent(volunteer_menu.this,volunteerProfile.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
                break;
            case R.id.volunteer_logout:
                Intent intent2 = new Intent(volunteer_menu.this,MainActivity.class);
               startActivity(intent2);
        }
        //drawLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public  void menuClick(View view)
    {
        openDrawer(drawLayout);
    }

    private static void openDrawer(DrawerLayout drawLayout) {
        drawLayout.openDrawer(GravityCompat.START);
    }
    @Override
    public void onBackPressed(){
        if(drawLayout.isDrawerOpen(GravityCompat.START)){
            drawLayout.closeDrawer(GravityCompat.START);
        }
        else
        {Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}