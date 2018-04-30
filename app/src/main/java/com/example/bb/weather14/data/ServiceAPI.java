package com.example.bb.weather14.data;

import com.example.bb.weather14.data.cuongdto.HourlyDTO;
import com.example.bb.weather14.data.dto.Coord;
import com.example.bb.weather14.data.dto.GoogleMapSearchDTO;
import com.example.bb.weather14.data.dto.LocationDTO;
import com.example.bb.weather14.data.dto.TempDailyDTO;
import com.example.bb.weather14.data.dto.TempDetailDTO;
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

  @GET("http://dataservice.accuweather.com/locations/v1/search")
  Call<List<LocationDTO>> getLocationKeyByName(@Query("q") String name,
                                               @Query("details") boolean details,
                                               @Query("offset") int offset);

  @GET("/locations/v1/cities/geoposition/search")
  Call<LocationDTO> getLocation(@Query("q") String latlngLocation,
                                @Query("details") boolean detail,
                                @Query("toplevel") boolean topLevel);

  @GET("/forecasts/v1/hourly/12hour/{location_key}")
  Call<List<HourlyDTO>> getHourlyWeather(@Path("location_key") String locationKey,
                                         @Query("details") boolean detail,
                                         @Query("metric") boolean metric);

  @GET("/currentconditions/v1/{location_key}")
  Call<List<TempDetailDTO>> getCurrentTempDetail(@Path("location_key") String key,
                                                 @Query("details") boolean details);

  @GET("/forecasts/v1/daily/5day/{location_key}")
  Call<TempDailyDTO> getTempDaily(@Path("location_key") String key,
                                  @Query("details") boolean details,
                                  @Query("metric") boolean metric);

}
