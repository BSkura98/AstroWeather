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
        int month, day, hour, minute;
        hour = AstroInformation.getMoonInfo().getMoonrise().getHour();
        minute = AstroInformation.getMoonInfo().getMoonrise().getMinute();
        moonriseTextView.setText((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
        hour = AstroInformation.getMoonInfo().getMoonset().getHour();
        minute = AstroInformation.getMoonInfo().getMoonset().getMinute();
        moonsetTextView.setText((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
        month = AstroInformation.getMoonInfo().getNextNewMoon().getMonth();
        day = AstroInformation.getMoonInfo().getNextNewMoon().getDay();
        newMoonTextView.setText(AstroInformation.getMoonInfo().getNextNewMoon().getYear()+"."+(month<10?"0":"")+month+"."+(day<10?"0":"")+day);
        month = AstroInformation.getMoonInfo().getNextFullMoon().getMonth();
        day = AstroInformation.getMoonInfo().getNextFullMoon().getDay();
        fullMoonTextView.setText(AstroInformation.getMoonInfo().getNextFullMoon().getYear()+"."+(month<10?"0":"")+month+"."+(day<10?"0":"")+day);
        moonPhaseTextView.setText(String.format( "%.2f", AstroInformation.getMoonInfo().getIllumination()*100)+"%");
        moonAgeTextView.setText(String.format( "%.0f", AstroInformation.getMoonInfo().getAge()));
    }
}
