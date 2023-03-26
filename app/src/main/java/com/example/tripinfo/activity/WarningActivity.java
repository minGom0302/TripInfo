package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.etc.DownloadBC;
import com.example.tripinfo.presenter.WarningPresenter;

public class WarningActivity extends AppCompatActivity implements WarningContract.View {
    TextView countryTv, warningLevelTv, warningLevelInfoTv, regionTv, remarkTv;
    AppCompatButton downloadBtn;
    FrameLayout noDataLayout;
    ScrollView yesDataLayout;
    WarningContract.Presenter presenter;
    DownloadBC downloadBC;
    BroadcastReceiver mBr;

    String downloadUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        presenter = new WarningPresenter(this, this, 0);
        downloadBC = new DownloadBC(this, this);
        mBr = downloadBC.getDownloadCompleteReceiver();

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter completeFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(mBr, completeFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBr);
    }

    private void init() {
        noDataLayout = findViewById(R.id.warning_noDataLayout);
        yesDataLayout = findViewById(R.id.warning_yesDataLayout);
        countryTv = findViewById(R.id.warning_countryName);
        warningLevelTv = findViewById(R.id.warning_warningLevel);
        warningLevelInfoTv = findViewById(R.id.warning_warningLevelInfo);
        regionTv = findViewById(R.id.warning_regionTy);
        remarkTv = findViewById(R.id.warning_remark);
        downloadBtn = findViewById(R.id.warning_downloadBtn);
        downloadBtn.setOnClickListener(v -> {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/TripInfo") + "/" + countryTv.getText().toString() + "_위험지도.jpg";
            downloadBC.URLDownloading(downloadUrl, path);
        });
        findViewById(R.id.warning_whatIsBtn).setOnClickListener(v ->
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.0404.go.kr/walking/walking_intro.jsp")))
        );

        presenter.getData();
    }

    @Override
    public void setLayout(WarningInfo warningData) {
        if(warningData.getData().size() != 0) {
            yesDataLayout.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);

            WarningInfo.Warning data = warningData.getData().get(0);

            countryTv.setText(data.getCountry_nm());
            warningLevelTv.setText(data.getLvl());
            warningLevelInfoTv.setText(data.getLvlInfo());
            regionTv.setText(data.getRegion_ty());
            remarkTv.setText(data.getRemark());
            downloadUrl = data.getDang_map_download_url();
            if(downloadUrl != null) {
                downloadBtn.setVisibility(View.VISIBLE);
            } else {
                downloadBtn.setVisibility(View.INVISIBLE);
            }
        } else {
            yesDataLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setError() {
        yesDataLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
        Toast.makeText(this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
    }
}