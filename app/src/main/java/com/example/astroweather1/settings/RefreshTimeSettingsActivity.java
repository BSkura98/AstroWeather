package com.example.astroweather1.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astroweather1.AstroInformation;
import com.example.astroweather1.R;

public class RefreshTimeSettingsActivity extends AppCompatActivity {
    //String text;
    EditText refreshTimeEditText;
    Button confirmRefreshTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_time_settings);

        refreshTimeEditText = findViewById(R.id.refreshTimeEditText);
        confirmRefreshTimeButton = findViewById(R.id.confirmRefreshTimeButton);
        confirmRefreshTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(refreshTimeEditText.getText().toString().length()!=0){
                    AstroInformation.setRefreshTime(Integer.parseInt(refreshTimeEditText.getText().toString()));
                }else{
                    Toast.makeText(getApplicationContext(),"Error: Incorrect data", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        if(savedInstanceState != null){
            refreshTimeEditText.setText(savedInstanceState.getString("text"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("text", refreshTimeEditText.getText().toString());
    }
}
