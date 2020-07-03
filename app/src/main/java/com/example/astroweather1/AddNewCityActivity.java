package com.example.astroweather1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewCityActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private Button addButton, backButton;
    private EditText cityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_city);
        addButton = findViewById(R.id.addCityButton);
        backButton = findViewById(R.id.backButton);
        cityEditText = findViewById(R.id.cityEditText);
        databaseHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = cityEditText.getText().toString();
                if (cityEditText.length() != 0) {
                    AddData(newEntry);
                    cityEditText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }

    public void AddData(String newEntry) {
        boolean insertData = databaseHelper.addData(newEntry);

        if (insertData) {
            Toast.makeText(this,"Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
