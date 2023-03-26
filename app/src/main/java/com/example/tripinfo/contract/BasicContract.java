package com.example.tripinfo.contract;

import com.example.tripinfo.dto.CountryInfo;

public interface BasicContract {
    interface View {
        void setLayout(CountryInfo countryInfo);
    }

    interface Presenter {
        void getData();
    }
}
