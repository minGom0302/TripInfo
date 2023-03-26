package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.adapter.AdjustAdapter;
import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.etc.DownloadBC;
import com.example.tripinfo.presenter.WarningPresenter;

import java.util.List;

public class WarningAdjustActivity extends AppCompatActivity implements WarningContract.View {
    WarningContract.Presenter presenter;
    DownloadBC downloadBC;
    BroadcastReceiver mBr;

    SharedPreferences sp;
    TextView countryTv;
    RecyclerView recyclerView;
    FrameLayout noDataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_adjust);

        presenter = new WarningPresenter(this, this, 1);
        downloadBC = new DownloadBC(this, this);
        mBr = downloadBC.getDownloadCompleteReceiver();
        sp = PreferenceManager.getDefaultSharedPreferences(this);

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
        countryTv = findViewById(R.id.warningAd_countryNameTv);
        recyclerView = findViewById(R.id.warningAd_adRv);
        noDataLayout = findViewById(R.id.warningAd_noDataLayout);
        presenter.getData();
    }

    @Override
    public void setLayout(WarningInfo warningData) {
        if(warningData.getData().size() != 0) {
            List<WarningInfo.Warning> data = warningData.getData();
            countryTv.setText(sp.getString("countryName", ""));

            noDataLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            AdjustAdapter adapter = new AdjustAdapter(data, downloadBC);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        } else {
            noDataLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setError() {
        noDataLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
    }
}