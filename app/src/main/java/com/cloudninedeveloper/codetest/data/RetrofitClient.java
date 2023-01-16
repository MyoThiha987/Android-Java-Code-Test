package com.cloudninedeveloper.codetest.data;


import com.cloudninedeveloper.codetest.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author myothiha
 * Created 15/01/2023 at 3:56 PM.
 **/

public class RetrofitClient {

    private static Retrofit retrofitInstance;

    public static Retrofit getRetrofit() {
        if (retrofitInstance == null) {
            //if Retrofit instance is null and then create new retrofit
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }
}

