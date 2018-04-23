package com.example.bb.weather14.data.cuongdto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class WindDTO {
  @SerializedName("Speed")
  WeatherUnitDTO windSpeed;
  @SerializedName("Direction")
  WindDirection windDirection;

  public WeatherUnitDTO getWindSpeed() {
    return windSpeed;
  }

  public WindDirection getWindDirection() {
    return windDirection;
  }

  public void setWindSpeed(WeatherUnitDTO windSpeed) {
    this.windSpeed = windSpeed;
  }

  public void setWindDirection(WindDirection windDirection) {
    this.windDirection = windDirection;
  }
}
