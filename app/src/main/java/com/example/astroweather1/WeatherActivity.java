package com.example.astroweather1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.astroweather1.fragments.MoonFragment;
import com.example.astroweather1.fragments.SunFragment;
import com.example.astroweather1.settings.LocalizationSettingsActivity;
import com.example.astroweather1.settings.RefreshTimeSettingsActivity;
import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationJsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.astroweather1.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    private BasicDataFragment basicDataFragment;
    private AdditionalDataFragment additionalDataFragment;
    private UpcomingDaysFragment upcomingDaysFragment;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        basicDataFragment = new BasicDataFragment();
        additionalDataFragment = new AdditionalDataFragment();
        upcomingDaysFragment = new UpcomingDaysFragment();

        fragments = new ArrayList<>();
        fragments.add(basicDataFragment);
        fragments.add(additionalDataFragment);
        fragments.add(upcomingDaysFragment);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),fragments);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.change_city:
                intent = new Intent(this, CitiesListActivity.class);
                startActivity(intent);
                //intent = new Intent(this, RefreshTimeSettingsActivity.class);
                //startActivity(intent);
                return true;
            case R.id.change_unit:
                intent = new Intent(this, UnitSelectionActivity.class);
                startActivity(intent);
                //intent = new Intent(this, RefreshTimeSettingsActivity.class);
                //startActivity(intent);
                return true;
            case R.id.refresh_weather_data:
                ExampleRequestManager requestManager = ExampleRequestManager.getInstance(this);
                final Context context = this;
                final ExampleRequest request = new ExampleRequest(Request.Method.GET, null, null, null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("Response: "+response.toString());
                        try{
                            WeatherInformationJsonParser.parse(response.toString(), context);
                            //FileOperator.saveFile(response.toString(), context);
                            for(Fragment fragment:fragments){
                                ((UpdateData)fragment).updateData();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        // Add success logic here
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error");
                        toastMessage("Something went wrong");
                        // Add error handling here
                    }
                });
                requestManager.addToRequestQueue(request);
        }

        return super.onOptionsItemSelected(item);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}