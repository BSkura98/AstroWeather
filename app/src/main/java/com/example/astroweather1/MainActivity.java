package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationOperator;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    Button astroButton, weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(ConnectionInformation.checkInternetConnection(this)) {
            if(WeatherInformationOperator.readSettingsFile(this)>600000){//po 10 minutach dopiero mogą być odświeżone dane, czyli 600000 milisekund
                sendRequest();
            }else{
                try{
                    WeatherInformationOperator.readWeatherForecastFile(this);
                    toastMessage("Reading weather information from a file...");
                }catch (FileNotFoundException e){
                    sendRequest();
                    //toastMessage("An error occured while reading weather information from a file");
                }
            }
        }
        else{
            try{
                WeatherInformationOperator.readWeatherForecastFile(this);
                toastMessage("No internet access - reading weather information from a file...");
            }catch (FileNotFoundException e){
                toastMessage("No internet access - the weather information in the application is incorrect");
            }
        }


        astroButton =findViewById(R.id.astroButton);
        weatherButton = findViewById(R.id.weatherButton);

        astroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AstroActivity.class);
                startActivity(startIntent);
            }
        });
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), WeatherActivity.class);
                startActivity(startIntent);
            }
        });
    }

    private void sendRequest(){
        WeatherRequestManager requestManager = WeatherRequestManager.getInstance(this);
        final Context context = this;
        WeatherRequest request = new WeatherRequest(Request.Method.GET, null, null, WeatherInformation.getCity(), new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                System.out.println(response.toString());
                try{
                    toastMessage("Weather information updated");
                    WeatherInformationOperator.parse(response.toString(), context);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    WeatherInformationOperator.readWeatherForecastFile(context);
                    toastMessage("No internet access - reading weather information from a file...");
                }catch (FileNotFoundException e){
                    toastMessage("No internet access - the weather information in the application is incorrect");
                }
            }
        });
        requestManager.addToRequestQueue(request);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
}
