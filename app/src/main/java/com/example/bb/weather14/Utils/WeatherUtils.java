package com.example.bb.weather14.Utils;

/**
 * Created by Windows 18 on 4/21/2018.
 */

public class WeatherUtils {
   public int fromFtoC(float fDegree){
     float cDegree =(fDegree-32)*(5/9);
     return Math.round(cDegree);
   }

   public int fromMiletoKm(float miles){
     double km=miles*(0.621371);
     return (int) Math.round(km);
   }

   public double fromInchToMM(float inch){
     double mm=inch*(25.4);
     return mm;
   }
}
