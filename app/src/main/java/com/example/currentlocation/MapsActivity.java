package com.example.currentlocation;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int uttaraCount = 1;
    private int bananiCount = 1;
    private int mirpurCount = 1;
    private int mohammadpurCount = 1;
    private int dhanmondiCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        List<MapData<Double, Double>> latlong = new ArrayList<>();
//
//        latlong.add(0, new MapData<>(23.1215, 89.456));
//        latlong.add(1, new MapData<>(48.12, 89.456));
//        latlong.add(2, new MapData<>(65.15, 89.456));
//        latlong.add(3, new MapData<>(15.25, 89.456));
//        latlong.add(4, new MapData<>(69.115, 89.456));


        // Add a marker in Sydney and move the camera


       /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if(user.getUsertype() == "Volunteer")
                    {

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/


        LatLng Uttara = new LatLng(23.866767, 90.403685);
        LatLng Mirpur = new LatLng(23.8223, 90.3654);
        LatLng Mohammadpur = new LatLng(23.7660, 90.3586);
        LatLng Dhanmondi = new LatLng(23.745374, 90.385246);
        LatLng Banani = new LatLng(23.7911968, 90.402110);

        LatLng NewPlace[] = new LatLng[3];
        NewPlace[0] = new LatLng(23.8637, 90.4028);
        NewPlace[1] = new LatLng(23.8732, 90.4089);
        NewPlace[2] = new LatLng(23.7952, 90.4059);

        for(int i=0; i<3; i++){
            if(SphericalUtil.computeDistanceBetween(Uttara, NewPlace[i]) < 2000) {
                uttaraCount++;
            }else if (SphericalUtil.computeDistanceBetween(Banani, NewPlace[i]) < 2000){
                bananiCount++;
            }else if (SphericalUtil.computeDistanceBetween(Mirpur, NewPlace[i]) < 2000){
                mirpurCount++;
            }else if (SphericalUtil.computeDistanceBetween(Mohammadpur, NewPlace[i]) < 2000){
                mohammadpurCount++;
            }
            else if (SphericalUtil.computeDistanceBetween(Dhanmondi, NewPlace[i]) < 2000){
                dhanmondiCount++;
            }


            mMap.addMarker(
                    new MarkerOptions()
                            .position(NewPlace[i])
                            .title("new place" + i)
            );
        }

        mMap.addMarker(
                new MarkerOptions()
                        .position(Uttara)
                        .title("Uttara")
                        .snippet("Crime Index: 70")
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Uttara, 12f));
        Circle circle1 = mMap.addCircle(
                new CircleOptions()
                        .center(Uttara)
                        .radius(uttaraCount * 1000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(uttaraCount), 250, 0))
                        .fillColor(Color.argb(90, 60*(uttaraCount), 250, 0))
        );
        circle1.setVisible(true);


        mMap.addMarker(
                new MarkerOptions()
                        .position(Banani)
                        .title("Banani")
                        .snippet("Crime Index: 10")
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Banani, 12f));
        Circle circle2 = mMap.addCircle(
                new CircleOptions()
                        .center(Banani)
                        .radius((bananiCount) * 1000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(bananiCount), 250, 0))
                        .fillColor(Color.argb(90, 60*(bananiCount), 250, 0))
        );
        circle2.setVisible(true);



        /*mMap.addMarker(
                new MarkerOptions()
                        .position(Mohammadpur)
                        .title("Mohammadpur")
                        .snippet("Crime Index: 50")
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mohammadpur, 12f));
        Circle circle3 = mMap.addCircle(
                new CircleOptions()
                        .center(Mohammadpur)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 250, 250, 0))
                        .fillColor(Color.argb(80, 250, 250, 0))
        );
        circle3.setVisible(true);

        mMap.addMarker(
                new MarkerOptions()
                        .position(Mirpur)
                        .title("Mirpur")
                        .snippet("Crime Index: 30")
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mirpur, 12f));
        Circle circle4 = mMap.addCircle(
                new CircleOptions()
                        .center(Mirpur)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 170, 250, 0))
                        .fillColor(Color.argb(80, 170, 250, 0))
        );
        circle4.setVisible(true);

        mMap.addMarker(
                new MarkerOptions()
                        .position(Dhanmondi)
                        .title("Dhanmondi")
                        .snippet("Crime Index: 50")
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dhanmondi, 12f));
        Circle circle5 = mMap.addCircle(
                new CircleOptions()
                        .center(Dhanmondi)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 250, 170, 0))
                        .fillColor(Color.argb(80, 250, 170, 0))
        );
        circle5.setVisible(true);*/
    }
}