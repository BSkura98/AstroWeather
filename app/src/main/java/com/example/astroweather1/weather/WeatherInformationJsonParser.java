package com.example.astroweather1.weather;

import org.json.*;

import java.util.Iterator;

public class WeatherInformationJsonParser {
    public static void parse(String jsonString, WeatherInformation weatherInformation) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        weatherInformation.setCity(obj.getJSONObject("location").getString("city"));
        weatherInformation.setLatitude(obj.getJSONObject("location").getDouble("lat"));
        weatherInformation.setLongitude(obj.getJSONObject("location").getDouble("long"));
        weatherInformation.setTemperature(obj.getJSONObject("current_observation").getJSONObject("condition").getInt("temperature"));
        weatherInformation.setPressure(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getDouble("pressure"));
        weatherInformation.setDescription(obj.getJSONObject("current_observation").getJSONObject("condition").getString("text"));
        weatherInformation.setWindSpeed(obj.getJSONObject("current_observation").getJSONObject("wind").getDouble("speed"));
        weatherInformation.setWindDirection(obj.getJSONObject("current_observation").getJSONObject("wind").getInt("direction"));
        weatherInformation.setHumidity(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getInt("humidity"));
        weatherInformation.setVisibility(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getDouble("visibility"));
        JSONArray array = obj.getJSONArray("forecasts");
        /*Iterator iterator = array.iterator();
        while(iterator.hasNext()){
            obj = (JSONObject)iterator.next();
            weatherInformation.addDay(new WeatherSimpleInformation(obj.getString("day"), obj.getInt("low"), obj.getInt("high"), obj.getString("text")));
        }*/
    }
}
