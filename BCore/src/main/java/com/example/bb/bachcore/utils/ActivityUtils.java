package com.example.bb.bachcore.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by BB on 3/7/2018.
 */

public class ActivityUtils {

    public static <T extends Activity> void startActivity(Context context, Class<T> clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    public static <T extends Activity> void startActivity(Context context, Class<T> clazz,
                                                          Bundle extras) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

        addFragmentToActivity(fragmentManager, fragment, frameId, false, null);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean
                                                     addToBackStack, String tag) { // push fragment as view

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
