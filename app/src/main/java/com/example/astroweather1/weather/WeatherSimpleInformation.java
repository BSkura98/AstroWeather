package com.example.astroweather1.weather;

public class WeatherSimpleInformation {
    String day;
    int minTemperatureInFahrenheit;
    int maxTemperatureInFahrenheit;
    int minTemperatureInCelsius;
    int maxTemperatureInCelsius;
    String description;

    public WeatherSimpleInformation(String day, int minTemperatureInFahrenheit, int maxTemperatureInFahrenheit, String description) {
        this.day = day;
        this.minTemperatureInFahrenheit = minTemperatureInFahrenheit;
        this.maxTemperatureInFahrenheit = maxTemperatureInFahrenheit;
        this.minTemperatureInCelsius = (int)((minTemperatureInFahrenheit-32)*(0.5556));
        this.maxTemperatureInCelsius = (int)((maxTemperatureInFahrenheit-32)*(0.5556));
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getMinTemperatureInFahrenheit() {
        return minTemperatureInFahrenheit;
    }

    public void setMinTemperatureInFahrenheit(int minTemperatureInFahrenheit) {
        this.minTemperatureInFahrenheit = minTemperatureInFahrenheit;
        this.minTemperatureInCelsius = (int)((minTemperatureInFahrenheit-32)*(0.5556));
    }

    public int getMaxTemperatureInFahrenheit() {
        return maxTemperatureInFahrenheit;
    }

    public void setMaxTemperatureInFahrenheit(int maxTemperatureInFahrenheit) {
        this.maxTemperatureInFahrenheit = maxTemperatureInFahrenheit;
        this.maxTemperatureInCelsius = (int)((maxTemperatureInFahrenheit-32)*(0.5556));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinTemperature(){
        return WeatherInformation.getUnit().equals("C")?minTemperatureInCelsius:minTemperatureInFahrenheit;
    }

    public int getMaxTemperature(){
        return WeatherInformation.getUnit().equals("C")?maxTemperatureInCelsius:maxTemperatureInFahrenheit;
    }
}
