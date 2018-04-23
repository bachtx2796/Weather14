package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/23/2018.
 */

public class TempDetailDTO {
  @SerializedName("WeatherText")
  private String weatherText;
  @SerializedName("WeatherIcon")
  private int weatherIcon;
  @SerializedName("IsDayTime")
  private boolean isDayTime;
  @SerializedName("Temperature")
  private Elevation temperature;
  @SerializedName("Wind")
  private WindDTO wind;
}
