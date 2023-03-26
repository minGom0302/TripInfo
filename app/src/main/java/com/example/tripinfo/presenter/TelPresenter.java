package com.example.tripinfo.presenter;

import android.content.Context;

import com.example.tripinfo.contract.TelContract;
import com.example.tripinfo.dto.TelInfo;
import com.example.tripinfo.model.TelModel;

public class TelPresenter implements TelContract.Presenter {
    TelContract.View view;
    Context context;
    TelModel model;

    public TelPresenter(TelContract.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new TelModel(context, this);
    }

    @Override
    public void getDataInfo() {
        model.getDataInfo();
    }

    @Override
    public void getReturnData() {
        TelInfo.dataInfo dataInfo = model.getDataInfoMore();
        view.setLayout(dataInfo);
    }

    @Override
    public void internetError() {
        view.setError();
    }
}
