package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/25/2018.
 */

public class TemperatureSummaryDTO {

  @SerializedName("Past6HourRange")
  private Past6HourRangeDTO past6HourRangeDTO;

  public Past6HourRangeDTO getPast6HourRangeDTO() {
    return past6HourRangeDTO;
  }
}
