package com.example.bb.weather14.screen.location;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bb.weather14.R;
import com.example.bb.weather14.data.dto.SugguestLocation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BB on 4/7/2018.
 */

public class MyLocationAdapter extends RecyclerView.Adapter {

  private Context mContext;
  private List<SugguestLocation> mSugguestLocations;
  private OnClickTrashhListener onClickTrashhListener;

  public void setOnClickTrashhListener(OnClickTrashhListener onClickTrashhListener) {
    this.onClickTrashhListener = onClickTrashhListener;
  }

  public MyLocationAdapter(Context mContext, List<SugguestLocation> mSugguestLocations) {
    this.mContext = mContext;
    this.mSugguestLocations = mSugguestLocations;
  }

  public class MyLocationHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.trash_bt)
    ImageView mTrashBt;

    public MyLocationHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(mContext, R.layout.item_my_location, null);
    return new MyLocationHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    MyLocationHolder myLocationHolder = (MyLocationHolder) holder;
    myLocationHolder.mTrashBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onClickTrashhListener.onClickTrash(position);
      }
    });

    myLocationHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onClickTrashhListener.onItemClick(mSugguestLocations.get(position).getKey());
      }
    });
    myLocationHolder.mNameTv.setText(mSugguestLocations.get(position).getDescription());
  }

  @Override
  public int getItemCount() {
    return mSugguestLocations.size();
  }

  public interface OnClickTrashhListener {
    void onItemClick(String des);

    void onClickTrash(int pos);
  }
}
