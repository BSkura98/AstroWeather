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

import com.example.astroweather1.AstroInformation;
import com.example.astroweather1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.os.Looper.getMainLooper;

public class MoonFragment extends Fragment {
    private TextView moonriseTextView, moonsetTextView, newMoonTextView, fullMoonTextView, moonPhaseTextView, moonAgeTextView, currentTime2;
    private Calendar currentDate;

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
        currentTime2 = rootView.findViewById(R.id.currentTimeTextView);
        currentDate = Calendar.getInstance();
        AstroInformation.initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
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
                someHandler.postDelayed(this, 60 * AstroInformation.getRefreshTime());

                AstroInformation.initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
                setData();

            }
        }, 10);

        return rootView;
    }

    public void setData(){
        moonriseTextView.setText(AstroInformation.getMoonInfo().getMoonrise().toString());
        moonsetTextView.setText(AstroInformation.getMoonInfo().getMoonset().toString());
        newMoonTextView.setText(AstroInformation.getMoonInfo().getNextNewMoon().toString());
        fullMoonTextView.setText(AstroInformation.getMoonInfo().getNextFullMoon().toString());
        moonPhaseTextView.setText(Double.toString(AstroInformation.getMoonInfo().getIllumination()));
        moonAgeTextView.setText(Double.toString(AstroInformation.getMoonInfo().getAge()/24));
    }
}
