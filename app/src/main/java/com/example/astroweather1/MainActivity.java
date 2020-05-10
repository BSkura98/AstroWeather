package com.example.astroweather1;

import android.os.Build;
import android.os.Bundle;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroweather1.fragments.MoonFragment;
import com.example.astroweather1.fragments.PageFragment1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.astroweather1.ui.main.SectionsPagerAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView sunriseTextView, sunsetTextView, azimuthRiseTextView, azimuthSetTextView, twilightTextView, dawnTextView;
    AstroDateTime astroDateTime;
    AstroCalculator.Location location;
    AstroCalculator astroCalculator;
    AstroCalculator.SunInfo sunInfo;
    AstroCalculator.MoonInfo moonInfo;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new MoonFragment());

        //pager = findViewById(R.id.view_pager);
        //pagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), list);
        //pager.setAdapter(pagerAdapter);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),list);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //initializeAstroCalculator();

        /*sunriseTextView = findViewById(R.id.sunriseTextView);
        String text = sunInfo.getSunrise().toString();
        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+text);
        sunriseTextView.setText(text);
        sunsetTextView = findViewById(R.id.sunsetTextView);
        //sunsetTextView.setText(sunInfo.getSunset().toString());
        azimuthRiseTextView = findViewById(R.id.azimuthRiseTextView);
        //azimuthRiseTextView.setText((int) sunInfo.getAzimuthRise());
        azimuthSetTextView = findViewById(R.id.azimuthSetTextView);
        //azimuthSetTextView.setText((int)sunInfo.getAzimuthSet());
        twilightTextView = findViewById(R.id.twilightTextView);
        //twilightTextView.setText(sunInfo.getTwilightEvening().toString());
        dawnTextView = findViewById(R.id.dawnTextView);
        //dawnTextView.setText(sunInfo.getTwilightMorning().toString());*/
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