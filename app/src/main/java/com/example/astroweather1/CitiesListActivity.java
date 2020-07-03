package com.example.astroweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.ArrayList;

public class CitiesListActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private ListView listView;
    Button addCityButton;
    EditText cityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);
        listView=findViewById(R.id.listView);
        addCityButton = findViewById(R.id.addCityButton);
        cityEditText=findViewById(R.id.cityEditText);
        databaseHelper = new DatabaseHelper(this);

        addCityButton.setOnClickListener(new View.OnClickListener() {
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

        populateListView();
    }

    public void AddData(String newEntry) {
        boolean insertData = databaseHelper.addData(newEntry);

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
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = databaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    //Intent editScreenIntent = new Intent(CitiesListActivity.this, EditDataActivity.class);
                    //editScreenIntent.putExtra("id",itemID);
                    //editScreenIntent.putExtra("name",name);
                    //startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });*/
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
