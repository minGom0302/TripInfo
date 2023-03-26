package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.contract.ImContract;
import com.example.tripinfo.dto.ImInfo;
import com.example.tripinfo.presenter.ImPresenter;

public class ImmigrationActivity extends AppCompatActivity implements ImContract.View {
    TextView countryNameTv, haveYnTv, visaYn01Tv, visaCn01Tv, visaYn02Tv, visaCn02Tv, visaYn03Tv, visaCn03Tv, entryCnTv, remarkTv, remarkTitleTv;
    FrameLayout noDataLayout;
    ScrollView yesDataLayout;

    ImContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immigration);

        presenter = new ImPresenter(this, this);

        init();

        presenter.getData();
    }

    private void init() {
        noDataLayout = findViewById(R.id.im_noDataLayout);
        yesDataLayout = findViewById(R.id.im_yesDataLayout);
        countryNameTv = findViewById(R.id.im_countryName);
        haveYnTv = findViewById(R.id.im_haveYn);
        visaYn01Tv = findViewById(R.id.im_visaYn01);
        visaCn01Tv = findViewById(R.id.im_visaCn01);
        visaYn02Tv = findViewById(R.id.im_visaYn02);
        visaCn02Tv = findViewById(R.id.im_visaCn02);
        visaYn03Tv = findViewById(R.id.im_visaYn03);
        visaCn03Tv = findViewById(R.id.im_visaCn03);
        entryCnTv = findViewById(R.id.im_entryCn);
        remarkTv = findViewById(R.id.im_remark);
        remarkTitleTv = findViewById(R.id.im_remarkTitle);
    }

    @Override
    public void setLayout(ImInfo.ImData imData) {
        if(imData != null) {
            noDataLayout.setVisibility(View.GONE);
            yesDataLayout.setVisibility(View.VISIBLE);

            countryNameTv.setText(imData.getCountry_nm());
            haveYnTv.setText(imData.getHave_yn());
            visaYn01Tv.setText(imData.getGnrl_pspt_visa_yn());
            visaCn01Tv.setText(imData.getGnrl_pspt_visa_cn());
            visaYn02Tv.setText(imData.getOfclpspt_visa_yn());
            visaCn02Tv.setText(imData.getOfclpspt_visa_cn());
            visaYn03Tv.setText(imData.getDplmt_pspt_visa_yn());
            visaCn03Tv.setText(imData.getDplmt_pspt_visa_cn());
            entryCnTv.setText(imData.getNvisa_entry_evdc_cn());
            if(imData.getRemark().equals("") || imData.getRemark() == null) {
                remarkTitleTv.setVisibility(View.GONE);
            } else {
                remarkTitleTv.setVisibility(View.VISIBLE);
                remarkTv.setText(imData.getRemark());
            }
        } else {
            noDataLayout.setVisibility(View.VISIBLE);
            yesDataLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showToast() {
        noDataLayout.setVisibility(View.VISIBLE);
        yesDataLayout.setVisibility(View.GONE);
        Toast.makeText(this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
    }
}