package com.example.bb.weather14.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.example.bb.bachcore.activity.ContainerActivity;
import com.example.bb.weather14.R;
import com.example.bb.weather14.screen.location.LocationFragment;
import com.example.bb.weather14.screen.rada.RadaFragmnet;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by BB on 3/16/2018.
 */

public class MainActivity extends ContainerActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    public Fragment onCreateFirstFragment() {
        return new MainFragment(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void showMenu() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    public void closeMenu() {
        mDrawerLayout.closeDrawers();
    }

    @OnClick(R.id.rada_bt)
    public void showRada() {
        new RadaFragmnet(this).pushView(true);
        closeMenu();
    }

    @OnClick(R.id.location_bt)
    public void showLoacation() {
        new LocationFragment(this).pushView(true);
        closeMenu();
    }

}
