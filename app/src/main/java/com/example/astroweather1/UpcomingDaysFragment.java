package com.example.astroweather1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astroweather1.weather.WeatherInformation;
import com.example.astroweather1.weather.WeatherSimpleInformation;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingDaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingDaysFragment extends Fragment implements UpdateData{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView dayTextView,dayTextView2,dayTextView3,dayTextView4,dayTextView5,dayTextView6,dayTextView7;
    private TextView minTextView,minTextView2,minTextView3,minTextView4,minTextView5,minTextView6,minTextView7;
    private TextView maxTextView,maxTextView2,maxTextView3,maxTextView4,maxTextView5,maxTextView6,maxTextView7;
    private TextView descriptionTextView, descriptionTextView2, descriptionTextView3, descriptionTextView4, descriptionTextView5, descriptionTextView6, descriptionTextView7;

    public UpcomingDaysFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingDaysFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingDaysFragment newInstance(String param1, String param2) {
        UpcomingDaysFragment fragment = new UpcomingDaysFragment();
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

    @Override
    public void updateData(){
        List<WeatherSimpleInformation> days = WeatherInformation.getDays();
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
    }
}
