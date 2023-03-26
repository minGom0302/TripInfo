package com.example.tripinfo.contract;

import com.example.tripinfo.dto.WarningInfo;

public interface WarningContract {
    interface View {
        void setLayout(WarningInfo warningData);
        void setError();
    }

    interface Presenter {
        void getData();
        void getWarningInfo();
        void setInternetError();
    }
}
