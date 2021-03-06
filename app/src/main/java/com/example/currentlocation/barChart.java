package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class barChart extends AppCompatActivity {
    EditText xValue,yValue;
    Button InsertBtn;
    BarChart barChart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    //    ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
    BarData barData;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
//        xValue=findViewById(R.id.xco);
//        yValue=findViewById(R.id.yco);
        InsertBtn=findViewById(R.id.button2);
        barChart=findViewById(R.id.graph);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("ChartValues");
        insertData();
    }

    private void insertData() {
        InsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//               String id=myRef.push().getKey();
//               int x= Integer.parseInt(xValue.getText().toString());
//               int y=Integer.parseInt(yValue.getText().toString());
//
//               DataPoint dataPoint = new DataPoint(x,y);
//                myRef.child("crime1").setValue(new DataPoint(1, MapsActivity.uttaraCount));
//                myRef.child("crime2").setValue(new DataPoint(2, MapsActivity.bananiCount));
//                myRef.child("crime3").setValue(new DataPoint(3, MapsActivity.mohammadpurCount));
//                myRef.child("crime4").setValue(new DataPoint(4, MapsActivity.dhanmondiCount));
//                myRef.child("crime5").setValue(new DataPoint(5, MapsActivity.mirpurCount));

                retrieveData();

            }
        });
    }

    private void retrieveData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
                //holds data from firebase
                if(dataSnapshot.hasChildren())
                {
                    for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren())//
                    {
                        DataPoint dataPoint = myDataSnapshot.getValue(DataPoint.class);//create datapoint object
                        dataVals.add(new BarEntry(dataPoint.getxValue(),dataPoint.getyValue()));//save in array list
                    }
                    showChart(dataVals);
                }else
                {
                    barChart.clear();
                    barChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showChart(ArrayList<BarEntry> dataVals)
    {
        BarDataSet barDataSet = new BarDataSet(dataVals, "Locations");
        barDataSet.setColors(Color.WHITE);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barData = new BarData(barDataSet);
        barChart.clear();
//        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateX(2000);
        barChart.getDescription().setText("Location vs Overall Crime Count");
        barChart.setClickable(false);
        barChart.invalidate();
    }
}