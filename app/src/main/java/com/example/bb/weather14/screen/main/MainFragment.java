package com.example.bb.weather14.screen.main;

import android.Manifest;
import android.annotation.SuppressLint;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.DialogUtils;
import com.example.bb.bachcore.utils.PermissionUtils;
import com.example.bb.bachcore.utils.RecyclerUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.data.dto.DailyDTO;
import com.example.bb.weather14.data.dto.LocationDTO;
import com.example.bb.weather14.data.dto.TempDailyDTO;
import com.example.bb.weather14.data.dto.TempDetailDTO;
import com.example.bb.weather14.data.dto.TempInDay;
import com.example.bb.weather14.data.dto.WindDTO;
import com.example.bb.weather14.screen.main.adapter.DailyAdapter;
import com.example.bb.weather14.screen.main.adapter.HourlyAdapter;
import com.example.bb.weather14.utils.DateTimeUtil;
import com.example.bb.weather14.utils.WeatherUtils;
import com.example.bb.weather14.customview.CustomHeaderView;
import com.example.bb.weather14.data.ServiceBuilder;
import com.example.bb.weather14.data.cuongdto.HourlyDTO;


import com.example.bb.weather14.map.MapManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

  @BindView(R.id.main_temp_tv)
  TextView mTempTv;
  @BindView(R.id.main_des_tv)
  TextView mDesTv;
  @BindView(R.id.max_temp_tv)
  TextView mMaxTempTv;
  @BindView(R.id.min_temp_tv)
  TextView mMinTemTv;
  @BindView(R.id.wind_tv)
  TextView mWindTv;
  @BindView(R.id.main_iv)
  ImageView mMainIv;

  @BindView(R.id.des_iv)
  ImageView mDesIv;
  @BindView(R.id.doam_tv)
  TextView mDoamTv;
  @BindView(R.id.luongmua_tv)
  TextView mLuongmuaTv;
  @BindView(R.id.kha_nag_co_mua_tv)
  TextView mKNMuaTv;
  @BindView(R.id.may_phu_tv)
  TextView mMayphuTv;
  @BindView(R.id.ap_suat_tv)
  TextView mApsuatTv;
  @BindView(R.id.cuc_tim_tv)
  TextView mCuctimTv;
  @BindView(R.id.binh_minh_tv)
  TextView mBinhminhTv;
  @BindView(R.id.hoang_hon_tv)
  TextView mHoanghonTv;
  @BindView(R.id.daily_temp_rv)
  RecyclerView mDailyRv;

  @BindView(R.id.temp_hourly_rv)
  RecyclerView mHourlyRv;
  @BindView(R.id.detail_bt)
  TextView mDetailBt;
  @BindView(R.id.chart_temp)
  LineChart mLineChartTemp;

  private List<HourlyDTO> tempInHours;
  private HourlyAdapter hourlyAdapter;

  private List<TempInDay> tempInDays;
  private DailyAdapter dailyAdapter;

  private String mLocation;
  private String mLocationName;

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
      if (mLocation == null){
        getMyLocation();
      } else {
        getTemp(mLocation);
      }

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

  private void getContentTemp(final String key) {
    ServiceBuilder.getApiService().getCurrentTempDetail(key, true).enqueue(new Callback<List<TempDetailDTO>>() {
      @Override
      public void onResponse(Call<List<TempDetailDTO>> call, Response<List<TempDetailDTO>> response) {
        DialogUtils.dismissProgressDialog();
        if (response.isSuccessful()) {
          getTempDaily(key, response.body().get(0));
        } else {// on error
          Toast.makeText(getContext(), "Lỗi gì đó ?:)?!!", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<List<TempDetailDTO>> call, Throwable t) {
        DialogUtils.dismissProgressDialog();
        Toast.makeText(getContext(), getContext().getString(R.string.networking_connect), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void getTempDaily(final String key, final TempDetailDTO tempDetailDTO) {
    ServiceBuilder.getApiService().getTempDaily(key, true, true).enqueue(new Callback<TempDailyDTO>() {
      @Override
      public void onResponse(Call<TempDailyDTO> call, Response<TempDailyDTO> response) {
        if (response.isSuccessful()) {
          getTempHourly(key,tempDetailDTO,response.body());
        } else {
          Toast.makeText(getContext(), "Lỗi gì đó ?:)?!!", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<TempDailyDTO> call, Throwable t) {
        DialogUtils.dismissProgressDialog();
        Toast.makeText(getContext(), getContext().getString(R.string.networking_connect), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void getTempHourly(String key, final TempDetailDTO tempDetailDTO, final TempDailyDTO tempDailyDTO) {
    ServiceBuilder.getApiService().getHourlyWeather(key, true, true).enqueue(new Callback<List<HourlyDTO>>() {
      @Override
      public void onResponse(Call<List<HourlyDTO>> call, Response<List<HourlyDTO>> response) {
        DialogUtils.dismissProgressDialog();
        if (response.isSuccessful()) {
          bindData(tempDetailDTO, tempDailyDTO, response.body());
        } else {
          Toast.makeText(getContext(), "Lỗi gì đó ?:)?!!", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<List<HourlyDTO>> call, Throwable t) {
        DialogUtils.dismissProgressDialog();
        Toast.makeText(getContext(), getContext().getString(R.string.networking_connect), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void bindData(TempDetailDTO tempDetailDTO, TempDailyDTO dailyDTO, List<HourlyDTO> hourlies) {
    mTempTv.setText(tempDetailDTO.getTemperature().getMetric().getValue() + "");
    mDesTv.setText(tempDetailDTO.getWeatherText());
    mMaxTempTv.setText("Lớn nhất: " + tempDetailDTO.getTemperatureSummary().getPast6HourRangeDTO().getMaxElevation().getMetric().getValue());
    mMinTemTv.setText("Nhỏ nhất: " + tempDetailDTO.getTemperatureSummary().getPast6HourRangeDTO().getMinElevation().getMetric().getValue());
    WindDTO wind = tempDetailDTO.getWind();
    mWindTv.setText("Gió: " + wind.getWindDirection().getLocalName() + "   " + wind.getWindSpeed().getMetric().getValue() + wind.getWindSpeed().getMetric().getUnit());
    String uri = WeatherUtils.getWeatherIconURL(tempDetailDTO.getWeatherIcon());
    Picasso.with(getContext()).load(uri).into(mMainIv);

    //Bind main des
    DailyDTO currentDay = dailyDTO.getDailyDTOList().get(0);
    Picasso.with(getContext()).load(uri).into(mDesIv);
    mDoamTv.setText(tempDetailDTO.getRelativeHumidity() + "%");
    mLuongmuaTv.setText(currentDay.getDay().getRain().getRealValue() + "mm");
    mKNMuaTv.setText(currentDay.getDay().getRainProbability() + "%");
    mCuctimTv.setText(tempDetailDTO.getUvIndex() + "");
    mMayphuTv.setText(tempDetailDTO.getCloudCover() + "%");
    mApsuatTv.setText(tempDetailDTO.getPressure().getMetric().getValue() + " " + tempDetailDTO.getPressure().getMetric().getUnit());
    mBinhminhTv.setText(currentDay.getSun().getRise());
    mHoanghonTv.setText(currentDay.getSun().getSet());

    //Bind daily temp
    RecyclerUtils.setupHorizontalRecyclerView(getContext(), mDailyRv);
    tempInDays = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DAY_OF_WEEK, i);
      int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

      DailyDTO day = dailyDTO.getDailyDTOList().get(i);
      tempInDays.add(new TempInDay(dayOfWeek, day.getDay().getRainProbability(), day.getDay().getIcon(), day.getTemperature().getMaxTemp().getValue(), day.getTemperature().getMinTemp().getValue()));
    }
    dailyAdapter = new DailyAdapter(tempInDays, getContext());
    mDailyRv.setAdapter(dailyAdapter);

    //Bind hourly temp

    RecyclerUtils.setupHorizontalRecyclerView(getContext(), mHourlyRv);
    tempInHours = hourlies;
    hourlyAdapter = new HourlyAdapter(getContext(), tempInHours);
    mHourlyRv.setAdapter(hourlyAdapter);
    SpannableString content = new SpannableString(getContext().getString(R.string.temp_detail));
    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
    mDetailBt.setText(content);
    //Bind line chart
    mLineChartTemp.setDrawGridBackground(false);
    mLineChartTemp.getDescription().setEnabled(false);

    YAxis rightAxis = mLineChartTemp.getAxisRight();
    rightAxis.setDrawGridLines(false);
    rightAxis.setDrawAxisLine(false);
    rightAxis.setAxisMinimum(0f);
    rightAxis.setDrawLabels(false);

    YAxis leftAxis = mLineChartTemp.getAxisLeft();
    leftAxis.setDrawGridLines(false);
    leftAxis.setDrawAxisLine(false);
    leftAxis.setAxisMinimum(0f);
    leftAxis.setDrawLabels(false);

    final List<String> xLabel = new ArrayList<>();

    for (int i = 0; i < 12; i++) {
      xLabel.add(tempInHours.get(i).getDateTime().substring(11, 16));

    }
    XAxis xAxis = mLineChartTemp.getXAxis();
    xAxis.setDrawAxisLine(false);
    xAxis.setDrawGridLines(false);
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setAxisMinimum(0f);
    xAxis.setGranularity(1f);
    xAxis.setLabelCount(12);
    xAxis.setTextColor(Color.WHITE);
    xAxis.setValueFormatter(new IAxisValueFormatter() {
      @Override
      public String getFormattedValue(float value, AxisBase axis) {
        return xLabel.get((int) value % xLabel.size());
      }
    });

    mLineChartTemp.animateX(2500);

    ArrayList<Entry> values = new ArrayList<Entry>();
    for (int i = 0; i < 12; i++) {
      float temp = tempInHours.get(i).getTemp().getTemp();
      values.add(new Entry(i, Math.round(temp), null));
    }
    LineDataSet set1 = new LineDataSet(values, "Biên độ nhiệt");

    set1.setDrawIcons(false);

    // set the line to be drawn like this "- - - - - -"
    set1.enableDashedLine(20f, 0f, 0f);

    set1.enableDashedHighlightLine(10f, 5f, 0f);
    set1.setColor(Color.parseColor("#FFEB3B"));
    set1.setCircleColor(Color.parseColor("#FFEB3B"));
    set1.setLineWidth(1f);
    set1.setCircleRadius(1f);
    set1.setDrawCircleHole(false);
    set1.setValueTextSize(9f);
    set1.setValueTextColor(Color.WHITE);
    set1.setDrawFilled(true);
    set1.setFormLineWidth(1f);
    set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
    set1.setFormSize(10f);

    mLineChartTemp.getLegend().setTextColor(Color.WHITE);

    if (Utils.getSDKInt() >= 18) {
      // fill drawable only supported on api level 18 and above
      Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_white);
      set1.setFillDrawable(drawable);
    } else {
      set1.setFillColor(Color.parseColor("#aaffffff"));
    }

    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    dataSets.add(set1); // add the datasets

    // create a data object with the datasets
    LineData data = new LineData(dataSets);

    // set data
    mLineChartTemp.setData(data);
  }

  public void setLocationKey(String key,String name){
    mLocation = key;
    mLocationName = name;
  }

  public void getTemp(String locationKey) {
    DialogUtils.showProgressDialog(getContext());
    mCustomHeaderView.setTitle(mLocationName);
    getContentTemp(locationKey);
  }
}
