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

public class SunFragment extends Fragment {
    private TextView sunriseTextView, sunsetTextView, azimuthRiseTextView, azimuthSetTextView, twilightTextView, dawnTextView, currentTime1;
    private Calendar currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sun,container,false);

        sunriseTextView = rootView.findViewById(R.id.sunriseTextView);
        sunsetTextView = rootView.findViewById(R.id.sunsetTextView);
        azimuthRiseTextView = rootView.findViewById(R.id.azimuthRiseTextView);
        azimuthSetTextView = rootView.findViewById(R.id.azimuthSetTextView);
        twilightTextView = rootView.findViewById(R.id.twilightTextView);
        dawnTextView = rootView.findViewById(R.id.dawnTextView);
        currentTime1 = rootView.findViewById(R.id.currentTimeTextView);
        currentDate = Calendar.getInstance();
        AstroInformation.initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
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
                someHandler.postDelayed(this, 60 * AstroInformation.getRefreshTime());

                AstroInformation.initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
                setData();

            }
        }, 10);

        return rootView;
    }

    public void setData(){
        sunriseTextView.setText(AstroInformation.getSunInfo().getSunrise().toString());
        sunsetTextView.setText(AstroInformation.getSunInfo().getSunset().toString());
        azimuthRiseTextView.setText(Double.toString(AstroInformation.getSunInfo().getAzimuthRise()));
        azimuthSetTextView.setText(Double.toString(AstroInformation.getSunInfo().getAzimuthSet()));
        twilightTextView.setText(AstroInformation.getSunInfo().getTwilightEvening().toString());
        dawnTextView.setText(AstroInformation.getSunInfo().getTwilightMorning().toString());
    }
}
