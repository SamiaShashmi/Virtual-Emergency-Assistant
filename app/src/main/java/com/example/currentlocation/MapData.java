package com.example.currentlocation;

public class MapData {
    String lat;
    String lon;

    public MapData(String lat, String lon){
        this.lat = lat;
        this.lon = lon;
    }
    public MapData()
    {
        this.lat = "23.7453736";
        this.lon = "90.385246099999";
    }

    public String getLat(){
        return this.lat;
    }
    public String getLon(){
        return  this.lon;
    }

}
