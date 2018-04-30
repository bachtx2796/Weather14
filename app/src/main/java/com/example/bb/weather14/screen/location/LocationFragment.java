package com.example.bb.weather14.screen.location;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.DialogUtils;
import com.example.bb.bachcore.utils.RecyclerUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.data.ServiceBuilder;
import com.example.bb.weather14.data.dto.LocationDTO;
import com.example.bb.weather14.data.dto.PredictionPlaces;
import com.example.bb.weather14.data.dto.SugguestLocation;
import com.example.bb.weather14.pref.PrefWrapper;
import com.example.bb.weather14.screen.searchlocation.SearchLocationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by administrator on 4/2/18.
 */

@SuppressLint("ValidFragment")
public class LocationFragment extends BaseFragment {

  @BindView(R.id.location_rv)
  RecyclerView mLocationRv;

  private List<SugguestLocation> sugguestLocations;
  private MyLocationAdapter myLocationAdapter;
  private OnItemClickListener mOnItemClickListener;

  public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
    this.mOnItemClickListener = mOnItemClickListener;
  }

  public LocationFragment(ContainerView mContainerView) {
    super(mContainerView);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_location;
  }

  @Override
  protected void initLayout() {
    RecyclerUtils.setupVerticalRecyclerView(getContext(), mLocationRv);
    sugguestLocations = new ArrayList<>();
    myLocationAdapter = new MyLocationAdapter(getContext(), sugguestLocations);
    myLocationAdapter.setOnClickTrashhListener(new MyLocationAdapter.OnClickTrashhListener() {
      @Override
      public void onItemClick(String des, String name) {
        mOnItemClickListener.onItemClick(des, name);
        back();
      }

      @Override
      public void onClickTrash(int pos) {
        sugguestLocations.remove(pos);
        PrefWrapper.savePlaces(getContext(), new PredictionPlaces(sugguestLocations));
        myLocationAdapter.notifyDataSetChanged();
      }
    });
    mLocationRv.setAdapter(myLocationAdapter);
  }

  @OnClick(R.id.back_iv)
  public void back() {
    getActivity().onBackPressed();
  }

  @OnClick(R.id.add_location_bt)
  public void addLocation() {
    new SearchLocationFragment(mContainerView).pushView(true);
  }

  @Override
  public void onDisplay() {
    super.onDisplay();
    initPlaces();
  }

  private void initPlaces() {
    if (PrefWrapper.getPlaces(getContext()) == null || PrefWrapper.getPlaces(getContext()).getmSugguestLocations() == null) {
      //Do nothing
    } else {
      sugguestLocations.clear();
      sugguestLocations.addAll(PrefWrapper.getPlaces(getContext()).getmSugguestLocations());
      myLocationAdapter.notifyDataSetChanged();
    }
  }

  public interface OnItemClickListener {
    void onItemClick(String locationKey, String name);
  }
}
