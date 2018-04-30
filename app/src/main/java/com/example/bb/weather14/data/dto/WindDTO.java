package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class WindDTO {
  @SerializedName("Speed")
  Elevation windSpeed;
  @SerializedName("Direction")
  Direction windDirection;

  public Elevation getWindSpeed() {
    return windSpeed;
  }

  public Direction getWindDirection() {
    return windDirection;
  }

  public void setWindSpeed(Elevation windSpeed) {
    this.windSpeed = windSpeed;
  }

  public void setWindDirection(Direction windDirection) {
    this.windDirection = windDirection;
  }
}
