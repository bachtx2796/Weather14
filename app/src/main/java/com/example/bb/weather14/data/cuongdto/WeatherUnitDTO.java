package com.example.bb.weather14.data.cuongdto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class WeatherUnitDTO {
  @SerializedName("Value")
  float temp;
  @SerializedName("Unit")
  String unit;
  @SerializedName("UnitType")
  int unitType;
}
