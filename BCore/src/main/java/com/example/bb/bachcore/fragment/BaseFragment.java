package com.example.bb.bachcore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bb.bachcore.ProjectDefault.CoreDefault;
import com.example.bb.bachcore.activity.ContainerView;

import butterknife.ButterKnife;

/**
 * Created by BB on 3/9/2018.
 */

public abstract class BaseFragment extends Fragment {

  protected View mRootView;
  protected int mAnimIn = CoreDefault.ANIM_NONE;
  protected int mAnimOut = CoreDefault.ANIM_NONE;

  protected ContainerView mContainerView;

  protected BaseFragment(ContainerView mContainerView) {
    this.mContainerView = mContainerView;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mRootView = inflater.inflate(getLayoutId(), container, false);

    ButterKnife.bind(this, mRootView);
    initLayout();

    mRootView.setClickable(true);
    return mRootView;
  }

  protected abstract int getLayoutId();

  protected abstract void initLayout();

  public BaseFragment setAnimOut(int animOut) {
    mAnimOut = animOut;
    return this;
  }

  /**
   * Set enter animation
   */
  public BaseFragment setAnimIn(int animIn) {
    mAnimIn = animIn;
    return this;
  }

  public void pushView(boolean addToBackStack) {
    mContainerView.pushView(this, addToBackStack);
  }

  public void onDisplay() {
    Log.e(getClass().getSimpleName(), "onDisplay");
  }
}
