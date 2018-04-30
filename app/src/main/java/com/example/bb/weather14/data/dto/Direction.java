package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class Direction {
  @SerializedName("Degrees")
  int degree;
  @SerializedName("Localized")
  String localName;
  @SerializedName("English")
  String englishName;

  public int getDegree() {
    return degree;
  }

  public String getLocalName() {
    return localName;
  }

  public String getEnglishName() {
    return englishName;
  }
}
