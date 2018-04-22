package com.example.bb.weather14.screen.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bb.weather14.R;
import com.example.bb.weather14.Utils.WeatherUtils;
import com.example.bb.weather14.data.cuongdto.HourlyDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Windows 18 on 4/22/2018.
 */

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyVH> {
  private Context mContext;
  private List<HourlyDTO> mlistWeather=new ArrayList<HourlyDTO>();
  public HourlyAdapter(Context context, List<HourlyDTO> hourlyDTOS) {
    this.mlistWeather= hourlyDTOS;
    this.mContext=context;
  }

  @Override
  public HourlyVH onCreateViewHolder(ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(mContext).inflate(R.layout.hourly_weather_item,parent,false);
    return new HourlyVH(v);
  }

  @Override
  public void onBindViewHolder(HourlyVH holder, int position) {
    holder.mTimeTv.setText("13:00");
    String uri= WeatherUtils.getWeatherIconURL(mlistWeather.get(position).getIconValue());
    Picasso.with(mContext).load(uri).into(holder.mWeatherIv);
    holder.mDegreeTv.setText(mlistWeather.get(0).getTemp().getTemp()+"");

  }

  @Override
  public int getItemCount() {
    return mlistWeather.size();
  }

  class HourlyVH extends RecyclerView.ViewHolder{
    @BindView(R.id.tv_time_weather)
    TextView mTimeTv;
    @BindView(R.id.iv_weather_ic)
    ImageView mWeatherIv;
    @BindView(R.id.tv_degree_hourly)
    TextView mDegreeTv;

    public HourlyVH(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }

  }
}
