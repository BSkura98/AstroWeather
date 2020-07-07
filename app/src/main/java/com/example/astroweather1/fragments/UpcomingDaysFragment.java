package com.example.astroweather1.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather1.R;
import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherSimpleInformation;

import java.util.List;


public class UpcomingDaysFragment extends Fragment implements UpdateData {
    private TextView dayTextView,dayTextView2,dayTextView3,dayTextView4,dayTextView5,dayTextView6,dayTextView7;
    private TextView minTextView,minTextView2,minTextView3,minTextView4,minTextView5,minTextView6,minTextView7;
    private TextView maxTextView,maxTextView2,maxTextView3,maxTextView4,maxTextView5,maxTextView6,maxTextView7;
    private TextView descriptionTextView, descriptionTextView2, descriptionTextView3, descriptionTextView4, descriptionTextView5, descriptionTextView6, descriptionTextView7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_days, container, false);

        dayTextView = view.findViewById(R.id.dayTextView);
        dayTextView2 = view.findViewById(R.id.dayTextView2);
        dayTextView3 = view.findViewById(R.id.dayTextView3);
        dayTextView4 = view.findViewById(R.id.dayTextView4);
        dayTextView5 = view.findViewById(R.id.dayTextView5);
        dayTextView6 = view.findViewById(R.id.dayTextView6);
        dayTextView7 = view.findViewById(R.id.dayTextView7);

        minTextView = view.findViewById(R.id.minTextView);
        minTextView2 = view.findViewById(R.id.minTextView2);
        minTextView3 = view.findViewById(R.id.minTextView3);
        minTextView4 = view.findViewById(R.id.minTextView4);
        minTextView5 = view.findViewById(R.id.minTextView5);
        minTextView6 = view.findViewById(R.id.minTextView6);
        minTextView7 = view.findViewById(R.id.minTextView7);

        maxTextView = view.findViewById(R.id.maxTextView);
        maxTextView2 = view.findViewById(R.id.maxTextView2);
        maxTextView3 = view.findViewById(R.id.maxTextView3);
        maxTextView4 = view.findViewById(R.id.maxTextView4);
        maxTextView5 = view.findViewById(R.id.maxTextView5);
        maxTextView6 = view.findViewById(R.id.maxTextView6);
        maxTextView7 = view.findViewById(R.id.maxTextView7);

        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        descriptionTextView2 = view.findViewById(R.id.descriptionTextView2);
        descriptionTextView3 = view.findViewById(R.id.descriptionTextView3);
        descriptionTextView4 = view.findViewById(R.id.descriptionTextView4);
        descriptionTextView5 = view.findViewById(R.id.descriptionTextView5);
        descriptionTextView6 = view.findViewById(R.id.descriptionTextView6);
        descriptionTextView7 = view.findViewById(R.id.descriptionTextView7);

        updateData();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateData();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateData(){
        List<WeatherSimpleInformation> days = WeatherInformation.getDays();
        //if(days.size()>0){
            dayTextView.setText(days.get(0).getDay());
            minTextView.setText(Integer.toString(days.get(0).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView.setText(Integer.toString(days.get(0).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView.setText(days.get(0).getDescription());

            dayTextView2.setText(days.get(1).getDay());
            minTextView2.setText(Integer.toString(days.get(1).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView2.setText(Integer.toString(days.get(1).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView2.setText(days.get(1).getDescription());

            dayTextView3.setText(days.get(2).getDay());
            minTextView3.setText(Integer.toString(days.get(2).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView3.setText(Integer.toString(days.get(2).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView3.setText(days.get(2).getDescription());

            dayTextView4.setText(days.get(3).getDay());
            minTextView4.setText(Integer.toString(days.get(3).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView4.setText(Integer.toString(days.get(3).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView4.setText(days.get(3).getDescription());

            dayTextView5.setText(days.get(4).getDay());
            minTextView5.setText(Integer.toString(days.get(4).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView5.setText(Integer.toString(days.get(4).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView5.setText(days.get(4).getDescription());

            dayTextView6.setText(days.get(5).getDay());
            minTextView6.setText(Integer.toString(days.get(5).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView6.setText(Integer.toString(days.get(5).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView6.setText(days.get(5).getDescription());

            dayTextView7.setText(days.get(6).getDay());
            minTextView7.setText(Integer.toString(days.get(6).getMinTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            maxTextView7.setText(Integer.toString(days.get(6).getMaxTemperature())+"°"+WeatherInformation.getTemperatureUnit());
            descriptionTextView7.setText(days.get(6).getDescription());
        //}
    }
}
