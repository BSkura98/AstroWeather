package com.example.astroweather1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.astroweather1.fragments.AdditionalDataFragment;
import com.example.astroweather1.fragments.BasicDataFragment;
import com.example.astroweather1.fragments.UpcomingDaysFragment;
import com.example.astroweather1.fragments.UpdateData;
import com.example.astroweather1.ui.main.WeatherSectionsPagerAdapter;
import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationOperator;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        WeatherSectionsPagerAdapter sectionsPagerAdapter = new WeatherSectionsPagerAdapter(this, getSupportFragmentManager(),fragments);
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
            case R.id.favorite_locations:
                intent = new Intent(this, FavoriteLocationsActivity.class);
                startActivity(intent);
                return true;
            case R.id.change_city:
                intent=new Intent(this, ChangeCityActivity.class);
                startActivity(intent);
                return true;
            case R.id.change_units:
                intent = new Intent(this, UnitSelectionActivity.class);
                startActivity(intent);
                return true;
            case R.id.refresh_weather_data:
                WeatherRequestManager requestManager = WeatherRequestManager.getInstance(this);
                final Context context = this;
                final WeatherRequest request = new WeatherRequest(Request.Method.GET, null, null, WeatherInformation.getCity(), new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("Response: "+response.toString());
                        try{
                            WeatherInformationOperator.parse(response.toString(), context);
                            for(Fragment fragment:fragments){
                                ((UpdateData)fragment).updateData();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toastMessage("An error occurred while refreshing the data - make sure you have internet access");
                    }
                });
                requestManager.addToRequestQueue(request);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}