package com.example.bb.weather14.data.cuongdto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class HourlyDTO {
  @SerializedName("DateTime")
  String dateTime;
  @SerializedName("WeatherIcon")
  int iconValue;
  @SerializedName("IconPhrase")
  String iconType;
  @SerializedName("Temperature")
  WeatherUnitDTO temp;
  @SerializedName("RealFeelTemperature")
  WeatherUnitDTO realFeel;
  @SerializedName("Wind")
  WindDTO wind;
  @SerializedName("WindGust")
  WindGust windGust;
  @SerializedName("Visibility")
  WeatherUnitDTO visibility;
  @SerializedName("UVIndex")
  int uvIndex;
  @SerializedName("UVIndexText")
  String uvIndexText;
  @SerializedName("RainProbability")
  String rainProbability;
  @SerializedName("SnowProbability")
  String snowProbability;
  @SerializedName("IceProbability")
  String iceProbability;
  @SerializedName("Rain")
  WeatherUnitDTO rainAmount;
  @SerializedName("Snow")
  WeatherUnitDTO snowAmount;
  @SerializedName("Ice")
  WeatherUnitDTO iceAmount;
  @SerializedName("CloudCover")
  int cloudCover;

  public String getDateTime() {
    return dateTime;
  }

  public int getIconValue() {
    return iconValue;
  }

  public String getIconType() {
    return iconType;
  }

  public WeatherUnitDTO getTemp() {
    return temp;
  }

  public WeatherUnitDTO getRealFeel() {
    return realFeel;
  }

  public WindDTO getWind() {
    return wind;
  }

  public WindGust getWindGust() {
    return windGust;
  }

  public WeatherUnitDTO getVisibility() {
    return visibility;
  }

  public int getUvIndex() {
    return uvIndex;
  }

  public String getUvIndexText() {
    return uvIndexText;
  }

  public String getRainProbability() {
    return rainProbability;
  }

  public String getSnowProbability() {
    return snowProbability;
  }

  public String getIceProbability() {
    return iceProbability;
  }

  public WeatherUnitDTO getRainAmount() {
    return rainAmount;
  }

  public WeatherUnitDTO getSnowAmount() {
    return snowAmount;
  }

  public WeatherUnitDTO getIceAmount() {
    return iceAmount;
  }

  public int getCloudCover() {
    return cloudCover;
  }
}
