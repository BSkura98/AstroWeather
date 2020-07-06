package com.example.astroweather1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather1.R;
import com.example.astroweather1.weather.WeatherInformation;


public class AdditionalDataFragment extends Fragment implements UpdateData {
    private TextView cityTextView, windDirectionTextView, windSpeedTextView, humidityTextView, visibilityTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional_data, container, false);
        cityTextView = view.findViewById(R.id.cityTextView);
        windDirectionTextView = view.findViewById(R.id.windDirectionTextView);
        windSpeedTextView = view.findViewById(R.id.windSpeedTextView);
        humidityTextView = view.findViewById(R.id.humidityTextView);
        visibilityTextView = view.findViewById(R.id.visibilityTextView);

        updateData();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateData();
    }

    @Override
    public void updateData(){
        cityTextView.setText(WeatherInformation.getCity());
        windDirectionTextView.setText("Wind direction: "+WeatherInformation.getWindDirectionAsString());
        windSpeedTextView.setText("Wind speed: "+String.format( "%.2f", WeatherInformation.getWindSpeed())+" "+WeatherInformation.getWindSpeedUnit());
        humidityTextView.setText("Humidity: "+Integer.toString(WeatherInformation.getHumidity())+"%");
        visibilityTextView.setText("Visibility: "+String.format( "%.2f", WeatherInformation.getLatitude())+" "+WeatherInformation.getVisibilityUnit());
    }
}
