package com.example.currentlocation;

import androidx.annotation.NonNull;
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
    private int crimeCount = 0;
    protected static int uttaraCount = 0;
    protected static int bananiCount = 0;
    protected static int mirpurCount = 0;
    protected static int mohammadpurCount = 0;
    protected static int dhanmondiCount = 0;

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
        showMapData();
    }

    private void showMapData() {

        LatLng Uttara = new LatLng(23.866767, 90.403685);
        LatLng Banani = new LatLng(23.79119, 90.40211);
        LatLng Mirpur = new LatLng(23.8223, 90.3654);
        LatLng Mohammadpur = new LatLng(23.7660, 90.3586);
        LatLng Dhanmondi = new LatLng(23.74537, 90.38524);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Location");

        reference.child("crimeloc1").setValue(new MapData(23.8637, 90.4028));
        reference.child("crimeloc2").setValue(new MapData(23.8732, 90.4089));
        reference.child("crimeloc3").setValue(new MapData(23.7952, 90.4059));
        reference.child("crimeloc4").setValue(new MapData(23.74784, 90.38541));
        reference.child("crimeloc5").setValue(new MapData(23.76962, 90.36034));


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        MapData mapData = snapshot.getValue(MapData.class);
                        double latd = mapData.getLat();
                        double lond = mapData.getLon();
                        LatLng newLoc = new LatLng(latd, lond);

                        if (SphericalUtil.computeDistanceBetween(Uttara, newLoc) < 2000) {
                            uttaraCount++;
                        } else if (SphericalUtil.computeDistanceBetween(Banani, newLoc) < 2000) {
                            bananiCount++;
                        } else if (SphericalUtil.computeDistanceBetween(Mirpur, newLoc) < 2000) {
                            mirpurCount++;
                        } else if (SphericalUtil.computeDistanceBetween(Mohammadpur, newLoc) < 2000) {
                            mohammadpurCount++;
                        } else if (SphericalUtil.computeDistanceBetween(Dhanmondi, newLoc) < 2000) {
                            dhanmondiCount++;
                        }
                        crimeCount++;
                        mMap.addMarker(
                                new MarkerOptions()
                                        .position(newLoc)
                                        .title("crime location " + crimeCount)
                        );
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        /*LatLng NewPlace[] = new LatLng[3];
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
        }*/

        mMap.addMarker(
                new MarkerOptions()
                        .position(Uttara)
                        .title("Uttara")
                        .snippet("Crime Index: " + 100*uttaraCount)
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Uttara, 12f));
        Circle circle1 = mMap.addCircle(
                new CircleOptions()
                        .center(Uttara)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(uttaraCount), 250 - (30*uttaraCount), 0))
                        .fillColor(Color.argb(90, 60*(uttaraCount), 250 - (30*uttaraCount), 0))
        );
        circle1.setVisible(true);


        mMap.addMarker(
                new MarkerOptions()
                        .position(Banani)
                        .title("Banani")
                        .snippet("Crime Index: " + 100*bananiCount)
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Banani, 12f));
        Circle circle2 = mMap.addCircle(
                new CircleOptions()
                        .center(Banani)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(bananiCount), 250 - (30*bananiCount), 0))
                        .fillColor(Color.argb(90, 60*(bananiCount), 250 - (30*bananiCount), 0))
        );
        circle2.setVisible(true);


        mMap.addMarker(
                new MarkerOptions()
                        .position(Mohammadpur)
                        .title("Mohammadpur")
                        .snippet("Crime Index: " + 100*mohammadpurCount)
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mohammadpur, 12f));
        Circle circle3 = mMap.addCircle(
                new CircleOptions()
                        .center(Mohammadpur)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(mohammadpurCount), 250 - (30*mohammadpurCount), 0))
                        .fillColor(Color.argb(80, 60*(mohammadpurCount), 250 - (30*mohammadpurCount), 0))
        );
        circle3.setVisible(true);


        mMap.addMarker(
                new MarkerOptions()
                        .position(Mirpur)
                        .title("Mirpur")
                        .snippet("Crime Index: " + 100*mirpurCount)
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mirpur, 12f));
        Circle circle4 = mMap.addCircle(
                new CircleOptions()
                        .center(Mirpur)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(mirpurCount), 250 - (30*mirpurCount), 0))
                        .fillColor(Color.argb(80, 60*(mirpurCount), 250 - (30*mirpurCount), 0))
        );
        circle4.setVisible(true);


        mMap.addMarker(
                new MarkerOptions()
                        .position(Dhanmondi)
                        .title("Dhanmondi")
                        .snippet("Crime Index: " + 100*dhanmondiCount)
        );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dhanmondi, 12f));
        Circle circle5 = mMap.addCircle(
                new CircleOptions()
                        .center(Dhanmondi)
                        .radius(2000)
                        .strokeWidth(2f)
                        .strokeColor(Color.argb(70, 60*(dhanmondiCount), 250 - (30*dhanmondiCount), 0))
                        .fillColor(Color.argb(80, 60*(dhanmondiCount), 250 - (30*dhanmondiCount), 0))
        );
        circle5.setVisible(true);
    }
}