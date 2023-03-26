package com.example.tripinfo.contract;

import com.example.tripinfo.adapter.NoticeAdapter;
import com.example.tripinfo.dto.NoticeData;

import java.util.List;

public interface NoticeContract {
    interface View {
        void setLayout(List<NoticeData> noticeList);
        void noData();
        void setIsLoading(List<NoticeData> noticeList);
    }

    interface Presenter {
        void getFirstData();
        void getList();
        void loadMoreData(NoticeAdapter adapter);
        void adapterUpdate();
    }
}
