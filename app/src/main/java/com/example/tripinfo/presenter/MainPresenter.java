package com.example.tripinfo.presenter;

import com.example.tripinfo.contract.MainContract;
import com.example.tripinfo.dto.CountryInfo;
import com.example.tripinfo.model.MainModel;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    MainModel model;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void viewSetCountry(List<CountryInfo> countryList) {
        view.setCountryRv(countryList);
    }

    @Override
    public void searchCountry(String country) {
        model = new MainModel(this);
        model.getCountryInfo(country);
    }
}
