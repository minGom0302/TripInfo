package com.example.tripinfo.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.etc.RetrofitAPI;
import com.example.tripinfo.etc.RetrofitClient;
import com.example.tripinfo.etc.ServiceKeyGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WarningModel {
    WarningInfo warningInfo;
    WarningContract.Presenter presenter;
    SharedPreferences sp;
    RetrofitAPI api;

    public WarningModel(Context context, WarningContract.Presenter presenter) {
        api = RetrofitClient.getRetrofit();
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        this.presenter = presenter;
    }

    @SuppressLint("SuspiciousIndentation")
    public void getDataWarning(int cnd) {
        String serviceKey;
        String returnType = "JSON";
        String numOfRows = "20";
        String pageNo = "1";
        String countryName = sp.getString("countryName", "");

        Call<WarningInfo> call = null;
        if(cnd == 0) {
            serviceKey = ServiceKeyGroup.getWarningKey();
            call = api.getWarning(serviceKey, returnType, numOfRows, pageNo, countryName);
        } else if(cnd == 1) {
            serviceKey = ServiceKeyGroup.getWarningAdjustKey();
            call = api.getWarningAdjust(serviceKey, returnType, numOfRows, pageNo, countryName);
        } else if(cnd == 2) {
            serviceKey = ServiceKeyGroup.getSpecialWarningKey();
            call = api.getSpecialWarning(serviceKey, returnType, numOfRows, pageNo, countryName);
        } else if(cnd == 3) {
            serviceKey = ServiceKeyGroup.getSafetyInfoKey();
            call = api.getSafetyInfo(serviceKey, returnType, numOfRows, pageNo, countryName);
        } else if(cnd == 4) {
            serviceKey = ServiceKeyGroup.getSafetyNoticeKey();
            numOfRows = "40";
            call = api.getSafetyNoticeInfo(serviceKey, returnType, numOfRows, pageNo, countryName);
        } else if(cnd == 5) {
            serviceKey = ServiceKeyGroup.getAccidentKey();
            call = api.getAccident(serviceKey, returnType, numOfRows, pageNo, countryName);
        }


        if(call != null)
        call.enqueue(new Callback<WarningInfo>() {
            @Override
            public void onResponse(@NonNull Call<WarningInfo> call, @NonNull Response<WarningInfo> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        warningInfo = response.body();
                    }
                }

                presenter.getWarningInfo();
            }

            @Override
            public void onFailure(@NonNull Call<WarningInfo> call, @NonNull Throwable t) {
                presenter.setInternetError();
            }
        });
    }

    public WarningInfo getWarningInfo(int cnd) {
        if(warningInfo.getData() != null) {
            if (warningInfo.getData().size() != 0 && cnd == 0) {
                int lvl = warningInfo.getData().get(0).getAlarm_lvl();
                String lvlStr = "";
                String lvlInfo = "";
                switch (lvl) {
                    case 1:
                        lvlStr = "1단계(여행유의)";
                        lvlInfo = "신변안전 위험 요인 숙지 및 대비";
                        break;
                    case 2:
                        lvlStr = "2단계(여행자제)";
                        lvlInfo = "(여해예정자) 불필요한 여행 자제\n(체류자) 신변 안전 특별 유의";
                        break;
                    case 3:
                        lvlStr = "3단계(출국권고)";
                        lvlInfo = "(여행예정자) 여행 취소 및 연기\n(체류자) 긴요한 용무가 아닌 한 출국";
                        break;
                    case 4:
                        lvlStr = "4단계(여행금지)";
                        lvlInfo = "(여행예정자) 여행금지 준수\n(체류자) 즉시 대피 및 철수";
                        break;
                }
                warningInfo.getData().get(0).setLvl(lvlStr);
                warningInfo.getData().get(0).setLvlInfo(lvlInfo);
            } else if (warningInfo.getData().size() != 0 && cnd == 1 || cnd == 3) {
                for (WarningInfo.Warning data : warningInfo.getData()) {
                    if (data.getTxt_origin_cn() != null) {
                        data.setTxt_origin_cn(data.getTxt_origin_cn().replaceAll("&nbsp;-", "\n - ").replaceAll("&nbsp;", " ").replaceAll("○", "\n○")
                                .replaceAll("□", "\n□").replaceAll("ㅇ", "\nㅇ").replaceAll("※", "\n※").replaceAll("☞", "\n☞")
                                .replaceAll("\\(1\\)", "\n\\(1\\)").replaceAll("\\(2\\)", "\n\\(2\\)").replaceAll("\\(3\\)", "\n\\(3\\)")
                                .replaceAll("&gt;", "").replaceAll("&lt;", "").replaceAll("①", "\n①").replaceAll("②", "\n②").replaceAll("③", "\n③"));
                    }
                }
            } else if (warningInfo.getData().size() != 0 && (cnd == 4 || cnd == 5)) {
                for (int i = 0; i < warningInfo.getData().size(); i++) {
                    WarningInfo.Warning data = warningInfo.getData().get(i);
                    boolean booleanValue = true;
                    StringBuilder changeData = new StringBuilder();
                    String text;
                    if (cnd == 4) {
                        text = data.getTxt_origin_cn();
                    } else {
                        text = data.getNews();
                    }
                    try {
                        for (int j = 0; j < text.length(); j++) {
                            String alpha = String.valueOf(text.charAt(j));
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


                    text = changeData.toString();
                    text = text.replaceAll("&nbsp;-", "\n - ").replaceAll("&nbsp;", " ").replaceAll("○", "\n○")
                            .replaceAll("□", "\n□").replaceAll("ㅇ", "\nㅇ").replaceAll("※", "\n※").replaceAll("☞", "\n☞")
                            .replaceAll("\\(1\\)", "\n\\(1\\)").replaceAll("\\(2\\)", "\n\\(2\\)").replaceAll("\\(3\\)", "\n\\(3\\)")
                            .replaceAll("&gt;", "").replaceAll("&lt;", "").replaceAll("①", "\n①").replaceAll("②", "\n②").replaceAll("③", "\n③");
                    if (cnd == 4) {
                        warningInfo.getData().get(i).setTxt_origin_cn(text);
                    } else {
                        warningInfo.getData().get(i).setNews(text);
                    }
                }
            }
        }

        return warningInfo;
    }
}
