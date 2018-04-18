package com.example.bb.weather14.data;

import com.example.bb.weather14.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Windows 18 on 4/14/2018.
 */

public class ServiceBuilder {
    private static Retrofit retrofit;
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl url = original.url().newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .addQueryParameter("language", "vi")
                    .build();
            Request request = original.newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        }
    }).build();

    public static ServiceAPI getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ServiceAPI.class);
    }
}
