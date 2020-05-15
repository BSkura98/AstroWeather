package com.example.astroweather1.commondata;

import com.astrocalculator.AstroCalculator;

public class Location {
    private static AstroCalculator.Location location = new AstroCalculator.Location(0,0);

    public static void setLatitude(double latitude){
        location.setLatitude(latitude);
    }

    public static double getLatitude(){
        return location.getLatitude();
    }

    public static void setLongitude(double longitude){
        location.setLongitude(longitude);
    }

    public static double getLongitude(){
        return location.getLongitude();
    }

    public static void setLocation(double latitude, double longitude){
        location.setLatitude(latitude);
        location.setLongitude(longitude);
    }

    public static AstroCalculator.Location getLocation(){
        return location;
    }
}
