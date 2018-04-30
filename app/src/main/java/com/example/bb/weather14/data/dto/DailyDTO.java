package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/26/2018.
 */

public class DailyDTO {

  @SerializedName("Sun")
  private SunDTO sun;
  @SerializedName("Day")
  private DayDTO day;
  @SerializedName("Temperature")
  private TemperatureDTO temperature;

  public TemperatureDTO getTemperature() {
    return temperature;
  }

  public SunDTO getSun() {
    return sun;
  }

  public DayDTO getDay() {
    return day;
  }
}
