package com.example.bb.weather14.data.dto;

/**
 * Created by BB on 4/27/2018.
 */

public class TempInDay {

  private int day;
  private int rain;
  private int icon;
  private int maxTemp;
  private int minTemp;

  public TempInDay(int day, int rain, int icon, int maxTemp, int minTemp) {
    this.day = day;
    this.rain = rain;
    this.icon = icon;
    this.maxTemp = maxTemp;
    this.minTemp = minTemp;
  }

  public int getDay() {
    return day;
  }

  public int getRain() {
    return rain;
  }

  public int getIcon() {
    return icon;
  }

  public int getMaxTemp() {
    return maxTemp;
  }

  public int getMinTemp() {
    return minTemp;
  }

  public int getSubTemp() {
    return maxTemp - minTemp;
  }

  public void setMaxTemp(int maxTemp) {
    this.maxTemp = maxTemp;
  }

  public void setMinTemp(int minTemp) {
    this.minTemp = minTemp;
  }
}
