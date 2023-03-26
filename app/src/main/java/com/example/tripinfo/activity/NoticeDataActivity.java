package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.example.tripinfo.R;
import com.example.tripinfo.etc.DownloadBC;

public class NoticeDataActivity extends AppCompatActivity {
    TextView titleTv, dayTv, contextTv, levelTv;
    AppCompatButton downloadBtn;
    
    DownloadBC downloadBC;
    BroadcastReceiver mBr;
    String path;
    String downloadURL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_data);

        downloadBC = new DownloadBC(this, this);
        mBr = downloadBC.getDownloadCompleteReceiver();

        init();
    }

    private void init() {
        titleTv = findViewById(R.id.noticeData_title);
        dayTv = findViewById(R.id.noticeData_day);
        contextTv = findViewById(R.id.noticeData_context);
        levelTv = findViewById(R.id.noticeData_level);
        downloadBtn = findViewById(R.id.noticeData_downloadBtn);
        downloadBtn.setOnClickListener(v -> {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/TripInfo") + "/다운로드파일_" + dayTv.getText().toString();
            downloadBC.URLDownloading(downloadURL, path);
        });

        Intent intent = getIntent();
        if(intent.getStringExtra("download") != null) {
            if(intent.getStringExtra("download").length() != 0) {
                downloadURL = intent.getStringExtra("download");
                downloadBtn.setVisibility(View.VISIBLE);
            } else {
                downloadBtn.setVisibility(View.INVISIBLE);
            }
        } else {
            downloadBtn.setVisibility(View.INVISIBLE);
        }
        if(intent.getStringExtra("level") != null) {
            if(intent.getStringExtra("level").length() != 0) {
                String level = intent.getStringExtra("level");
                levelTv.setText(level);
                levelTv.setVisibility(View.VISIBLE);
                if(level.equals("안내")) {
                    levelTv.setTextColor(ContextCompat.getColor(this, R.color.light_blue01));
                } else if(level.equals("주의")) {
                    levelTv.setTextColor(ContextCompat.getColor(this, R.color.light_red01));
                }

            } else {
                levelTv.setVisibility(View.GONE);
            }
        } else {
            levelTv.setVisibility(View.GONE);
        }

        titleTv.setText(intent.getStringExtra("title"));
        dayTv.setText(intent.getStringExtra("day"));
        contextTv.setText(intent.getStringExtra("context"));
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
}