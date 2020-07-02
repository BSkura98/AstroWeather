package com.example.astroweather1.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherInformation {
    String city="";
    double latitude=0;
    double longitude=0;
    int temperature=0;
    double pressure=0;
    String description="";
    double windSpeed=0;
    int windDirection=0;
    int humidity=0;
    double visibility=0;
    List<WeatherSimpleInformation> days=new ArrayList<>();

    public WeatherInformation(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public void addDay(WeatherSimpleInformation day){
        days.add(day);
    }

    public List<WeatherSimpleInformation> getDays() {
        return days;
    }
}
