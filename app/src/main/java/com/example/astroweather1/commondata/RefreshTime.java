package com.example.astroweather1.commondata;

public class RefreshTime {
    private static int refreshTime = 15;

    public static int getRefreshTime() {
        return refreshTime;
    }

    public static void setRefreshTime(int seconds) {
        refreshTime = seconds;
    }
}
