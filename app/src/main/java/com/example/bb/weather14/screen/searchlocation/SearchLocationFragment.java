package com.example.bb.weather14.screen.searchlocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.bb.bachcore.activity.ContainerView;
import com.example.bb.bachcore.fragment.BaseFragment;
import com.example.bb.bachcore.utils.ActivityUtils;
import com.example.bb.bachcore.utils.RecyclerUtils;
import com.example.bb.weather14.R;
import com.example.bb.weather14.data.GoogleService;
import com.example.bb.weather14.data.GoogleServiceBuilder;
import com.example.bb.weather14.data.dto.PredictionPlaces;
import com.example.bb.weather14.data.dto.SugguestLocation;
import com.example.bb.weather14.pref.PrefWrapper;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

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
public class SearchLocationFragment extends BaseFragment {

    @BindView(R.id.search_et)
    EditText mSearchEt;
    @BindView(R.id.location_sugguest_rv)
    SuperRecyclerView mLocationSugguestRv;

    private List<SugguestLocation> mSugguestLocations;
    private LocationAdapter adapter;

    public SearchLocationFragment(ContainerView mContainerView) {
        super(mContainerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_location;
    }

    @Override
    protected void initLayout() {

        initRv();

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getLocation(editable.toString());
            }
        });
    }

    private void initRv() {
        RecyclerUtils.setupVerticalRecyclerView(getContext(), mLocationSugguestRv.getRecyclerView());
        mSugguestLocations = new ArrayList<>();
        adapter = new LocationAdapter(getContext(), mSugguestLocations);
        adapter.setOnItemClickListener(new LocationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                ActivityUtils.hideKeyboard(getContext(),mSearchEt);
                PredictionPlaces tmp = PrefWrapper.getPlaces(getContext());
                if (tmp == null) {
                    tmp = new PredictionPlaces(new ArrayList<SugguestLocation>());
                }
                tmp.getmSugguestLocations().add(mSugguestLocations.get(pos));
                PrefWrapper.savePlaces(getContext(), tmp);
                getActivity().onBackPressed();
            }
        });
        mLocationSugguestRv.setAdapter(adapter);
    }

    @OnClick(R.id.delete_bt)
    public void deleteSearch() {
        mSearchEt.setText("");
    }

    private void getLocation(String search) {
        GoogleServiceBuilder.getService()
                .getLocationSuggestion(search, "vi", GoogleService.KEY)
                .enqueue(new Callback<PredictionPlaces>() {
                    @Override
                    public void onResponse(Call<PredictionPlaces> call, Response<PredictionPlaces> response) {
                        if (response.isSuccessful()) {
                            mSugguestLocations.clear();
                            mSugguestLocations.addAll(response.body().getmSugguestLocations());
                            adapter.notifyDataSetChanged();
                        } else {
                            // Do nothing
                        }
                    }

                    @Override
                    public void onFailure(Call<PredictionPlaces> call, Throwable t) {

                    }
                });
    }
}
