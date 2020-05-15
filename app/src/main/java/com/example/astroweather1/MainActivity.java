package com.example.astroweather1;

import android.content.Intent;
import android.os.Bundle;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroweather1.dialogs.LocationDialog;
import com.example.astroweather1.dialogs.RefreshTimeDialog;
import com.example.astroweather1.fragments.MoonFragment;
import com.example.astroweather1.fragments.SunFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import com.example.astroweather1.ui.main.SectionsPagerAdapter;
import com.example.astroweather1.commondata.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AstroDateTime astroDateTime;
    AstroCalculator.Location location;
    AstroCalculator astroCalculator;
    AstroCalculator.SunInfo sunInfo;
    AstroCalculator.MoonInfo moonInfo;
    int refreshTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> list = new ArrayList<>();
        list.add(new SunFragment());
        list.add(new MoonFragment());

        Intent intent = new Intent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),list);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        refreshTime = 15;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.refresh_time_settings:
                RefreshTimeDialog refreshTimeDialog = new RefreshTimeDialog();
                refreshTimeDialog.show(getSupportFragmentManager(), "refreshTimeDialog");
                return true;
            case R.id.localization_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeAstroCalculator(){
        astroDateTime = new AstroDateTime(
                2020,
                5,
                9,
                13,
                4,
                0,
                2,
                false
        );
        location = new AstroCalculator.Location(51,19);
        astroCalculator = new AstroCalculator(astroDateTime, location);

        sunInfo = astroCalculator.getSunInfo();
        moonInfo = astroCalculator.getMoonInfo();
    }
}