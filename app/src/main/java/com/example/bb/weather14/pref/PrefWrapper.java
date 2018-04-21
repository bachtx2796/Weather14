package com.example.bb.weather14.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bb.weather14.data.dto.PredictionPlaces;
import com.google.gson.Gson;

/**
 * Shared Preferences wrapper
 * Created by hungdn on 6/19/2017.
 */

public class PrefWrapper {

    public static final String MY_PREFERENCES = "Pref";
    public static final String MY_LOCATIONS = "locations";
    public static final String LOCATION_KEY="key";
    private static Context viewContext;

    public static void init(Context context){
        viewContext=context;
    }

    public static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void savePlaces(Context context, PredictionPlaces places) {
        String placesJson = new Gson().toJson(places);
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(MY_LOCATIONS, placesJson);
        editor.apply();
    }

    public static PredictionPlaces getPlaces(Context context) {
        String placeJson = getPreference(context).getString(MY_LOCATIONS, null);
        if (placeJson == null) {
            return null;
        }
        return new Gson().fromJson(placeJson, PredictionPlaces.class);
    }

    public static void saveLocationKey(String locationKey){
        String key=new Gson().toJson(locationKey);
        SharedPreferences.Editor editor = getPreference(viewContext).edit();
        editor.putString(LOCATION_KEY,key);
        editor.apply();
    }

    public static String getKey(){
        String placeKey=getPreference(viewContext).getString(LOCATION_KEY,"");
        return new Gson().fromJson(placeKey,String.class);
    }


}
