package com.example.bb.weather14.screen.searchlocation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.bb.weather14.R;
import com.example.bb.weather14.data.dto.SugguestLocation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BB on 4/7/2018.
 */

public class LocationAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SugguestLocation> mSugguestLocations;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LocationAdapter(Context mContext, List<SugguestLocation> mSugguestLocations) {
        this.mContext = mContext;
        this.mSugguestLocations = mSugguestLocations;
    }

    public class LocationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_tv)
        TextView mNamrTv;

        public LocationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_location,null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LocationHolder holder1 = (LocationHolder) holder;
        holder1.mNamrTv.setText(mSugguestLocations.get(position).getDescription());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSugguestLocations.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
}
