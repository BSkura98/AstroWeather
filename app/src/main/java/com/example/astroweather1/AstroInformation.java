package com.example.astroweather1;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

public class AstroInformation {
    private static AstroCalculator astroCalculator;
    private static AstroCalculator.Location location = new AstroCalculator.Location(51,19);
    private static int refreshTime = 15;

    public static void setLocation(double latitude, double longitude){
        location.setLatitude(latitude);
        location.setLongitude(longitude);
    }

    public static AstroCalculator.Location getLocation(){
        return location;
    }

    public static int getRefreshTime() {
        return refreshTime;
    }

    public static void setRefreshTime(int seconds) {
        refreshTime = seconds;
    }

    public static void initializeAstroCalculator(int year, int month, int day, int hour, int minute, int second){
        AstroDateTime astroDateTime = new AstroDateTime(
                year,
                month,
                day,
                hour,
                minute,
                second,
                2,
                false
        );
        astroCalculator = new AstroCalculator(astroDateTime, location);
    }

    public static AstroCalculator.SunInfo getSunInfo(){
        return astroCalculator.getSunInfo();
    }

    public static AstroCalculator.MoonInfo getMoonInfo(){
        return astroCalculator.getMoonInfo();
    }
}
