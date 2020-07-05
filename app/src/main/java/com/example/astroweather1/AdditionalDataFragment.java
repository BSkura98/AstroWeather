package com.example.astroweather1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather1.weather.WeatherInformation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdditionalDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdditionalDataFragment extends Fragment implements UpdateData{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView cityTextView, windDirectionTextView, windSpeedTextView, humidityTextView, visibilityTextView;

    public AdditionalDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdditionalDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdditionalDataFragment newInstance(String param1, String param2) {
        AdditionalDataFragment fragment = new AdditionalDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        windSpeedTextView.setText("Wind speed: "+Double.toString(WeatherInformation.getWindSpeed()));
        humidityTextView.setText("Humidity: "+Integer.toString(WeatherInformation.getHumidity()));
        visibilityTextView.setText("Visibility: "+Double.toString(WeatherInformation.getVisibility()));
    }
}
