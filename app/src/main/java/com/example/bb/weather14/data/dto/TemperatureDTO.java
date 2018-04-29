package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/28/2018.
 */

public class TemperatureDTO {

  @SerializedName("Minimum")
  private Metric minTemp;
  @SerializedName("Maximum")
  private Metric maxTemp;

  public Metric getMinTemp() {
    return minTemp;
  }

  public Metric getMaxTemp() {
    return maxTemp;
  }
}
