package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationOperator;

import java.util.ArrayList;

public class FavoriteLocationsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private ListView listView;
    Button addCityButton;
    EditText cityEditText;
    WeatherRequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_locations);
        requestManager = WeatherRequestManager.getInstance(this);
        listView=findViewById(R.id.listView);
        addCityButton = findViewById(R.id.confirmCityButton);
        cityEditText=findViewById(R.id.cityEditText);
        databaseHelper = new DatabaseHelper(this);

        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = cityEditText.getText().toString();
                if (cityEditText.length() != 0) {
                    AddData(newEntry.trim().replace(" ", "-"));
                    cityEditText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });

        populateListView();
    }

    public void AddData(String newEntry) {
        final Context context = this;
        final WeatherRequest request = new WeatherRequest(Request.Method.GET, null, null, newEntry, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                System.out.println("Response: "+response.toString());
                try{
                    WeatherInformationOperator.parse(response.toString(), context);
                    addToDatabase(WeatherInformation.getCity());
                    populateListView();
                }catch (Exception e){
                    toastMessage("An error occured - make sure the city name is correct");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage("An error occured - make sure you have internet access");
            }
        });
        requestManager.addToRequestQueue(request);
    }

    private void addToDatabase(String city){
        boolean insertData = databaseHelper.addData(city);

        if (insertData) {
            Toast.makeText(this,"Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateListView() {
        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        listView.setLongClickable(true);
        final Context context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                System.out.println(name.toLowerCase());
                WeatherRequest request = new WeatherRequest(Request.Method.GET, null, null, name, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println(response.toString());
                        try{
                            WeatherInformationOperator.parse(response.toString(), context);
                            finish();
                        }catch (Exception e){
                            toastMessage("Error");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toastMessage("An error occured - make sure you have internet access");
                    }
                });
                requestManager.addToRequestQueue(request);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final int item = position;
                final String name = parent.getItemAtPosition(position).toString();

                new AlertDialog.Builder(FavoriteLocationsActivity.this)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseHelper.deleteData(name);
                                populateListView();
                                toastMessage("Deleted");
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
