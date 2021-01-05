package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class volunteerSignUP extends AppCompatActivity {
    private EditText signUpEmailEditText, signUpPasswordEditText;
    private EditText Fullname;
    private EditText dateOfbirth;
    private EditText MobileNumber;
    private EditText userName;
    private RadioGroup radiogroup1;
    private RadioButton radiobutton1;
    private RadioGroup radiogroup2;
    private RadioButton radiobutton2;
    private EditText emergencyContact1;
    private EditText emergencyContact2;
    private ProgressBar progressBar;
    private String latitude/* = "23.7453736"*/;
    private String longitude/* = "90.38524609999999"*/;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_sign_u_p);
        progressBar = findViewById(R.id.progressBarid);
        signUpEmailEditText= findViewById(R.id.signUpEmailEditTextID);
        signUpPasswordEditText=findViewById(R.id.signUpPasswordEditTextID);
        TextView signInTextView = findViewById(R.id.SignInTextViewID2);

        Fullname=findViewById(R.id.fullNameID);
        dateOfbirth=findViewById(R.id.dateOfBirthID);
        MobileNumber=findViewById(R.id.mobileID);
        userName=findViewById(R.id.userNameID);

        radiogroup1=findViewById(R.id.genderradioid);
        radiogroup2=findViewById(R.id.usertyperadioid);
        emergencyContact1 = findViewById(R.id.emergencyContact1);
        emergencyContact2 = findViewById(R.id.emergencyContact2);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Button signUpButton;
        signUpButton = findViewById(R.id.SignUpButtonIID);

    }
    public void signUp(View view)
    {
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("User");

        String fullname=Fullname.getText().toString().trim();
        String dateofbirth=dateOfbirth.getText().toString().trim();
        String mobileNumber=MobileNumber.getText().toString().trim();
        String username=userName.getText().toString().trim();
        String email2= signUpEmailEditText.getText().toString().trim();
        String password2= signUpPasswordEditText.getText().toString().trim();
        String eCon1 = emergencyContact1.getText().toString().trim();
        String eCon2 = emergencyContact2.getText().toString().trim();

        String gender;
        String usertype;

        int rdio1=radiogroup1.getCheckedRadioButtonId();
        radiobutton1=findViewById(rdio1);
        gender=radiobutton1.getText().toString().trim();

        int rdio2=radiogroup2.getCheckedRadioButtonId();
        radiobutton2=findViewById(rdio2);
        usertype=radiobutton2.getText().toString().trim();



        //getLatLon();
        //getLat();
        //getLon();
        if(email2.isEmpty())
        {
            signUpEmailEditText.setError("Enter an email Address");
            signUpEmailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email2).matches())
        {
            signUpEmailEditText.setError("Enter an email Address");
            signUpEmailEditText.requestFocus();
            return;

        }
        if(fullname.isEmpty())
        {
            signUpEmailEditText.setError("Field should not be empty");
            signUpEmailEditText.requestFocus();
            return;
        }
        if(dateofbirth.isEmpty())
        {
            dateOfbirth.setError("Field should not be empty");
            dateOfbirth.requestFocus();
            return;
        }
        if(mobileNumber.isEmpty())
        {
            MobileNumber.setError("Field should not be empty");
            MobileNumber.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            userName.setError("Field should not be empty");
            userName.requestFocus();
            return;
        }
        //checking validity of password
        if(password2.isEmpty())
        {
            signUpPasswordEditText.setError("Field should not be empty");
            signUpPasswordEditText.requestFocus();
            return;
        }
        if(password2.length() < 8)
        {
            signUpPasswordEditText.setError("Password must contain atleast 8 characters");
            signUpPasswordEditText.requestFocus();
            return;
        }

        User user= new User(fullname,dateofbirth,mobileNumber,gender,usertype,username,email2,
                password2,latitude,longitude,eCon1,eCon2);
        reference.child(username).setValue(user);



        Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(volunteerSignUP.this,MainActivity.class));
    }
    public void openSignInActivity(View view){
        startActivity(new Intent(volunteerSignUP.this,MainActivity.class));
    }

    /*private void getLatLon() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(signUpActivity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        String phoneNumber = "99999";
                          latitude = String.valueOf(addresses.get(0).getLatitude());
                          longitude = String.valueOf(addresses.get(0).getLongitude());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }*/
    public void getLat() {
        ActivityCompat.requestPermissions(volunteerSignUP.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(volunteerSignUP.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(volunteerSignUP.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        String lat = "";
                        lat = lat + addresses.get(0).getLatitude();
                        latitude = lat;



                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }
    public void getLon() {
        ActivityCompat.requestPermissions(volunteerSignUP.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(volunteerSignUP.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(volunteerSignUP.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        String lon = "";
                        lon = lon + addresses.get(0).getLongitude();
                        longitude = lon;



                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }

}