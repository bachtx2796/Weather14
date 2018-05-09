package com.example.bb.weather14.screen.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.bb.weather14.R;
import com.example.bb.weather14.data.dto.TempInDay;
import com.example.bb.weather14.utils.WeatherUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BB on 4/27/2018.
 */

public class DailyAdapter extends RecyclerView.Adapter {

  private List<TempInDay> tempInDays;
  private Context mContext;

  public DailyAdapter(List<TempInDay> tempInDays, Context mContext) {
    this.tempInDays = tempInDays;
    this.mContext = mContext;
  }

  public class TempHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.day_tv)
    TextView mDayTv;
    @BindView(R.id.daily_rain_tv)
    TextView mRainTv;
    @BindView(R.id.daily_max_temp_tv)
    TextView mMaxTempTv;
    @BindView(R.id.daily_min_temp_tv)
    TextView mMinTempTv;
    @BindView(R.id.chart_view)
    View mChartTempTv;
    @BindView(R.id.daily_temp_iv)
    ImageView mTempIv;
    @BindView(R.id.space_view)
    View mSpaceView;

    public TempHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = View.inflate(mContext, R.layout.item_temp_daily, null);
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    itemView.setLayoutParams(params);
    return new TempHolder(itemView);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    TempHolder tempHolder = (TempHolder) holder;
    TempInDay tempInDay = tempInDays.get(position);
    tempHolder.mDayTv.setText(getDay(tempInDay.getDay()));
    String uri = WeatherUtils.getWeatherIconURL(tempInDay.getIcon());
    Picasso.with(mContext).load(uri).into(tempHolder.mTempIv);
    tempHolder.mRainTv.setText(tempInDay.getRain() + "%");
    tempHolder.mMaxTempTv.setText(tempInDay.getMaxTemp() + "");
    tempHolder.mMinTempTv.setText(tempInDay.getMinTemp() + "");

    float scale = mContext.getResources().getDisplayMetrics().density;

    tempHolder.mSpaceView.getLayoutParams().height = (int) ((15 - tempInDay.getSubTemp()) * 20 * scale);
    tempHolder.mChartTempTv.getLayoutParams().height = (int) (tempInDay.getSubTemp() * 20 * scale);
  }

  private String getDay(int day) {
    if (day == 1)
      return " CN ";
    return "TH " + day;
  }

  @Override
  public int getItemCount() {
    return tempInDays.size();
  }
}
