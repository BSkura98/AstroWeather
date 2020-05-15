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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.os.Looper.getMainLooper;

public class SunFragment extends Fragment {
    TextView sunriseTextView, sunsetTextView, azimuthRiseTextView, azimuthSetTextView, twilightTextView, dawnTextView, currentTime1;
    AstroDateTime astroDateTime;
    AstroCalculator.Location location;
    AstroCalculator astroCalculator;
    AstroCalculator.SunInfo sunInfo;
    AstroCalculator.MoonInfo moonInfo;
    Calendar currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);

        sunriseTextView = rootView.findViewById(R.id.sunriseTextView);
        sunsetTextView = rootView.findViewById(R.id.sunsetTextView);
        azimuthRiseTextView = rootView.findViewById(R.id.azimuthRiseTextView);
        azimuthSetTextView = rootView.findViewById(R.id.azimuthSetTextView);
        twilightTextView = rootView.findViewById(R.id.twilightTextView);
        dawnTextView = rootView.findViewById(R.id.dawnTextView);
        currentTime1 = rootView.findViewById(R.id.currentTime1);
        currentDate = Calendar.getInstance();
        initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
        setData();
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("pl", "PL"));
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                currentTime1.setText(sdf.format(new Date()));
                someHandler.postDelayed(this, 10);
            }
        }, 10);

        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {//TODO dobrze byłoby to zrobić tak, żeby to było wspólne dla MoonFragment,a  także żeby po zmianie refreshtime od razu się zaktualizowało
                someHandler.postDelayed(this, 60 * RefreshTime.getRefreshTime());

                initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
                setData();

            }
        }, 10);

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
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", new Locale("pl", "PL"));
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        //sunriseTextView.setText(Integer.toString(sunInfo.getSunrise().getYear())+"."+Integer.toString(sunInfo.getSunrise().getMonth())+"."+Integer.toString(sunInfo.getSunrise().getDay()));
        //sunriseTextView.setText(sdf.format(new Date(sunInfo.getSunrise().getYear(), sunInfo.getSunrise().getMonth(), sunInfo.getSunrise().getDay(), sunInfo.getSunrise().getHour(), sunInfo.getSunrise().getMinute(), sunInfo.getSunrise().getSecond())));
        sunriseTextView.setText(sunInfo.getSunrise().toString());
        sunsetTextView.setText(sunInfo.getSunset().toString());
        azimuthRiseTextView.setText(Double.toString(sunInfo.getAzimuthRise()));
        azimuthSetTextView.setText(Double.toString(sunInfo.getAzimuthSet()));
        twilightTextView.setText(sunInfo.getTwilightEvening().toString());
        dawnTextView.setText(sunInfo.getTwilightMorning().toString());
    }
}
