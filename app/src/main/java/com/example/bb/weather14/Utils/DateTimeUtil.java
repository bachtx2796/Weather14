package com.example.bb.weather14.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Windows 18 on 4/22/2018.
 */

public class DateTimeUtil {
  public static String getCurrentDay(){
    Calendar calendar=Calendar.getInstance();
    String format=new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
    return format;
  }
}
