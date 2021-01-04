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
        this.lat = "";
        this.lon = "";
    }

    public String getLat(){
        return this.lat;
    }
    public String getLon(){
        return  this.lon;
    }

}
