package com.example.astroweather1.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherInformation {
    static String city="Warszawa";
    static double latitude=0;
    static double longitude=0;
    static int temperatureInFahrenheit =0;
    static int temperatureInCelsius=0;
    static double pressureInInches =0;
    static double pressureInhPa = 0;
    static String description="";
    static double windSpeedInMph =0;
    static double windSpeedInKmH = 0;
    static int windDirection=0;
    static int humidity=0;
    static double visibilityInMiles =0;
    static double visibilityInKm = 0;
    static List<WeatherSimpleInformation> days=new ArrayList<>();
    static String temperatureUnit = "F";
    static String pressureUnit = "inches";
    static String windSpeedUnit="mph";
    static String visibilityUnit="miles";

    //public WeatherInformation(){}

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        WeatherInformation.city = city;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        WeatherInformation.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        WeatherInformation.longitude = longitude;
    }

    public static int getTemperatureInFahrenheit() {
        return temperatureInFahrenheit;
    }

    public static void setTemperatureInFahrenheit(int temperatureInFahrenheit) {
        WeatherInformation.temperatureInFahrenheit = temperatureInFahrenheit;
        WeatherInformation.temperatureInCelsius=(int)((temperatureInFahrenheit-32)*(0.5556));
    }

    public static double getPressureInInches() {
        return pressureInInches;
    }

    public static void setPressureInInches(double pressureInInches) {
        WeatherInformation.pressureInInches = pressureInInches;
        WeatherInformation.pressureInhPa = pressureInInches*33.863886667;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        WeatherInformation.description = description;
    }

    public static double getWindSpeedInMph() {
        return windSpeedInMph;
    }

    public static void setWindSpeedInMph(double windSpeedInMph) {
        WeatherInformation.windSpeedInMph = windSpeedInMph;
        WeatherInformation.windSpeedInKmH = windSpeedInMph*1.609344;
    }

    public static int getWindDirection() {
        return windDirection;
    }

    public static void setWindDirection(int windDirection) {
        WeatherInformation.windDirection = windDirection;
    }

    public static int getHumidity() {
        return humidity;
    }

    public static void setHumidity(int humidity) {
        WeatherInformation.humidity = humidity;
    }

    public static double getVisibilityInMiles() {
        return visibilityInMiles;
    }

    public static void setVisibilityInMiles(double visibilityInMiles) {
        WeatherInformation.visibilityInMiles = visibilityInMiles;
        WeatherInformation.visibilityInKm = visibilityInMiles*1.609344;
    }

    public static void addDay(WeatherSimpleInformation day){
        days.add(day);
    }

    public static List<WeatherSimpleInformation> getDays() {
        return days;
    }

    public static String getTemperatureUnit() {
        return temperatureUnit;
    }

    public static void setTemperatureUnit(String temperatureUnit) {
        WeatherInformation.temperatureUnit = temperatureUnit;
    }

    public static String getPressureUnit() {
        return pressureUnit;
    }

    public static void setPressureUnit(String pressureUnit) {
        WeatherInformation.pressureUnit = pressureUnit;
    }

    public static String getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public static void setWindSpeedUnit(String windSpeedUnit) {
        WeatherInformation.windSpeedUnit = windSpeedUnit;
    }

    public static String getVisibilityUnit() {
        return visibilityUnit;
    }

    public static void setVisibilityUnit(String visibilityUnit) {
        WeatherInformation.visibilityUnit = visibilityUnit;
    }

    public static int getTemperature(){
        return temperatureUnit.equals("C")?temperatureInCelsius:temperatureInFahrenheit;
    }

    public static double getPressure(){
        return pressureUnit.equals("inches")?pressureInInches: pressureInhPa;
    }

    public static double getWindSpeed(){
        return windSpeedUnit.equals("mph")?windSpeedInMph:windSpeedInKmH;
    }

    public static double getVisibility(){
        return visibilityUnit.equals("miles")?visibilityInMiles:visibilityInKm;
    }

    public static String getWindDirectionAsString(){
        if(windDirection>337){
            return "N";
        }else if(windDirection>292){
            return "NW";
        }else if(windDirection>247){
            return "W";
        }else if(windDirection>202){
            return "SW";
        }else if(windDirection>157){
            return "S";
        }else if(windDirection>112){
            return "SE";
        }else if(windDirection>67){
            return "E";
        }else if(windDirection>22){
            return "NE";
        }else{
            return "N";
        }
    }
}
