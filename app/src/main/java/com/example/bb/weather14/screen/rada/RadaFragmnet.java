package com.example.bb.weather14.screen.rada;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.PermissionUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.map.MapManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by BB on 3/30/2018.
 */

@SuppressLint("ValidFragment")
public class RadaFragmnet extends BaseFragment {

    private MapManager mapManager;
    private FusedLocationProviderClient fusedLocationProviderClient;

    private String[] LOCATION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public RadaFragmnet(ContainerView mContainerView) {
        super(mContainerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rada;
    }

    @Override
    protected void initLayout() {
        initMap();
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapManager = new MapManager(getContext(), googleMap);
                mapManager.turnOnGPS();
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
                if (!PermissionUtils.needRequestPermissions(getActivity(), RadaFragmnet.this, LOCATION, 100)) {
                    getCurrentLocation();
                }

            }
        });

    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        if (mapManager != null){
            mapManager.getGoogleMap().setMyLocationEnabled(true);
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mapManager.moveToLocation(latLng);
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && PermissionUtils.isPermissionsGranted(getActivity(), grantResults)) {
            getCurrentLocation();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.map_fragment);
        getActivity().getFragmentManager().beginTransaction().remove(f).commit();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();
        if (fusedLocationProviderClient != null){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mapManager.moveToLocation(latLng);
                    }
                }
            });
        }

    }

}
