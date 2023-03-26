package com.example.tripinfo.contract;

import com.example.tripinfo.dto.TelInfo;

public interface TelContract {
    interface View {
        void setLayout(TelInfo.dataInfo dataInfo);
        void setError();
    }

    interface Presenter {
        void getDataInfo();
        void getReturnData();
        void internetError();
    }
}
