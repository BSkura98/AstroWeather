package com.example.astroweather1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroweather1.R;

public class PageFragment1 extends Fragment {
    TextView sunriseTextView, sunsetTextView, azimuthRiseTextView, azimuthSetTextView, twilightTextView, dawnTextView;
    AstroDateTime astroDateTime;
    AstroCalculator.Location location;
    AstroCalculator astroCalculator;
    AstroCalculator.SunInfo sunInfo;
    AstroCalculator.MoonInfo moonInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);

        initializeAstroCalculator();
        sunriseTextView = rootView.findViewById(R.id.sunriseTextView);
        //String text = sunInfo.getSunrise().toString();
        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+text);
        sunriseTextView.setText(sunInfo.getSunrise().toString());
        sunsetTextView = rootView.findViewById(R.id.sunsetTextView);
        sunsetTextView.setText(sunInfo.getSunset().toString());
        azimuthRiseTextView = rootView.findViewById(R.id.azimuthRiseTextView);
        azimuthRiseTextView.setText(Double.toString(sunInfo.getAzimuthRise()));
        azimuthSetTextView = rootView.findViewById(R.id.azimuthSetTextView);
        azimuthSetTextView.setText(Double.toString(sunInfo.getAzimuthSet()));
        twilightTextView = rootView.findViewById(R.id.twilightTextView);
        twilightTextView.setText(sunInfo.getTwilightEvening().toString());
        dawnTextView = rootView.findViewById(R.id.dawnTextView);
        dawnTextView.setText(sunInfo.getTwilightMorning().toString());

        return rootView;
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
