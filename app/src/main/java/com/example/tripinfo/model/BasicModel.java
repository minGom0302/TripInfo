package com.example.tripinfo.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tripinfo.dto.CountryInfo;
import com.example.tripinfo.presenter.BasicPresenter;

public class BasicModel {
    BasicPresenter presenter;
    SharedPreferences sp;

    public BasicModel(BasicPresenter presenter, Context context) {
        this.presenter = presenter;
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public CountryInfo getData() {
        String countryName = sp.getString("countryName", "");
        String countryEnName = sp.getString("countryEnName", "");
        String basic = sp.getString("basic", "");
        String continent = sp.getString("continent", "");
        String imgUrl = sp.getString("imgUrl", "");

        basic = basic.replaceAll("<div>", "").replaceAll("<br>", "")
                     .replaceAll("</div>", "").replaceAll("</p>", "")
                     .replaceAll("<p style=\"margin-left: 15px; margin-right: 15px;\">-", "")
                     .replaceAll("<p style=\"margin-left: 20px; margin-right: 20px;\">", "");

        return new CountryInfo(countryName, countryEnName, basic, continent, imgUrl);
    }
}
