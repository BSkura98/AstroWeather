package com.example.astroweather1.fragments;

import android.os.Bundle;
import android.os.Handler;
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
import com.example.astroweather1.commondata.*;

import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.os.Looper.getMainLooper;

public class MoonFragment extends Fragment {
    TextView moonriseTextView, moonsetTextView, newMoonTextView, fullMoonTextView, moonPhaseTextView, moonAgeTextView, currentTime2;
    AstroDateTime astroDateTime;
    AstroCalculator.Location location;
    AstroCalculator astroCalculator;
    AstroCalculator.SunInfo sunInfo;
    AstroCalculator.MoonInfo moonInfo;
    Calendar currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_moon,container,false);

        moonriseTextView = rootView.findViewById(R.id.moonriseTextView);
        moonsetTextView = rootView.findViewById(R.id.moonsetTextView);
        newMoonTextView = rootView.findViewById(R.id.newMoonTextView);
        fullMoonTextView = rootView.findViewById(R.id.fullMoonTextView);
        moonPhaseTextView = rootView.findViewById(R.id.moonPhaseTextView);
        moonAgeTextView = rootView.findViewById(R.id.moonAgeTextView);
        currentTime2 = rootView.findViewById(R.id.currentTime2);
        currentDate = Calendar.getInstance();
        initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
        setData();
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("pl", "PL"));
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                currentTime2.setText(sdf.format(new Date()));

                someHandler.postDelayed(this, 10);
            }
        }, 10);

        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                someHandler.postDelayed(this, 60 * RefreshTime.getRefreshTime());

                initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
                setData();

            }
        }, 10);

        /*sunriseTextView = rootView.findViewById(R.id.sunriseTextView);
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
        dawnTextView.setText(sunInfo.getTwilightMorning().toString());*/

        return rootView;
    }

    public void initializeAstroCalculator(int year, int month, int day, int hour, int minute, int second){
        astroDateTime = new AstroDateTime(
                year,
                month,
                day,
                hour,
                minute,
                second,
                2,
                false
        );
        location = new AstroCalculator.Location(51,19);
        astroCalculator = new AstroCalculator(astroDateTime, location);

        sunInfo = astroCalculator.getSunInfo();
        moonInfo = astroCalculator.getMoonInfo();
    }

    public void setData(){
        moonriseTextView.setText(moonInfo.getMoonrise().toString());
        moonsetTextView.setText(moonInfo.getMoonset().toString());
        newMoonTextView.setText(moonInfo.getNextNewMoon().toString());
        fullMoonTextView.setText(moonInfo.getNextFullMoon().toString());
        moonPhaseTextView.setText(Double.toString(moonInfo.getIllumination()));
        moonAgeTextView.setText(Double.toString(moonInfo.getAge()/24));
    }
}
