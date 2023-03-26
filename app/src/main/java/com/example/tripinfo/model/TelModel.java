package com.example.tripinfo.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.tripinfo.contract.TelContract;
import com.example.tripinfo.dto.TelInfo;
import com.example.tripinfo.etc.RetrofitAPI;
import com.example.tripinfo.etc.RetrofitClient;
import com.example.tripinfo.etc.ServiceKeyGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelModel {
    TelContract.Presenter presenter;
    SharedPreferences sp;
    RetrofitAPI api;
    TelInfo telInfo;

    public TelModel(Context context, TelContract.Presenter presenter) {
        api = RetrofitClient.getRetrofit();
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        this.presenter = presenter;
    }

    public void getDataInfo() {
        String serviceKey = ServiceKeyGroup.getTelKey();
        String returnType = "JSON";
        String numOfRows = "20";
        String pageNo = "1";
        String countryName = sp.getString("countryName", "");

        api.getTel(serviceKey, returnType, numOfRows, pageNo, countryName).enqueue(new Callback<TelInfo>() {
            @Override
            public void onResponse(@NonNull Call<TelInfo> call, @NonNull Response<TelInfo> response) {
                if(response.isSuccessful()) {
                    telInfo = response.body();
                    presenter.getReturnData();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TelInfo> call, @NonNull Throwable t) {
                presenter.internetError();
            }
        });
    }

    public TelInfo.dataInfo getDataInfoMore() {
        TelInfo.dataInfo dataInfo = null;

        if(telInfo.getaDta().size() != 0) {
            dataInfo = telInfo.getaDta().get(0);
            String data = dataInfo.getContact_remark().replaceAll("&nbsp;", "");
            boolean booleanValue = true;
            StringBuilder changeData = new StringBuilder();

            int j = dataInfo.getContact_remark().length();

            try {
                for (int i = 0; i < j; i++) {
                    String alpha = String.valueOf(data.charAt(i));

                    if (alpha.equals("<")) {
                        booleanValue = false;
                    } else if (alpha.equals(">")) {
                        booleanValue = true;
                        alpha = "";
                    }

                    if (booleanValue) {
                        changeData.append(alpha);
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            data = changeData.toString();
            dataInfo.setContact_remark(data);
        }

        return dataInfo;
    }
}
