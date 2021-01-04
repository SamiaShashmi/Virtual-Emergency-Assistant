package com.example.currentlocation;

import javax.xml.transform.sax.SAXResult;

public class DataPoint {
    int xValue;
    int yValue;

    public DataPoint(int xValue,int yValue)
    {
        this.xValue=xValue;
        this.yValue=yValue;
    }

    public DataPoint() {

    }

    public int getxValue() {

        return xValue;
    }

    public int getyValue() {

        return yValue;
    }

}