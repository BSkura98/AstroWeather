package com.example.astroweather1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WeatherRequestManager {
    private static WeatherRequestManager sInstance;

    Context mContext;
    RequestQueue mRequestQueue;

    public static synchronized WeatherRequestManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new WeatherRequestManager(context);
        }
        return sInstance;
    }

    private WeatherRequestManager(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        mRequestQueue.add(request);
    }
}