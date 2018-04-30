package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/25/2018.
 */

public class Past6HourRangeDTO {

  @SerializedName("Minimum")
  private Elevation minElevation;

  @SerializedName("Maximum")
  private Elevation maxElevation;

  public Elevation getMinElevation() {
    return minElevation;
  }

  public Elevation getMaxElevation() {
    return maxElevation;
  }
}
