package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationOperator;

import org.json.JSONException;

public class UnitSelectionActivity extends AppCompatActivity {
    Spinner temperatureSpinner, pressureSpinner, windSpeedSpinner, visibilitySpinner;
    Button okButton;
    String temperatureUnits[] = {"Select unit","Celsius", "Fahrenheit"};
    String pressureUnits[] = {"Select unit","inches", "hPa"};
    String windSpeedUnits[] = {"Select unit","mph", "km/h"};
    String visibilityUnits[] = {"Select unit","miles", "km"};
    ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4;

    String selectedTemperatureUnit ="Select unit";
    String selectedPressureUnit="Select unit";
    String selectedWindSpeedUnit="Select unit";
    String selectedVisibilityUnit="Select unit";

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
        final Context context = this;

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedTemperatureUnit.equals("Select unit")){
                    WeatherInformation.setTemperatureUnit(selectedTemperatureUnit);
                }
                if(!selectedPressureUnit.equals("Select unit")){
                    WeatherInformation.setPressureUnit(selectedPressureUnit);
                }
                if(!selectedWindSpeedUnit.equals("Select unit")){
                    WeatherInformation.setWindSpeedUnit(selectedWindSpeedUnit);
                }
                if(!selectedVisibilityUnit.equals("Select unit")){
                    WeatherInformation.setVisibilityUnit(selectedVisibilityUnit);
                }
                try{
                    WeatherInformationOperator.saveSettingsFile(context);
                }catch (JSONException e){
                    e.printStackTrace();
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
                        selectedTemperatureUnit="Select unit";
                        break;
                    case 1:
                        selectedTemperatureUnit = "C";
                        break;
                    case 2:
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
                        selectedPressureUnit="Select unit";
                        break;
                    case 1:
                        selectedPressureUnit = "inches";
                        break;
                    case 2:
                        selectedPressureUnit = "hPa";
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
                        selectedWindSpeedUnit="Select unit";
                        break;
                    case 1:
                        selectedWindSpeedUnit = "mph";
                        break;
                    case 2:
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
                        selectedVisibilityUnit="Select unit";
                        break;
                    case 1:
                        selectedVisibilityUnit = "miles";
                        break;
                    case 2:
                        selectedVisibilityUnit = "km";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
