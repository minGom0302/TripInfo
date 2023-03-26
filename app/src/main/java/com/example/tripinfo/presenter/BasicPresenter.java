package com.example.tripinfo.presenter;

import android.content.Context;

import com.example.tripinfo.contract.BasicContract;
import com.example.tripinfo.dto.CountryInfo;
import com.example.tripinfo.model.BasicModel;

public class BasicPresenter implements BasicContract.Presenter {
    BasicContract.View view;
    BasicModel model;

    public BasicPresenter (BasicContract.View view, Context context) {
        this.view = view;
        model = new BasicModel(this, context);
    }

    @Override
    public void getData() {
        CountryInfo countryInfo = model.getData();
        view.setLayout(countryInfo);
    }
}
