
package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;


public class SunDTO {

  @SerializedName("EpochRise")
  private Long mEpochRise;
  @SerializedName("EpochSet")
  private Long mEpochSet;
  @SerializedName("Rise")
  private String mRise;
  @SerializedName("Set")
  private String mSet;

  public Long getEpochRise() {
    return mEpochRise;
  }

  public void setEpochRise(Long EpochRise) {
    mEpochRise = EpochRise;
  }

  public Long getEpochSet() {
    return mEpochSet;
  }

  public void setEpochSet(Long EpochSet) {
    mEpochSet = EpochSet;
  }

  public String getRise() {
    return mRise.substring(11, 16);
  }

  public void setRise(String Rise) {
    mRise = Rise;
  }

  public String getSet() {
    return mSet.substring(11, 16);
  }

  public void setSet(String Set) {
    mSet = Set;
  }

}
