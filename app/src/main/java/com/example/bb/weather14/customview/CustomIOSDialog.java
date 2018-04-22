package com.example.bb.weather14.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.bb.weather14.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Windows 18 on 4/22/2018.
 */

public class CustomIOSDialog extends Dialog {
  private Context mContext;
  TextView tvContent;

  TextView tvTitle;

  TextView tvNegative;

  TextView tvPossitive;
  private OnDialogClicked clicked;
  private String negative;
  private String possitive;

  public CustomIOSDialog(@NonNull Context context, OnDialogClicked clicked, String negativeText, String possitiveText) {
    super(context);
    this.mContext = context;
    this.clicked = clicked;
    negative = negativeText;
    possitive = possitiveText;
    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.view_custom_dialog);
    Window window=getWindow();
    if (window != null) {
      window.setGravity(Gravity.CENTER);
      window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    init();
  }

  private void init() {
    tvPossitive=findViewById(R.id.tv_possitive_dialog);
    tvNegative=findViewById(R.id.tv_negative_dialog);
    tvTitle=findViewById(R.id.tv_title_dialog);
    tvContent=findViewById(R.id.tv_content_dialog);
    tvNegative.setText(negative);
    tvPossitive.setText(possitive);
    tvNegative.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clicked.negativeClicked();
      }
    });
    tvPossitive.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clicked.possitiveClicked();
      }
    });
    Window window=this.getWindow();
    window.setLayout(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
    window.setGravity(Gravity.CENTER);
  }

  public void setTitleAndContent(String title, String content) {
    tvContent.setText(content);
    tvTitle.setText(title);
  }

  public interface OnDialogClicked {
    void negativeClicked();

    void possitiveClicked();
  }
}
