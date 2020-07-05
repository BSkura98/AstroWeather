package com.example.astroweather1.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherInformation {
    static String city="";
    static double latitude=0;
    static double longitude=0;
    static int temperatureInFahrenheit =0;
    static int temperatureInCelsius=0;
    static double pressure=0;
    static String description="";
    static double windSpeed=0;
    static int windDirection=0;
    static int humidity=0;
    static double visibility=0;
    static List<WeatherSimpleInformation> days=new ArrayList<>();
    static String unit = "F";

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

    public static double getPressure() {
        return pressure;
    }

    public static void setPressure(double pressure) {
        WeatherInformation.pressure = pressure;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        WeatherInformation.description = description;
    }

    public static double getWindSpeed() {
        return windSpeed;
    }

    public static void setWindSpeed(double windSpeed) {
        WeatherInformation.windSpeed = windSpeed;
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

    public static double getVisibility() {
        return visibility;
    }

    public static void setVisibility(double visibility) {
        WeatherInformation.visibility = visibility;
    }

    public static void addDay(WeatherSimpleInformation day){
        days.add(day);
    }

    public static List<WeatherSimpleInformation> getDays() {
        return days;
    }

    public static String getUnit() {
        return unit;
    }

    public static void setUnit(String unit) {
        WeatherInformation.unit = unit;
    }

    public static int getTemperature(){
        return unit.equals("C")?temperatureInCelsius:temperatureInFahrenheit;
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
