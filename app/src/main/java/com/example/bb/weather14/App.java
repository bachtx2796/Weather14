package com.example.bb.weather14;

import android.app.Application;

import com.example.bb.weather14.pref.PrefWrapper;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        PrefWrapper.init(this);
    }
}
