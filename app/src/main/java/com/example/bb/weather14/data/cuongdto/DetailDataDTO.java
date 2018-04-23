package com.example.bb.weather14.data.cuongdto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by administrator on 4/18/18.
 */

public class DetailDataDTO {
  @SerializedName("Temperature")
  WeatherUnitDTO temp;
  @SerializedName("RealFeelTemperature")
  WeatherUnitDTO realFeelTemp;
  @SerializedName("Wind")
  WindDTO temperature;
  @SerializedName("Rain")
  WeatherUnitDTO rain;
  @SerializedName("Snow")
  WeatherUnitDTO snow;
  @SerializedName("Ice")
  WeatherUnitDTO ice;
  @SerializedName("UVIndex")
  int uvIndex;
  @SerializedName("UVIndexText")
  String uvIndexLevel;


}
