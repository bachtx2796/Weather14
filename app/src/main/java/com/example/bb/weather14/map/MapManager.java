package com.example.bb.weather14.map;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by BB on 3/30/2018.
 */

public class MapManager {

    private GoogleMap googleMap;
    private Context mContext;

    public MapManager(Context context){
        this.mContext = context;
    }

    public MapManager(Context context,GoogleMap googleMap) {
        this.mContext = context;
        this.googleMap = googleMap;
        initMap();
    }

    public GoogleMap getGoogleMap(){
        return googleMap;
    }

    public void addMarker(LatLng latLng, String title) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(title);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        googleMap.clear(); // xoa cac marker khac tren man hinh
        googleMap.addMarker(markerOptions);
        moveToLocation(latLng);
    }

    private void initMap(){
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true); // hien thi button xac dinh vi tri
        googleMap.getUiSettings().setMapToolbarEnabled(true);
    }

    public void turnOnGPS() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSenable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSenable == false){
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            mContext.startActivity(intent);
        }
    }

    public void moveToLocation(LatLng latLng){
        CameraUpdate cameraUpdateFactory = CameraUpdateFactory.newLatLngZoom(latLng,16); // tu chay den vi tri xac dinh
        googleMap.animateCamera(cameraUpdateFactory);
    }
}
