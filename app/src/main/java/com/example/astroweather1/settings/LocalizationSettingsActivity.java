package com.example.astroweather1.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.astroweather1.AstroInformation;
import com.example.astroweather1.R;

public class LocalizationSettingsActivity extends AppCompatActivity {
    EditText latitudeEditText, longitudeEditText;
    Button confirmLocalizationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization_settings);

        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);
        confirmLocalizationButton = findViewById(R.id.confirmLocalizationButton);
        confirmLocalizationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(latitudeEditText.getText().toString().length()!=0
                        &&longitudeEditText.getText().toString().length()!=0
                        &&!latitudeEditText.getText().toString().equals(".")
                        &&!longitudeEditText.getText().toString().equals(".")
                        &&!latitudeEditText.getText().toString().equals("-")
                        &&!longitudeEditText.getText().toString().equals("-")
                        &&!latitudeEditText.getText().toString().startsWith("-.")
                        &&!longitudeEditText.getText().toString().startsWith("-.")
                        &&!(Double.parseDouble(latitudeEditText.getText().toString())>90)
                        &&!(Double.parseDouble(latitudeEditText.getText().toString())<-90)
                        &&!(Double.parseDouble(longitudeEditText.getText().toString())>180)
                        &&!(Double.parseDouble(longitudeEditText.getText().toString())<-180)){
                    AstroInformation.setLocation(Double.parseDouble(latitudeEditText.getText().toString()),Double.parseDouble(longitudeEditText.getText().toString()));
                }else{
                    Toast.makeText(getApplicationContext(),"Error: Incorrect data", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        if(savedInstanceState != null){
            latitudeEditText.setText(savedInstanceState.getString("latitude"));
            longitudeEditText.setText(savedInstanceState.getString("longitude"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("latitude", latitudeEditText.getText().toString());
        outState.putString("longitude", longitudeEditText.getText().toString());
    }
}
