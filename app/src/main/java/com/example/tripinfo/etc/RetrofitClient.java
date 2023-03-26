package com.example.tripinfo.etc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    static String URL = "http://apis.data.go.kr/1262000/";
    static Gson gson;
    static Retrofit retrofit;

    public static void setGsonAndRetrofit() {
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static RetrofitAPI getRetrofit() {
        return retrofit.create(RetrofitAPI.class);
    }
}
