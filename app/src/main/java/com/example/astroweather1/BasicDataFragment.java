package com.example.astroweather1;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationJsonParser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class BasicDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView cityTextView, temperatureTextView, descriptionTextView, pressureTextView, timeTextView, latitudeTextView, longitudeTextView;
    private Calendar currentDate;
    private Handler handler;

    public BasicDataFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_data, container, false);
        // Inflate the layout for this fragment
        cityTextView = view.findViewById(R.id.cityTextView);
        temperatureTextView = view.findViewById(R.id.temperatureTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        pressureTextView = view.findViewById(R.id.pressureTextView);
        latitudeTextView = view.findViewById(R.id.latitudeTextView);
        longitudeTextView = view.findViewById(R.id.longitudeTextView);
        timeTextView = view.findViewById(R.id.timeTextView);

        cityTextView.setText(WeatherInformation.getCity());
        temperatureTextView.setText("Temperature: "+Integer.toString(WeatherInformation.getTemperature()));
        descriptionTextView.setText("Description: "+WeatherInformation.getDescription());
        pressureTextView.setText("Pressure: "+Double.toString(WeatherInformation.getPressure()));
        latitudeTextView.setText("Latitude: "+ Double.toString(WeatherInformation.getLatitude()));
        longitudeTextView.setText("Longitude: "+Double.toString(WeatherInformation.getLongitude()));
        currentDate = Calendar.getInstance();

        return view;
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
}
