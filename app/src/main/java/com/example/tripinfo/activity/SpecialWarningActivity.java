package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.etc.DownloadBC;
import com.example.tripinfo.presenter.WarningPresenter;

public class SpecialWarningActivity extends AppCompatActivity implements WarningContract.View {
    TextView countryTv, regionTv, remarkTv;
    LinearLayout yesDataLayout;
    FrameLayout noDataLayout;
    WarningContract.Presenter presenter;
    DownloadBC downloadBC;
    BroadcastReceiver mBr;

    String downloadUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_warning);

        presenter = new WarningPresenter(this, this, 2);
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
        yesDataLayout = findViewById(R.id.spWarning_yesDataLayout);
        noDataLayout = findViewById(R.id.spWarning_noDataLayout);
        countryTv = findViewById(R.id.spWarning_countryName);
        regionTv = findViewById(R.id.spWarning_regionTy);
        remarkTv = findViewById(R.id.spWarning_bego);
        findViewById(R.id.spWarning_downloadBtn).setOnClickListener(v -> {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/TripInfo") + "/" + countryTv.getText().toString() + "_지도.jpg";
            downloadBC.URLDownloading(downloadUrl, path);
        });

        presenter.getData();
    }

    @Override
    public void setLayout(WarningInfo warningData) {
        if(warningData.getData().size() != 0) {
            WarningInfo.Warning data = warningData.getData().get(0);
            if (data.getEvacuate_rcmnd_remark() != null) {
                yesDataLayout.setVisibility(View.VISIBLE);
                noDataLayout.setVisibility(View.GONE);

                countryTv.setText(data.getCountry_nm());
                regionTv.setText(data.getRegion_ty());
                remarkTv.setText(data.getEvacuate_rcmnd_remark());
                downloadUrl = data.getDang_map_download_url();
            } else {
                yesDataLayout.setVisibility(View.GONE);
                noDataLayout.setVisibility(View.VISIBLE);
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