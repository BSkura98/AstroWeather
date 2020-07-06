package com.example.astroweather1.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather1.R;
import com.example.astroweather1.weather.WeatherInformation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class BasicDataFragment extends Fragment implements UpdateData {
    private TextView cityTextView, temperatureTextView, descriptionTextView, pressureTextView, timeTextView, latitudeTextView, longitudeTextView;
    private Calendar currentDate;
    private Handler handler;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_data, container, false);
        cityTextView = view.findViewById(R.id.cityTextView);
        temperatureTextView = view.findViewById(R.id.temperatureTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        pressureTextView = view.findViewById(R.id.pressureTextView);
        latitudeTextView = view.findViewById(R.id.latitudeTextView);
        longitudeTextView = view.findViewById(R.id.longitudeTextView);
        timeTextView = view.findViewById(R.id.timeTextView);

        updateData();
        currentDate = Calendar.getInstance();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateData();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", new Locale("pl", "PL"));
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                timeTextView.setText(simpleDateFormat.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        }, 10);
    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void updateData(){
        cityTextView.setText(WeatherInformation.getCity());
        temperatureTextView.setText("Temperature: "+Integer.toString(WeatherInformation.getTemperature())+"°"+WeatherInformation.getTemperatureUnit());
        descriptionTextView.setText("Description: "+WeatherInformation.getDescription());
        pressureTextView.setText("Pressure: "+String.format( "%.2f", WeatherInformation.getPressure())+" "+WeatherInformation.getPressureUnit());
        latitudeTextView.setText("Latitude: "+ String.format( "%.2f", WeatherInformation.getLatitude()));
        longitudeTextView.setText("Longitude: "+String.format( "%.2f", WeatherInformation.getLongitude()));
    }
}
