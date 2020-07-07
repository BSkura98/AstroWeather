package com.example.astroweather1.weather;

import android.content.Context;

import com.example.astroweather1.AstroInformation;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class WeatherInformationOperator {
    private static final String WEATHER_FORECAST_FILENAME ="weather_information.json";
    private static final String SETTINGS_FILENAME = "settings.json";

    public static void parse(String jsonString, Context context) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        WeatherInformation.setCity(obj.getJSONObject("location").getString("city"));
        WeatherInformation.setLatitude(obj.getJSONObject("location").getDouble("lat"));
        WeatherInformation.setLongitude(obj.getJSONObject("location").getDouble("long"));
        AstroInformation.setLocation(WeatherInformation.getLatitude(), WeatherInformation.getLongitude());
        WeatherInformation.setTemperatureInFahrenheit(obj.getJSONObject("current_observation").getJSONObject("condition").getInt("temperature"));
        WeatherInformation.setPressureInInches(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getDouble("pressure"));
        WeatherInformation.setDescription(obj.getJSONObject("current_observation").getJSONObject("condition").getString("text"));
        WeatherInformation.setWindSpeedInMph(obj.getJSONObject("current_observation").getJSONObject("wind").getDouble("speed"));
        WeatherInformation.setWindDirection(obj.getJSONObject("current_observation").getJSONObject("wind").getInt("direction"));
        WeatherInformation.setHumidity(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getInt("humidity"));
        WeatherInformation.setVisibilityInMiles(obj.getJSONObject("current_observation").getJSONObject("atmosphere").getDouble("visibility"));
        JSONArray array = obj.getJSONArray("forecasts");
        WeatherInformation.deleteDays();
        for(int i=0;i<array.length();i++){
            WeatherInformation.addDay(new WeatherSimpleInformation(((JSONObject)array.get(i)).getString("day"),((JSONObject)array.get(i)).getInt("low"),((JSONObject)array.get(i)).getInt("high"), ((JSONObject)array.get(i)).getString("text")));
        }
        saveSettingsFile(context);
        saveWeatherForecastFile(jsonString, context);
    }

    public static void saveWeatherForecastFile(String jsonString, Context context){
        FileOutputStream fos = null;

        try{
            fos = context.openFileOutput(WEATHER_FORECAST_FILENAME, MODE_PRIVATE);
            fos.write(jsonString.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readWeatherForecastFile(Context context) throws FileNotFoundException {
        FileInputStream fis = null;

        try{
            fis = context.openFileInput(WEATHER_FORECAST_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine())!=null){
                sb.append(text).append("\n");
            }
            WeatherInformationOperator.parse(sb.toString(), context);
        }catch(FileNotFoundException e){
            throw e;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveSettingsFile(Context context) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("last refresh",System.currentTimeMillis());
        object.put("last location", WeatherInformation.getCity());
        object.put("temperature unit", WeatherInformation.getTemperatureUnit());
        object.put("pressure unit", WeatherInformation.getPressureUnit());
        object.put("wind speed unit", WeatherInformation.getWindSpeedUnit());
        object.put("visibility unit", WeatherInformation.getVisibilityUnit());
        System.out.println("Built file: "+object.toString());
        FileOutputStream fos = null;

        try{
            fos = context.openFileOutput(SETTINGS_FILENAME, MODE_PRIVATE);
            fos.write(object.toString().getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static long readSettingsFile(Context context){
        FileInputStream fis = null;

        JSONObject obj=null;
        try{
            fis = context.openFileInput(SETTINGS_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine())!=null){
                sb.append(text).append("\n");
            }
            obj = new JSONObject(sb.toString());
            System.out.println("Saved file: "+sb.toString());
            WeatherInformation.setCity(obj.getString("last location"));
            WeatherInformation.setTemperatureUnit(obj.getString("temperature unit"));
            WeatherInformation.setPressureUnit(obj.getString("pressure unit"));
            WeatherInformation.setVisibilityUnit(obj.getString("visibility unit"));
        }catch(FileNotFoundException e){

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try{
                    fis.close();
                    if(obj!=null){
                        System.out.println(System.currentTimeMillis()-obj.getLong("last refresh"));
                        return System.currentTimeMillis()-obj.getLong("last refresh");
                    }
                }catch (IOException | JSONException e){
                    e.printStackTrace();
                }
            }
            return 600001;
        }
    }
}
