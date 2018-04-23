package com.example.bb.weather14.screen.main;

import android.Manifest;
import android.annotation.SuppressLint;

import android.location.Location;
import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.TextView;
import android.widget.Toast;


import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.DialogUtils;
import com.example.bb.bachcore.utils.PermissionUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.data.dto.LocationDTO;
import com.example.bb.weather14.data.dto.TempDetailDTO;
import com.example.bb.weather14.utils.DateTimeUtil;
import com.example.bb.weather14.utils.WeatherUtils;
import com.example.bb.weather14.customview.CustomHeaderView;
import com.example.bb.weather14.data.ServiceBuilder;
import com.example.bb.weather14.data.cuongdto.HourlyDTO;


import com.example.bb.weather14.map.MapManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BB on 3/16/2018.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment {

  @BindView(R.id.custom_header_view)
  CustomHeaderView mCustomHeaderView;

  @BindView(R.id.tv_current_day)
  TextView mCurrentDayTv;

  //  @BindView(R.id.rv_hourly_weather)
//  RecyclerView mHourlyRv;
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
    mCurrentDayTv.setText(DateTimeUtil.getCurrentDay());
    WeatherUtils.getWeatherIconURL(11);
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
    new MapManager(getContext()).turnOnGPS();

//    ServiceBuilder.getApiService().getHourlyWeather("353412", true, false).enqueue(new retrofit2.Callback<List<HourlyDTO>>() {
//      @Override
//      public void onResponse(Call<List<HourlyDTO>> call, Response<List<HourlyDTO>> response) {
//        if (response.isSuccessful()) {
//          HourlyAdapter adapter = new HourlyAdapter(getContext(), response.body());
//          LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//          mHourlyRv.setLayoutManager(manager);
//          mHourlyRv.setAdapter(adapter);
//        }
//      }
//
//      @Override
//      public void onFailure(Call<List<HourlyDTO>> call, Throwable t) {
//        // DO SOMETHING
//      }
//    });
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
      DialogUtils.showProgressDialog(getContext());
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
  private void getMyLocation() {
    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
      @Override
      public void onSuccess(Location location) {
        if (location != null) {
          // get data is here
          LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

          getCurrentTemp(latLng.latitude + "," + latLng.longitude);
        } else {
          getMyLocation();
        }
      }
    });
  }

  private void getCurrentTemp(String latlng) {
    ServiceBuilder.getApiService().getLocation(latlng, false, true).enqueue(new Callback<LocationDTO>() {
      @Override
      public void onResponse(Call<LocationDTO> call, Response<LocationDTO> response) {
        if (response.isSuccessful()) {
          LocationDTO locationDTO = response.body();
          mCustomHeaderView.setTitle(locationDTO.getLocalizedName() + ", " + locationDTO.getCountry().getLocalizedName());
          getContentTemp(locationDTO.getKey());
        } else { // on error
          getContentTemp("353412"); // fake data ha noi
          Toast.makeText(getContext(), "Lỗi gì đó ?:)?!!", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<LocationDTO> call, Throwable t) {
        Toast.makeText(getContext(), getContext().getString(R.string.networking_connect), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void getContentTemp(String key) {
    ServiceBuilder.getApiService().getCurrentTempDetail(key, true).enqueue(new Callback<TempDetailDTO>() {
      @Override
      public void onResponse(Call<TempDetailDTO> call, Response<TempDetailDTO> response) {
        DialogUtils.dismissProgressDialog();
        if (response.isSuccessful()) {

        } else {// on error
          Toast.makeText(getContext(), "Lỗi gì đó ?:)?!!", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<TempDetailDTO> call, Throwable t) {
        DialogUtils.dismissProgressDialog();
        Toast.makeText(getContext(), getContext().getString(R.string.networking_connect), Toast.LENGTH_SHORT).show();
      }
    });
  }

}
