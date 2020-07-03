package com.example.astroweather1;

import android.content.Context;

import com.example.astroweather1.weather.WeatherInformationJsonParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class FileOperator {
    private static final String FILENAME="weather_information.json";

    public static void saveFile(String jsonString, Context context){
        FileOutputStream fos = null;

        try{
            fos = context.openFileOutput(FILENAME, MODE_PRIVATE);
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
            fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine())!=null){
                sb.append(text).append("\n");
            }
            WeatherInformationJsonParser.parse(sb.toString());
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
}
