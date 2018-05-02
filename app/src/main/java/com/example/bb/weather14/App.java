package com.example.bb.weather14;

import android.app.Application;

import com.example.bb.weather14.pref.PrefWrapper;
import com.facebook.FacebookSdk;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    PrefWrapper.init(this);
    FacebookSdk.setApplicationId("370243553467434");
    FacebookSdk.sdkInitialize(getApplicationContext());
  }
}
