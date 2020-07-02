package com.example.astroweather1.weather;

import org.json.*;

import java.util.Iterator;

public class WeatherInformationJsonParser {
    public static void parse(String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        WeatherInformation.setCity(obj.getJSONObject("location").getString("city"));
        WeatherInformation.setLatitude(obj.getJSONObject("location").getDouble("lat"));
        WeatherInformation.setLongitude(obj.getJSONObject("location").getDouble("long"));
        WeatherInformation.setTemperature(obj.getJSONObject("current_observation").getJSONObject("condition").getInt("temperature"));
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
        /*Iterator iterator = array.iterator();
        while(iterator.hasNext()){
            obj = (JSONObject)iterator.next();
            weatherInformation.addDay(new WeatherSimpleInformation(obj.getString("day"), obj.getInt("low"), obj.getInt("high"), obj.getString("text")));
        }*/
    }
}
