package com.example.tripinfo.presenter;

import com.example.tripinfo.adapter.NoticeAdapter;
import com.example.tripinfo.contract.NoticeContract;
import com.example.tripinfo.dto.NoticeData;
import com.example.tripinfo.model.NoticeModel;

import java.util.List;

public class NoticePresenter implements NoticeContract.Presenter {
    NoticeContract.View view;
    NoticeModel model;
    NoticeAdapter adapter;

    public NoticePresenter(NoticeContract.View view) {
        this.view = view;
        model = new NoticeModel(this);
    }

    @Override
    public void getList() {
        List<NoticeData> noticeList = model.getNoticeList();
        if (noticeList != null) {
            view.setLayout(noticeList);
        } else {
            view.noData();
        }
    }

    @Override
    public void getFirstData() {
        model.getFirstData();
    }

    @Override
    public void loadMoreData(NoticeAdapter adapter) {
        this.adapter = adapter;
        model.loadMoreData(adapter);
    }

    public void adapterUpdate() {
        List<NoticeData> noticeList = model.getNoticeList();
        view.setIsLoading(noticeList);
    }
}
