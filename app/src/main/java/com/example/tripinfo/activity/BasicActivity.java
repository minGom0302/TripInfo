package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tripinfo.R;
import com.example.tripinfo.contract.BasicContract;
import com.example.tripinfo.dto.CountryInfo;
import com.example.tripinfo.presenter.BasicPresenter;

public class BasicActivity extends AppCompatActivity implements BasicContract.View {
    TextView countryName, countryEnName, continent, basic;
    ImageView imageView;

    BasicContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        init();

        presenter = new BasicPresenter(this, getApplicationContext());
        presenter.getData();
    }

    private void init() {
        countryName = findViewById(R.id.basic_countryNameTv);
        countryEnName = findViewById(R.id.basic_countryEnNameTv);
        continent = findViewById(R.id.basic_continentTv);
        basic = findViewById(R.id.basic_basicTv);
        imageView = findViewById(R.id.basic_imgView);
    }

    @Override
    public void setLayout(CountryInfo countryInfo) {
        countryName.setText(countryInfo.getCountryName());
        countryEnName.setText(countryInfo.getCountryEnName());
        continent.setText(countryInfo.getContinent());
        basic.setText(countryInfo.getBasic());

        if(countryInfo.getImgUrl().length() != 0) {
            Glide.with(getApplicationContext()).load(countryInfo.getImgUrl()).into(imageView);
        }
    }
}