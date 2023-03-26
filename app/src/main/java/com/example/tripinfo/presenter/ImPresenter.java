package com.example.tripinfo.presenter;

import android.content.Context;

import com.example.tripinfo.contract.ImContract;
import com.example.tripinfo.dto.ImInfo;
import com.example.tripinfo.model.ImModel;

public class ImPresenter implements ImContract.Presenter {
    ImContract.View view;
    ImModel model;

    public ImPresenter(ImContract.View view, Context context) {
        this.view = view;
        model = new ImModel(context, this);
    }

    @Override
    public void getData() {
        model.getData();
    }

    @Override
    public void getImInfo() {
        ImInfo.ImData imData = model.getImInfo();
        view.setLayout(imData);
    }

    @Override
    public void internetError() {
        view.showToast();
    }
}
