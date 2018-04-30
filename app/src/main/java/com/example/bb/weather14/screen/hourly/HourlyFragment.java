package com.example.bb.weather14.screen.hourly;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.DialogUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.customview.CustomHeaderView;
import com.example.bb.weather14.data.cuongdto.HourlyDTO;
import com.example.bb.weather14.screen.main.HourlyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Windows 18 on 4/24/2018.
 */

@SuppressLint("ValidFragment")
public class HourlyFragment extends BaseFragment {
  @BindView(R.id.vpHourly)
  ViewPager hourlyVp;
  @BindView(R.id.header)
  CustomHeaderView header;
  private List<HourlyDTO> mListData=new ArrayList<HourlyDTO>();
  private int postion;

  public HourlyFragment(ContainerView mContainerView) {
    super(mContainerView);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_hourly_weather;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    DialogUtils.dismissProgressDialog();
  }

  @Override
  protected void initLayout() {
    DialogUtils.showProgressDialog(getContext());
    hourlyVp.setAdapter(new HourlyDetailAdapter(getContext(),mListData));
    hourlyVp.setCurrentItem(postion);
    header.getmBackIv().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mContainerView.back();
      }
    });
  }

  public HourlyFragment setData(List<HourlyDTO> data,int position){
    this.mListData=data;
    this.postion=position;
    return this;
  }
}
