package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.astroweather1.weather.WeatherInformation;

public class UnitSelectionActivity extends AppCompatActivity {
    Spinner spinner;
    Button okButton;
    String units[] = {"Celsius", "Fahrenheit"};
    ArrayAdapter<String> adapter;

    String selectedUnit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_selection);
        spinner = findViewById(R.id.spinner);
        okButton = findViewById(R.id.okButton);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, units);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedUnit.isEmpty()){
                    WeatherInformation.setUnit(selectedUnit);
                }
                finish();
            }
        });

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //use postion value
                switch (position)
                {
                    case 0:
                        selectedUnit = "C";
                        break;
                    case 1:
                        selectedUnit = "F";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
