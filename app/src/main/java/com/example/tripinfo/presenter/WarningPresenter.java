package com.example.tripinfo.presenter;

import android.content.Context;

import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.model.WarningModel;

public class WarningPresenter implements WarningContract.Presenter {
    WarningContract.View view;
    WarningModel model;
    int cnd;

    public WarningPresenter(WarningContract.View view, Context context, int cnd) {
        this.view = view;
        model = new WarningModel(context, this);
        this.cnd = cnd;
    }

    @Override
    public void getData() {
        model.getDataWarning(cnd);
    }

    @Override
    public void getWarningInfo() {
        WarningInfo warningData = model.getWarningInfo(cnd);
        view.setLayout(warningData);
    }

    @Override
    public void setInternetError() {
        view.setError();
    }
}
