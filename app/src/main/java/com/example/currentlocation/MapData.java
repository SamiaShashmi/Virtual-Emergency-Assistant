package com.example.currentlocation;

public class MapData {
    private final double lat;
    private final double lon;

    public MapData(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat(){
        return this.lat;
    }
    public double getLon(){
        return  this.lon;
    }

}
