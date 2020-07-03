package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.astroweather1.weather.WeatherInformationJsonParser;

public class MainActivity extends AppCompatActivity {
    Button astroButton, weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ExampleRequestManager requestManager = ExampleRequestManager.getInstance(this);
        final Context context = this;
        ExampleRequest request = new ExampleRequest(Request.Method.GET, null, null, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                System.out.println(response.toString());
                try{
                    WeatherInformationJsonParser.parse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
                // Add success logic here
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                FileOperator.readFile(context);
                // Add error handling here
            }
        });
        requestManager.addToRequestQueue(request);

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
}
