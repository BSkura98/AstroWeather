package com.example.astroweather1;

import android.os.Bundle;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ustawianie lokalizacji oraz domyślnego czasu odświeżania informacji
        AstroInformation.setLocation(51,19);
        AstroInformation.setRefreshTime(15);

        List<Fragment> list = new ArrayList<>();
        list.add(new SunFragment());
        list.add(new MoonFragment());

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),list);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_time_settings:
                RefreshTimeDialog refreshTimeDialog = new RefreshTimeDialog();
                refreshTimeDialog.show(getSupportFragmentManager(), "refreshTimeDialog");
                return true;
            case R.id.localization_settings:
                LocationDialog locationDialog = new LocationDialog();
                locationDialog.show(getSupportFragmentManager(), "locationDialog");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}