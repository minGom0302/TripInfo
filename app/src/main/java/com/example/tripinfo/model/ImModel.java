package com.example.tripinfo.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.tripinfo.dto.ImInfo;
import com.example.tripinfo.etc.RetrofitAPI;
import com.example.tripinfo.etc.RetrofitClient;
import com.example.tripinfo.etc.ServiceKeyGroup;
import com.example.tripinfo.presenter.ImPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImModel {
    ImInfo imInfo;
    RetrofitAPI api;
    SharedPreferences sp;
    ImPresenter presenter;

    public ImModel(Context context, ImPresenter presenter) {
        api = RetrofitClient.getRetrofit();
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        this.presenter = presenter;
    }

    public void getData() {
        String serviceKey = ServiceKeyGroup.getImKey();
        String returnType = "JSON";
        String numOfRows = "10";
        String pageNo = "1";
        String countryName = sp.getString("countryName", "");

        api.getImInfo(serviceKey, returnType, numOfRows, pageNo, countryName).enqueue(new Callback<ImInfo>() {
            @Override
            public void onResponse(@NonNull Call<ImInfo> call, @NonNull Response<ImInfo> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        imInfo = response.body();
                    }
                }

                presenter.getImInfo();
            }

            @Override
            public void onFailure(@NonNull Call<ImInfo> call, @NonNull Throwable t) {
                presenter.internetError();
            }
        });
    }

    public ImInfo.ImData getImInfo() {
        ImInfo.ImData imData = null;
        if(imInfo.getData().size() != 0) {
            imData = imInfo.getData().get(0);
            imData.setHave_yn(imData.getHave_yn().replace("Y", "가능").replace("X", "불가능"));
            imData.setGnrl_pspt_visa_yn(imData.getGnrl_pspt_visa_yn().replace("Y","가능").replace("X", "불가능"));
            imData.setGnrl_pspt_visa_cn(imData.getGnrl_pspt_visa_cn().replace("X", "없음"));
            imData.setOfclpspt_visa_yn(imData.getOfclpspt_visa_yn().replace("Y","가능").replace("X", "불가능"));
            imData.setOfclpspt_visa_cn(imData.getOfclpspt_visa_cn().replace("X", "없음"));
            imData.setDplmt_pspt_visa_yn(imData.getDplmt_pspt_visa_yn().replace("Y","가능").replace("X", "불가능"));
            imData.setDplmt_pspt_visa_cn(imData.getDplmt_pspt_visa_cn().replace("X", "없음"));
            imData.setNvisa_entry_evdc_cn(imData.getNvisa_entry_evdc_cn().replace("X", "없음"));
        }
        return imData;
    }
}
