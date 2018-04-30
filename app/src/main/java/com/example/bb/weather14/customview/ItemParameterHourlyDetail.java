package com.example.bb.weather14.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bb.weather14.R;

/**
 * Created by Windows 18 on 4/24/2018.
 */

public class ItemParameterHourlyDetail extends LinearLayout {
  private TextView tvValue;
  public ItemParameterHourlyDetail(Context context) {
    super(context);
    init(context,null);
  }

  public ItemParameterHourlyDetail(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context,attrs);
  }

  public ItemParameterHourlyDetail(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  public ItemParameterHourlyDetail(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context,attrs);
  }

  public void init(Context context , AttributeSet attributeSet){
    View v= LayoutInflater.from(context).inflate(R.layout.item_parameter_hourly_detail,this,true);
    TextView title=v.findViewById(R.id.tv_parameter_title);
    tvValue=v.findViewById(R.id.tv_parameter_value);
    TypedArray arr=context.obtainStyledAttributes(attributeSet,R.styleable.ItemParameterHourlyDetail);
    if(arr.hasValue(R.styleable.ItemParameterHourlyDetail_item_parameter_title)){
      title.setText(arr.getString(R.styleable.ItemParameterHourlyDetail_item_parameter_title));
    }
    arr.recycle();
  }

  public void setValue(String value){
    tvValue.setText(value);
  }
}
