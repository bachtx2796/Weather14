package com.example.bb.weather14.data.dto;

import com.google.firebase.storage.StorageException;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/23/2018.
 */

public class TempDetailDTO {
  @SerializedName("WeatherText")
  private String weatherText;
  @SerializedName("WeatherIcon")
  private int weatherIcon;
  @SerializedName("IsDayTime")
  private boolean isDayTime;
  @SerializedName("Temperature")
  private Elevation temperature;
  @SerializedName("Wind")
  private WindDTO wind;
  @SerializedName("TemperatureSummary")
  private TemperatureSummaryDTO temperatureSummary;
  @SerializedName("RelativeHumidity")
  private int relativeHumidity; // do am
  @SerializedName("UVIndex")
  private int uvIndex; // cuc tim
  @SerializedName("CloudCover")
  private int cloudCover; //may phu
  @SerializedName("Pressure")
  private Elevation pressure;

  public int getCloudCover() {
    return cloudCover;
  }

  public Elevation getPressure() {
    return pressure;
  }

  public int getRelativeHumidity() {
    return relativeHumidity;
  }

  public int getUvIndex() {
    return uvIndex;
  }

  public TemperatureSummaryDTO getTemperatureSummary() {
    return temperatureSummary;
  }

  public String getWeatherText() {
    return weatherText;
  }

  public void setWeatherText(String weatherText) {
    this.weatherText = weatherText;
  }

  public int getWeatherIcon() {
    return weatherIcon;
  }

  public void setWeatherIcon(int weatherIcon) {
    this.weatherIcon = weatherIcon;
  }

  public boolean isDayTime() {
    return isDayTime;
  }

  public void setDayTime(boolean dayTime) {
    isDayTime = dayTime;
  }

  public Elevation getTemperature() {
    return temperature;
  }

  public void setTemperature(Elevation temperature) {
    this.temperature = temperature;
  }

  public WindDTO getWind() {
    return wind;
  }

  public void setWind(WindDTO wind) {
    this.wind = wind;
  }
}
