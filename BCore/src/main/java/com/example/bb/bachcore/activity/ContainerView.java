package com.example.bb.bachcore.activity;

import android.support.v4.app.Fragment;

/**
 * Created by BB on 3/9/2018.
 */

public interface ContainerView {

  Fragment onCreateFirstFragment();

  void pushView(Fragment view, boolean addToBackStack);

  void back();
}
