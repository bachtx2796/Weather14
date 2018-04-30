package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BB on 4/26/2018.
 */

public class TempDailyDTO {

  @SerializedName("DailyForecasts")
  private List<DailyDTO> dailyDTOList;

  public List<DailyDTO> getDailyDTOList() {
    return dailyDTOList;
  }
}
