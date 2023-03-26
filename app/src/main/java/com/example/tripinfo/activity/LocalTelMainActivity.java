package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.contract.TelContract;
import com.example.tripinfo.dto.TelInfo;
import com.example.tripinfo.presenter.TelPresenter;

public class LocalTelMainActivity extends AppCompatActivity implements TelContract.View {
    TelContract.Presenter presenter;
    TextView conTv;
    FrameLayout noDataLayout;
    ScrollView yesDataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_tel_main);

        presenter = new TelPresenter(this, this);

        init();
    }

    private void init() {
        conTv = findViewById(R.id.tel_tv);
        noDataLayout = findViewById(R.id.tel_noDataLayout);
        yesDataLayout = findViewById(R.id.tel_yesDataLayout);

        presenter.getDataInfo();
    }

    @Override
    public void setLayout(TelInfo.dataInfo dataInfo) {
        if(dataInfo != null) {
            noDataLayout.setVisibility(View.GONE);
            yesDataLayout.setVisibility(View.VISIBLE);
            conTv.setText(dataInfo.getContact_remark());
        } else {
            noDataLayout.setVisibility(View.VISIBLE);
            yesDataLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setError() {
        noDataLayout.setVisibility(View.VISIBLE);
        yesDataLayout.setVisibility(View.GONE);
        Toast.makeText(this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
    }
}