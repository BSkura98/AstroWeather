package com.example.astroweather1.weather;

import android.content.Context;

import com.example.astroweather1.FileOperator;

import org.json.*;

import static com.example.astroweather1.FileOperator.buildInformationFile;

public class WeatherInformationJsonParser {
    public static void parse(String jsonString, Context context) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        WeatherInformation.setCity(obj.getJSONObject("location").getString("city"));
        WeatherInformation.setLatitude(obj.getJSONObject("location").getDouble("lat"));
        WeatherInformation.setLongitude(obj.getJSONObject("location").getDouble("long"));
        WeatherInformation.setTemperatureInFahrenheit(obj.getJSONObject("current_observation").getJSONObject("condition").getInt("temperature"));
        WeatherInformation.setPressure(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getDouble("pressure"));
        WeatherInformation.setDescription(obj.getJSONObject("current_observation").getJSONObject("condition").getString("text"));
        WeatherInformation.setWindSpeed(obj.getJSONObject("current_observation").getJSONObject("wind").getDouble("speed"));
        WeatherInformation.setWindDirection(obj.getJSONObject("current_observation").getJSONObject("wind").getInt("direction"));
        WeatherInformation.setHumidity(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getInt("humidity"));
        WeatherInformation.setVisibility(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getDouble("visibility"));
        JSONArray array = obj.getJSONArray("forecasts");
        for(int i=0;i<array.length();i++){
            WeatherInformation.addDay(new WeatherSimpleInformation(((JSONObject)array.get(i)).getString("day"),((JSONObject)array.get(i)).getInt("low"),((JSONObject)array.get(i)).getInt("high"), ((JSONObject)array.get(i)).getString("text")));
        }
        buildInformationFile(context);
        FileOperator.saveFile(jsonString, context);
    }
}
