package com.example.bb.weather14.Utils;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class WeatherUtils {
  public static int fromFtoC(float fDegree) {
    float cDegree = (fDegree - 32) * (5 / 9);
    return Math.round(cDegree);
  }

  public static int fromMiletoKm(float miles) {
    double km = miles * (0.621371);
    return (int) Math.round(km);
  }

  public static double fromInchToMM(float inch) {
    return inch * (25.4);
  }

  public static String getWeatherIconURL(int iconNumber){
    return new String("https://developer.accuweather.com/sites/default/files/"+String.format("%02d",iconNumber)+"-s.png");
  }
}
