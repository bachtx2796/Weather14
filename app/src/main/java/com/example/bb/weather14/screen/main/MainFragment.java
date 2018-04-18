package com.example.bb.weather14.screen.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.PermissionUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.customview.CustomHeaderView;
import com.example.bb.weather14.data.ServiceBuilder;
import com.example.bb.weather14.data.dto.Coord;
import com.example.bb.weather14.data.dto.LocationDTO;
import com.example.bb.weather14.map.MapManager;
import com.example.bb.weather14.screen.rada.RadaFragmnet;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by BB on 3/16/2018.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment {

    @BindView(R.id.custom_header_view)
    CustomHeaderView mCustomHeaderView;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private String[] LOCATION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public MainFragment(ContainerView mContainerView) {
        super(mContainerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initLayout() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        new MapManager(getContext()).turnOnGPS();
        ServiceBuilder.getApiService().getLocation("21.028511, 105.804817",false,false).enqueue(new retrofit2.Callback<LocationDTO>() {
            @Override
            public void onResponse(Call<LocationDTO> call, Response<LocationDTO> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<LocationDTO> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.back_iv)
    public void showSetting() {
        ((MainActivity) mContainerView).showMenu();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();
        if (!PermissionUtils.needRequestPermissions(getActivity(), MainFragment.this, LOCATION, 100)) {
            getMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && PermissionUtils.isPermissionsGranted(getActivity(), grantResults)) {
            getMyLocation();
        }
    }

    @SuppressLint("MissingPermission")
    private void getMyLocation(){
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mCustomHeaderView.setTitle(latLng.latitude+" "+latLng.longitude);
                }
            }
        });
    }

}
