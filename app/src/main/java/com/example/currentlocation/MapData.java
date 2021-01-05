package com.example.currentlocation;

public class MapData {
    double lat;
    double lon;

    public MapData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    public MapData() {

    }

    public double getLat(){
        return this.lat;
    }
    public double getLon(){
        return  this.lon;
    }

}
