package com.example.tripinfo.contract;

import com.example.tripinfo.dto.CountryInfo;

import java.util.List;

public interface MainContract {
    interface View {
        void setCountryRv(List<CountryInfo> countryList);
    }

    interface Presenter {
        void searchCountry(String country);
        void viewSetCountry(List<CountryInfo> countryList);
    }
}
