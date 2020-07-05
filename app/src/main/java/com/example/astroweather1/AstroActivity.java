package com.example.astroweather1;

import android.content.Intent;
import android.os.Bundle;

import com.example.astroweather1.fragments.MoonFragment;
import com.example.astroweather1.fragments.SunFragment;
import com.example.astroweather1.settings.LocalizationSettingsActivity;
import com.example.astroweather1.settings.RefreshTimeSettingsActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import com.example.astroweather1.ui.main.AstroSectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AstroActivity extends AppCompatActivity {
    private SunFragment sunFragment;
    private MoonFragment moonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro);

        sunFragment = new SunFragment();
        moonFragment = new MoonFragment();

        List<Fragment> list = new ArrayList<>();
        list.add(sunFragment);
        list.add(moonFragment);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AstroSectionsPagerAdapter sectionsPagerAdapter = new AstroSectionsPagerAdapter(this, getSupportFragmentManager(),list);
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.refresh_time_settings:
                intent = new Intent(this, RefreshTimeSettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.localization_settings:
                intent = new Intent(this, LocalizationSettingsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}