package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/26/2018.
 */

public class DayDTO {

  @SerializedName("Icon")
  private int icon;
  @SerializedName("RainProbability")
  private int rainProbability;
  @SerializedName("Rain")
  private Metric rain;

  public int getRainProbability() {
    return rainProbability;
  }

  public Metric getRain() {
    return rain;
  }

  public int getIcon() {
    return icon;
  }
}
