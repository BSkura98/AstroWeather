package com.example.astroweather1.weather;

public class WeatherSimpleInformation {
    String day;
    int minTemperature;
    int maxTemperature;
    String description;

    public WeatherSimpleInformation(String day, int minTemperature, int maxTemperature, String description) {
        this.day = day;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
