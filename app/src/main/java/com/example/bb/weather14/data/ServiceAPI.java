package com.example.bb.weather14.data;

import com.example.bb.weather14.data.cuongdto.HourlyDTO;
import com.example.bb.weather14.data.dto.Coord;
import com.example.bb.weather14.data.dto.GoogleMapSearchDTO;
import com.example.bb.weather14.data.dto.LocationDTO;
import com.example.bb.weather14.pref.PrefWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Windows 18 on 4/14/2018.
 */

public interface ServiceAPI {
  @GET("/locations/v1/cities/geoposition/search")
  Call<LocationDTO> getLocation(@Query("q") String latlngLocation,
                                @Query("details") boolean detail,
                                @Query("toplevel") boolean topLevel);

  @GET("/forecasts/v1/hourly/12hour/{locationkey}")
  Call<List<HourlyDTO>> getHourlyWeather(@Path("locationkey") String locationKey,
                                         @Query("details") boolean detail,
                                         @Query("metric") boolean metric);

}
