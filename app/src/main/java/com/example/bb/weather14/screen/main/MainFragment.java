package com.example.bb.weather14.screen.main;

import android.annotation.SuppressLint;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.weather14.R;
import com.example.bb.weather14.map.MapManager;

import butterknife.OnClick;

/**
 * Created by BB on 3/16/2018.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment {


    public MainFragment(ContainerView mContainerView) {
        super(mContainerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initLayout() {
        new MapManager(getContext()).turnOnGPS();
    }

    @OnClick(R.id.back_iv)
    public void showSetting() {
        ((MainActivity) mContainerView).showMenu();
    }


}
