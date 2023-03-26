package com.example.tripinfo.contract;

import com.example.tripinfo.dto.ImInfo;

public interface ImContract {
    interface View {
        void setLayout(ImInfo.ImData imData);
        void showToast();
    }
    interface Presenter {
        void getData();
        void getImInfo();
        void internetError();
    }
}
