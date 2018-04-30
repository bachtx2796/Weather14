package com.example.bb.weather14.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.utils.StringUtils;
import com.example.bb.weather14.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.bb.weather14.R.styleable.CustomHeaderView_custom_hearder_background;

/**
 * Created by administrator on 4/2/18.
 */

public class CustomHeaderView extends LinearLayout {

    @BindView(R.id.title_tv)
    public TextView mTitleTv;
    @BindView(R.id.action_iv)
    ImageView mActionIv;
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.custom_layout)
    LinearLayout mCusLinearLayout;
    private ContainerView mContainer;
    public CustomHeaderView(Context context, ContainerView containerView) {
        super(context);
        init(null, 0);
        mContainer=containerView;
    }

    public CustomHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_header_view, this, true);

        ButterKnife.bind(this);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomHeaderView, defStyle, 0);
        String text = a.getString(R.styleable.CustomHeaderView_custom_header_title);
        if (!StringUtils.isEmpty(text)) {
            mTitleTv.setVisibility(VISIBLE);
            mTitleTv.setText(text);
        } else {
            //mTitleTv.setVisibility(GONE);
        }

        if (a.hasValue(CustomHeaderView_custom_hearder_background)) {
            mCusLinearLayout.setBackgroundColor(a.getColor(CustomHeaderView_custom_hearder_background, Color.TRANSPARENT));
        }

        if (a.hasValue(R.styleable.CustomHeaderView_custom_header_left_action_ic_src)) {
            mBackIv.setImageDrawable(a.getDrawable(
                    R.styleable.CustomHeaderView_custom_header_left_action_ic_src));
        }

        if (a.hasValue(R.styleable.CustomHeaderView_custom_header_action_ic_src)) {
            mActionIv.setImageDrawable(a.getDrawable(
                    R.styleable.CustomHeaderView_custom_header_action_ic_src));
        } else {
            mActionIv.setVisibility(GONE);
        }
        a.recycle();
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public ImageView getmBackIv() {
        return mBackIv;
    }

    public String getTitle() {
        return mTitleTv.getText().toString();
    }
}
