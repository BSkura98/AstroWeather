package com.example.astroweather1;

import android.content.Context;

import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class FileOperator {
    private static final String WEATHER_INFORMATION_FILENAME ="weather_information.json";
    private static final String LAST_INFORMATION_FILENAME = "last_information_filename.json";

    public static void saveFile(String jsonString, Context context){
        FileOutputStream fos = null;

        try{
            fos = context.openFileOutput(WEATHER_INFORMATION_FILENAME, MODE_PRIVATE);
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

    public static void readFile(Context context){
        FileInputStream fis = null;

        try{
            fis = context.openFileInput(WEATHER_INFORMATION_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine())!=null){
                sb.append(text).append("\n");
            }
            WeatherInformationJsonParser.parse(sb.toString(), context);
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

    public static void buildInformationFile(Context context) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("last refresh",System.currentTimeMillis());
        object.put("last location", WeatherInformation.getCity());
        object.put("unit", WeatherInformation.getUnit());
        System.out.println("Built file: "+object.toString());
        FileOutputStream fos = null;

        try{
            fos = context.openFileOutput(LAST_INFORMATION_FILENAME, MODE_PRIVATE);
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

    public static long loadLastInformation(Context context){
        FileInputStream fis = null;

        JSONObject obj=null;
        try{
            fis = context.openFileInput(LAST_INFORMATION_FILENAME);
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
            WeatherInformation.setUnit(obj.getString("unit"));
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
