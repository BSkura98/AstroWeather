package com.example.astroweather1;

import android.content.Intent;
import android.os.Bundle;

import com.example.astroweather1.fragments.MoonFragment;
import com.example.astroweather1.fragments.SunFragment;
import com.example.astroweather1.settings.LocalizationSettingsActivity;
import com.example.astroweather1.settings.RefreshTimeSettingsActivity;
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

import com.example.astroweather1.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    private BasicDataFragment basicDataFragment;
    private AdditionalDataFragment additionalDataFragment;
    private UpcomingDaysFragment upcomingDaysFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        basicDataFragment = new BasicDataFragment();
        additionalDataFragment = new AdditionalDataFragment();
        upcomingDaysFragment = new UpcomingDaysFragment();

        List<Fragment> list = new ArrayList<>();
        list.add(basicDataFragment);
        list.add(additionalDataFragment);
        list.add(upcomingDaysFragment);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),list);
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
        }

        return super.onOptionsItemSelected(item);
    }
}