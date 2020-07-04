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
import com.example.astroweather1.weather.WeatherInformationJsonParser;

public class MainActivity extends AppCompatActivity {
    Button astroButton, weatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(FileOperator.loadLastInformation(this)>600000){//po 10 minutach dopiero mogą być odświeżone dane; tu powinno być 600000
            ExampleRequestManager requestManager = ExampleRequestManager.getInstance(this);
            final Context context = this;
            ExampleRequest request = new ExampleRequest(Request.Method.GET, null, null, WeatherInformation.getCity(), new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    System.out.println(response.toString());
                    try{
                        toastMessage("Downloaded new data from Internet");
                        WeatherInformationJsonParser.parse(response.toString(), context);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    // Add success logic here
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    toastMessage("Read new data from a file - no Internet access");
                    FileOperator.readFile(context);
                    // Add error handling here
                }
            });
            requestManager.addToRequestQueue(request);
        }else{
            FileOperator.readFile(this);
            toastMessage("Read new data from a file");
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

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
