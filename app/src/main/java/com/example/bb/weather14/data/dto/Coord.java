
package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

public class Coord {

  @SerializedName("lat")
  private Double mLat;
  @SerializedName("lng")
  private Double mLng;

  public Double getLat() {
    return mLat;
  }

  public void setLat(Double lat) {
    mLat = lat;
  }

  public Double getLng() {
    return mLng;
  }

  public void setLng(Double lng) {
    mLng = lng;
  }

}
