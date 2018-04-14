package com.example.bb.weather14.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;


import com.example.bb.weather14.R;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppUtils {
  private static String TAG = AppUtils.class.getSimpleName();

  public static boolean isForeGround(Context context) {
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
    if (appProcesses == null) {
      return false;
    }
    final String packageName = context.getPackageName();
    for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
      if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
        return true;
      }
    }
    return false;
  }


  public static String getVersionApp(Context context) {
    PackageInfo pInfo;
    String version = "";
    try {
      pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      version = pInfo.versionName;
    } catch (Exception e) {
      Logger logger = Logger.getAnonymousLogger();
      logger.log(Level.SEVERE, "an exception was thrown", e);
    }

    return String.format(context.getString(R.string.version_name_format), version);
  }
}
