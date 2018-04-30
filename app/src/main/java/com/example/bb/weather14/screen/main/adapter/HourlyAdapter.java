package com.example.bb.weather14.screen.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bb.weather14.R;
import com.example.bb.weather14.utils.WeatherUtils;
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
  private OnHourlyItemClicked clicked;
  public HourlyAdapter(Context context, List<HourlyDTO> hourlyDTOS,OnHourlyItemClicked clicked) {
    this.mlistWeather= hourlyDTOS;
    this.mContext=context;
    this.clicked=clicked;
  }

  @Override
  public HourlyVH onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = View.inflate(mContext, R.layout.item_temp_hourly, null);
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    v.setLayoutParams(params);
    return new HourlyVH(v);
  }

  @Override
  public void onBindViewHolder(HourlyVH holder, final int position) {
    HourlyDTO hourlyDTO = mlistWeather.get(position);
    holder.mTimeTv.setText(hourlyDTO.getDateTime().substring(11,16));
    String uri = WeatherUtils.getWeatherIconURL(hourlyDTO.getIconValue());
    Picasso.with(mContext).load(uri).into(holder.mWeatherIv);
    holder.mDegreeTv.setText(hourlyDTO.getRainProbability() + "%");
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        clicked.onItemClicked(position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mlistWeather.size();
  }

  class HourlyVH extends RecyclerView.ViewHolder {
    @BindView(R.id.hour_tv)
    TextView mTimeTv;
    @BindView(R.id.hourly_temp_iv)
    ImageView mWeatherIv;
    @BindView(R.id.hourly_rain_tv)
    TextView mDegreeTv;

    public HourlyVH(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

  }

  public interface OnHourlyItemClicked{
     void onItemClicked(int position);
  }
}
