package com.example.bb.bachcore.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.TextView;

import com.example.bb.bachcore.R;


/**
 * Dialog Utils
 * Created by neo on 2/15/2016.
 */
public class DialogUtils {

  private static ProgressDialog sProgress;


  public static void showProgressDialog(final Activity activity) {

    try {

      dismissProgressDialog();
      if (!isValidContext(activity)) {
        return;
      }

      if (sProgress != null && sProgress.isShowing()) {
        sProgress.dismiss();
      }
      sProgress = new ProgressDialog(activity, R.style.AppCompatAlertDialogStyle);
      sProgress.show();
      sProgress.setTitle("");
      sProgress.setMessage(activity.getString(R.string.loading));
      sProgress.setCancelable(false);
      sProgress.setIndeterminate(true);
      sProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void showProgressDialog(final Context context) {
    try {
      dismissProgressDialog();
      if (!isValidContext(context)) {
        return;
      }

      sProgress = ProgressDialog.show(context, "", context.getString(R.string.loading),
          true, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Dismiss progress dialog
   */
  public static void dismissProgressDialog() {
    try {
      if (sProgress != null && sProgress.isShowing()) {
        sProgress.dismiss();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean isValidContext(Context context) {
    if (context == null) {
      return false;
    }

    if (context instanceof Activity && ((Activity) context).isFinishing()) {
      return false;
    }

    return true;
  }

}