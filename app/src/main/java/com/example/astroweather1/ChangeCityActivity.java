package com.example.astroweather1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.astroweather1.weather.WeatherInformationOperator;

public class ChangeCityActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private Button okButton;
    private EditText cityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);
        okButton = findViewById(R.id.confirmCityButton);
        cityEditText = findViewById(R.id.cityEditText);
        databaseHelper = new DatabaseHelper(this);

        final Context context = this;
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = cityEditText.getText().toString();
                if (cityEditText.length() != 0) {
                    //AddData(newEntry);
                    ExampleRequestManager requestManager = ExampleRequestManager.getInstance(context);
                    ExampleRequest request = new ExampleRequest(Request.Method.GET, null, null, newEntry, new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            System.out.println(response.toString());
                            try{
                                toastMessage("Downloaded new data from Internet");
                                WeatherInformationOperator.parse(response.toString(), context);
                                finish();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            // Add success logic here
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            toastMessage("Error");
                            //FileOperator.readFile(context);
                            // Add error handling here
                        }
                    });
                    requestManager.addToRequestQueue(request);
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
