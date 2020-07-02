package com.example.astroweather1;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherInformationJsonParser;
import com.example.astroweather1.weather.WeatherRequestSender;
import com.example.astroweather1.weather.WeatherRequestSender1;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView cityTextView, temperatureTextView, descriptionTextView, pressureTextView, timeTextView, latitudeTextView, longitudeTextView;

    public BasicDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasicDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasicDataFragment newInstance(String param1, String param2) {
        BasicDataFragment fragment = new BasicDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        cityTextView.setText(WeatherInformation.getCity());
        temperatureTextView.setText("Temperature: "+Integer.toString(WeatherInformation.getTemperature()));
        descriptionTextView.setText("Description: "+WeatherInformation.getDescription());
        pressureTextView.setText("Pressure: "+Double.toString(WeatherInformation.getPressure()));
        latitudeTextView.setText("Latitude: "+ Double.toString(WeatherInformation.getLatitude()));
        longitudeTextView.setText("Longitude: "+Double.toString(WeatherInformation.getLongitude()));

        return view;
    }
}
