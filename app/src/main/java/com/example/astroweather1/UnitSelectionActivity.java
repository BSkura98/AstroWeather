package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.astroweather1.weather.WeatherInformation;

public class UnitSelectionActivity extends AppCompatActivity {
    Spinner temperatureSpinner, pressureSpinner, windSpeedSpinner, visibilitySpinner;
    Button okButton;
    String temperatureUnits[] = {"Celsius", "Fahrenheit"};
    String pressureUnits[] = {"inches", "mbar"};
    String windSpeedUnits[] = {"mph", "km/h"};
    String visibilityUnits[] = {"miles", "km"};
    ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4;

    String selectedTemperatureUnit ="";
    String selectedPressureUnit="";
    String selectedWindSpeedUnit="";
    String selectedVisibilityUnit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_selection);
        temperatureSpinner = findViewById(R.id.temperatureSpinner);
        pressureSpinner = findViewById(R.id.pressureSpinner);
        windSpeedSpinner = findViewById(R.id.windSpeedSpinner);
        visibilitySpinner = findViewById(R.id.visibilitySpinner);
        okButton = findViewById(R.id.okButton);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temperatureUnits);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pressureUnits);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, windSpeedUnits);
        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, visibilityUnits);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedTemperatureUnit.isEmpty()){
                    WeatherInformation.setTemperatureUnit(selectedTemperatureUnit);
                }
                if(!selectedPressureUnit.isEmpty()){
                    WeatherInformation.setPressureUnit(selectedPressureUnit);
                }
                if(!selectedWindSpeedUnit.isEmpty()){
                    WeatherInformation.setWindSpeedUnit(selectedWindSpeedUnit);
                }
                if(!selectedVisibilityUnit.isEmpty()){
                    WeatherInformation.setVisibilityUnit(selectedVisibilityUnit);
                }
                finish();
            }
        });

        temperatureSpinner.setAdapter(adapter1);
        temperatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use postion value
                switch (position)
                {
                    case 0:
                        selectedTemperatureUnit = "C";
                        break;
                    case 1:
                        selectedTemperatureUnit = "F";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        pressureSpinner.setAdapter(adapter2);
        pressureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use postion value
                switch (position)
                {
                    case 0:
                        selectedPressureUnit = "inches";
                        break;
                    case 1:
                        selectedPressureUnit = "mbar";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        windSpeedSpinner.setAdapter(adapter3);
        windSpeedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use postion value
                switch (position)
                {
                    case 0:
                        selectedWindSpeedUnit = "mph";
                        break;
                    case 1:
                        selectedWindSpeedUnit = "km/h";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        visibilitySpinner.setAdapter(adapter4);
        visibilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use postion value
                switch (position)
                {
                    case 0:
                        selectedVisibilityUnit = "miles";
                        break;
                    case 1:
                        selectedVisibilityUnit = "km";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
