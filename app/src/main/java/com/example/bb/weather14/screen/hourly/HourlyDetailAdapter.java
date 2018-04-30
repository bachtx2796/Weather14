package com.example.bb.weather14.screen.hourly;

import android.annotation.SuppressLint;
import android.content.Context;

import android.support.v4.view.PagerAdapter;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bb.weather14.R;
import com.example.bb.weather14.customview.ItemParameterHourlyDetail;
import com.example.bb.weather14.data.cuongdto.HourlyDTO;
import com.example.bb.weather14.data.cuongdto.WeatherUnitDTO;
import com.example.bb.weather14.data.cuongdto.WindDTO;
import com.example.bb.weather14.utils.DateTimeUtil;
import com.example.bb.weather14.utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows 18 on 4/24/2018C.
 */

public class HourlyDetailAdapter extends PagerAdapter {
  private List<HourlyDTO> listData=new ArrayList<>();
  private Context mContext;
  private TextView temp;
  private ItemParameterHourlyDetail realTemp;
  private ItemParameterHourlyDetail humiditi;
  private ItemParameterHourlyDetail wind;
  private ItemParameterHourlyDetail windDirection;
  private ItemParameterHourlyDetail windGust;
  private ItemParameterHourlyDetail rainProb;
  private ItemParameterHourlyDetail rainValue;
  private ItemParameterHourlyDetail iceProb;
  private ItemParameterHourlyDetail iceValue;
  private ItemParameterHourlyDetail snowProb;
  private ItemParameterHourlyDetail snowValue;
  private ItemParameterHourlyDetail visibility;
  private ItemParameterHourlyDetail uv;
  private ItemParameterHourlyDetail cloudCover;
  private TextView weatherStatus;
  private ImageView ivDrop;
  private GridLayout glData;
  private TextView tvCurrentTime;
  private LinearLayout llHourly;
  private TextView tvhumi;
  private TextView tvRain;
  private ImageView ivStatus;

  public HourlyDetailAdapter(Context c,List<HourlyDTO> dtos) {
    this.listData=dtos;
    this.mContext=c;
  }

  @Override
  public Object instantiateItem(ViewGroup container, final int position) {
    final View v= LayoutInflater.from(mContext).inflate(R.layout.item_hourly_details,container,false);
    temp=v.findViewById(R.id.tv_hourly_detail_temp);
    ivStatus=v.findViewById(R.id.iv_status);
    realTemp=v.findViewById(R.id.iphRealFeel);
    llHourly=v.findViewById(R.id.ll_hourly_detail);
    wind=v.findViewById(R.id.iphWind);
    windDirection=v.findViewById(R.id.iphWindDirection);
    windGust=v.findViewById(R.id.iphWindGust);
    humiditi=v.findViewById(R.id.iphHumidi);
    rainProb=v.findViewById(R.id.iphRainProb);
    rainValue=v.findViewById(R.id.iphRainValue);
    snowProb=v.findViewById(R.id.iphSnowProb);
    snowValue=v.findViewById(R.id.iphSnowValue);
    iceProb=v.findViewById(R.id.iphIceProb);
    iceValue=v.findViewById(R.id.iphIceValue);
    ivDrop=v.findViewById(R.id.iv_detail);
    tvCurrentTime=v.findViewById(R.id.tv_current_time);
    tvRain=v.findViewById(R.id.tv_rain_prob);
    tvhumi=v.findViewById(R.id.tv_humi);
    ivDrop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        glData=v.findViewById(R.id.glDetailData);
        if(glData.getVisibility() == View.VISIBLE) {
          rotate((ImageView) view, 180F, 360F);
          glData.setVisibility(View.GONE);
        }else {
          rotate((ImageView) view,0F,180F);
          glData.setVisibility(View.VISIBLE);
        }
      }
    });
    cloudCover=v.findViewById(R.id.iphCloudCover);
    uv=v.findViewById(R.id.iphUvIndex);
    visibility=v.findViewById(R.id.iphVisibility);
    weatherStatus=v.findViewById(R.id.tv_weather_status);
    bindData(listData.get(position));
    container.addView(v);
    return v;
  }

  @Override
  public int getCount() {
    return listData.size();
  }


  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((LinearLayout) object);
  }

  @SuppressLint("SetTextI18n")
  public void bindData(HourlyDTO data){
    tvCurrentTime.setText(WeatherUtils.getCurrentTime(data.getDateTime()));
    if(data.isDayLight()){
      llHourly.setBackgroundResource(R.drawable.rainny);
    }else {
      llHourly.setBackgroundResource(R.drawable.night);
    }
    tvhumi.setText(data.getHumidity()+"%");
    tvRain.setText(data.getRainProbability()+"%");
    realTemp.setValue(Math.round(data.getRealFeel().getTemp()) + "\u00B0");
    temp.setText(Math.round(data.getTemp().getTemp())+"\u00B0");
    WindDTO windDTO=data.getWind();
    setWeatherData(windDTO.getWindSpeed(),wind);
    windDirection.setValue(windDTO.getWindDirection().getLocalName());
    humiditi.setValue(data.getHumidity()+"%");
    windGust.setValue(data.getWindGust().getSpeed().getTemp()+" "+data.getWindGust().getSpeed().getUnit());
    setWeatherData(data.getVisibility(),visibility);
    uv.setValue(data.getUvIndexText());
    rainProb.setValue(data.getRainProbability()+"%");
    iceProb.setValue(data.getIceProbability()+"%");
    snowProb.setValue(data.getSnowProbability()+"%");
    setWeatherData(data.getSnowAmount(),snowValue);
    setWeatherData(data.getIceAmount(),iceValue);
    setWeatherData(data.getRainAmount(),rainValue);
    cloudCover.setValue(data.getCloudCover()+" %");
    weatherStatus.setText(data.getIconType());
    if(data.getIconType().toLowerCase().contains("mưa")){
      ivStatus.setImageResource(R.drawable.ic_rain);
    }else if(data.getIconType().toLowerCase().contains("mây")){
      ivStatus.setImageResource(R.drawable.ic_cloudy);
    }else {
      ivStatus.setImageResource(R.drawable.ic_shining_sun);
    }
  }

  public void setWeatherData(WeatherUnitDTO data,ItemParameterHourlyDetail target){
    target.setValue(Math.round(data.getTemp())+" "+data.getUnit());
  }

  public void rotate(ImageView target,float from,float to){
    Animation rotateAnim = new RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    rotateAnim.setDuration(200);
    rotateAnim.setRepeatCount(0);
    rotateAnim.setRepeatMode(Animation.REVERSE);
    rotateAnim.setFillAfter(true);
    target.startAnimation(rotateAnim);
  }

}
