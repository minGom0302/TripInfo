package com.example.tripinfo.etc;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class DownloadBC {
    DownloadManager mDownloadManager;
    long mDownloadQueueId;
    String outputFilePath;
    Uri downloadURI;
    Context context;
    Activity activity;

    public DownloadBC(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    // 파일 다운로드
    public void URLDownloading(String strDownloadURI, String outputFilePath) {
        this.downloadURI = Uri.parse(strDownloadURI);
        this.outputFilePath = outputFilePath;

        if (mDownloadManager == null) {
            mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        }

        File outputFile = new File(outputFilePath);

        try {
            Uri downloadUri = downloadURI;
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setTitle("다운로드 항목");
            request.setDestinationUri(Uri.fromFile(outputFile));
            request.setAllowedOverMetered(true);

            mDownloadQueueId = mDownloadManager.enqueue(request);
        } catch (Exception e) {
            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(context, "첨부파일 다운로드를 위해 동의가 필요합니다.", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            110);
                } else {
                    Toast.makeText(context, "첨부파일 다운로드를 위해 동의가 필요합니다.\n거절할 경우 다운로드를 하지 못합니다.", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            110);
                }
            }
        }
    }


    // 다운로드 상황 표현하기
    public final BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if(mDownloadQueueId == reference) {
                // 다운로드 항목 조회에 필요한 정보
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(reference);
                Cursor cursor = mDownloadManager.query(query);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                int status = cursor.getInt(columnIndex);

                cursor.close();

                switch (status) {
                    case DownloadManager.STATUS_SUCCESSFUL:
                        Toast.makeText(context, "다운로드가 완료됐습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case DownloadManager.STATUS_PAUSED:
                        Toast.makeText(context, "다운로드가 중단됐습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case DownloadManager.STATUS_FAILED:
                        Toast.makeText(context, "다운로드가 취소됐습니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    public BroadcastReceiver getDownloadCompleteReceiver() { return downloadCompleteReceiver; }
}
