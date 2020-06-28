package com.example.astroweather1.fragments;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView sunriseTextView, sunsetTextView, azimuthRiseTextView, azimuthSetTextView, twilightTextView, dawnTextView, currentTime1, latitudeTextView, longitudeTextView;
    private Calendar currentDate;
    private Handler handler;

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
        currentTime1 = rootView.findViewById(R.id.currentTime1TextView);
        latitudeTextView = rootView.findViewById(R.id.latitudeTextView);
        latitudeTextView.setText("Latitude: "+ AstroInformation.getLocation().getLatitude());
        longitudeTextView = rootView.findViewById(R.id.longitudeTextView);
        longitudeTextView.setText("Longitude: "+ AstroInformation.getLocation().getLongitude());
        currentDate = Calendar.getInstance();
        AstroInformation.initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
        setData();


        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", new Locale("pl", "PL"));
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                currentTime1.setText(simpleDateFormat.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        }, 10);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000 * AstroInformation.getRefreshTime());

                AstroInformation.initializeAstroCalculator(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
                setData();
            }
        }, 10);
    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    public void setData(){
        try{
            int hour, minute;
            hour = AstroInformation.getSunInfo().getSunrise().getHour();
            minute = AstroInformation.getSunInfo().getSunrise().getMinute();
            sunriseTextView.setText((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
            hour = AstroInformation.getSunInfo().getSunset().getHour();
            minute = AstroInformation.getSunInfo().getSunset().getMinute();
            sunsetTextView.setText((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
            azimuthRiseTextView.setText(String.format( "%.2f", AstroInformation.getSunInfo().getAzimuthRise())+"°");
            azimuthSetTextView.setText(String.format( "%.2f", AstroInformation.getSunInfo().getAzimuthSet())+"°");
            hour = AstroInformation.getSunInfo().getTwilightEvening().getHour();
            minute = AstroInformation.getSunInfo().getTwilightEvening().getMinute();
            twilightTextView.setText((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
            hour = AstroInformation.getSunInfo().getTwilightMorning().getHour();
            minute = AstroInformation.getSunInfo().getTwilightMorning().getMinute();
            dawnTextView.setText((hour<10?"0":"")+hour+":"+(minute<10?"0":"")+minute);
            latitudeTextView.setText("Latitude: "+ AstroInformation.getLocation().getLatitude());
            longitudeTextView.setText("Longitude: "+ AstroInformation.getLocation().getLongitude());
        }catch (NullPointerException e){}
    }
}
