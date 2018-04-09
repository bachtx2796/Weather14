package com.example.bb.weather14.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BB on 8/29/2017.
 */

public class GoogleServiceBuilder {

	private static Retrofit sInstance;
	private static GoogleService sService;

	private static Retrofit getRetrofit(String endPoint) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
			.readTimeout(30, TimeUnit.SECONDS)
			.connectTimeout(30, TimeUnit.SECONDS)
			.addInterceptor(interceptor)
			.build();

		if (sInstance == null) {
			Gson gson = new GsonBuilder()
				.create();

			sInstance = new Retrofit.Builder()
				.baseUrl(endPoint)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		}

		return sInstance;
	}

	private static String getBaseUrl() {
		return "https://maps.googleapis.com/maps/";
	}

	public static GoogleService getService() {
		if (sService == null) {
			sService = getRetrofit(getBaseUrl()).create(GoogleService.class);
		}

		return sService;
	}

	public static GoogleService getService(String endPoint) {
		if (sService == null) {
			sService = getRetrofit(endPoint).create(GoogleService.class);
		}

		return sService;
	}
}
