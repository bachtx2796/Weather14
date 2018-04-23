package com.example.bb.weather14.data.cuongdto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class WindDirection {
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
