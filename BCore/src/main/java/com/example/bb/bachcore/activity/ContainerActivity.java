package com.example.bb.bachcore.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.bb.bachcore.ProjectDefault.CoreDefault;
import com.example.bb.bachcore.R;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.ActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BB on 3/7/2018.
 */

public abstract class ContainerActivity extends AppCompatActivity implements ContainerView {

  private FragmentManager.OnBackStackChangedListener mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
    @Override
    public void onBackStackChanged() {
      onFragmentDisplay();
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);
    initLayout();
    getSupportFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
  }



  @Override
  protected void onDestroy() {
    super.onDestroy();
    getSupportFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
  }

  private void initLayout() {
    pushView(onCreateFirstFragment(), false);
  }

  public void pushView(Fragment view, boolean addToBackStack) {
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), view, CoreDefault.FRAGMENT_CONTAINER_ID,
        addToBackStack, view.getClass().getSimpleName());
  }

  @Override
  public void back() {
    FragmentManager manager = getSupportFragmentManager();
    if (manager.getBackStackEntryCount() > 0) {
      manager.popBackStack();
    } else {
      finish();
    }
  }

  @Override
  public void onBackPressed() {
    back();
  }

  public static Fragment getTopFragment(FragmentManager manager) {
    if (manager == null) {
      return null;
    }
    if (manager.getBackStackEntryCount() > 0) {
      String fragmentName = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
      @SuppressLint("RestrictedApi") List<Fragment> fragments = manager.getFragments();
      if (fragments != null && !fragments.isEmpty()) {
        Fragment topFragment = null;
        int i = 1;
        while (i < fragments.size() &&
            (topFragment == null || !isSameClass(topFragment, fragmentName))) {

          topFragment = fragments.get(fragments.size() - i);
          i++;
        }
        return topFragment;
      }
    } else {
      @SuppressLint("RestrictedApi") List<Fragment> fragments = manager.getFragments();
      if (fragments != null && !fragments.isEmpty()) {
        return fragments.get(0);
      }
    }
    return null;
  }

  private static boolean isSameClass(Fragment topFragment, String fragmentName) {
    String simpleName = topFragment.getClass().getSimpleName();
    return simpleName.equals(fragmentName);
  }

  private void onFragmentDisplay() {
    new Handler().post(new Runnable() {
      @Override
      public void run() {
        Fragment fragment = getTopFragment(getSupportFragmentManager());
        if (fragment instanceof BaseFragment) {
          ((BaseFragment) fragment).onDisplay();
        }
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    onFragmentDisplay();
  }

  @SuppressLint("RestrictedApi")
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    FragmentManager manager = getSupportFragmentManager();
    if (manager != null && manager.getFragments() != null && !manager.getFragments().isEmpty()) {
      for (Fragment fragment : manager.getFragments()) {
        if (fragment != null) {
          fragment.onActivityResult(requestCode, resultCode, data);
        }
      }
    }
  }

  public int getLayoutId() {
    return R.layout.container;
  }

}
