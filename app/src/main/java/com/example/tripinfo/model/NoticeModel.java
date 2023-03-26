package com.example.tripinfo.model;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tripinfo.adapter.NoticeAdapter;
import com.example.tripinfo.contract.NoticeContract;
import com.example.tripinfo.dto.NoticeData;
import com.example.tripinfo.dto.NoticeInfo;
import com.example.tripinfo.etc.RetrofitAPI;
import com.example.tripinfo.etc.RetrofitClient;
import com.example.tripinfo.etc.ServiceKeyGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeModel {
    NoticeContract.Presenter presenter;
    RetrofitAPI api;
    List<NoticeData> noticeList;
    String TAG = "NoticeModel";
    int page = 1;

    public NoticeModel(NoticeContract.Presenter presenter) {
        this.presenter = presenter;
        api = RetrofitClient.getRetrofit();
    }

    public void getFirstData() {
        String serviceKey = ServiceKeyGroup.getNoticeKey();
        String returnType = "JSON";
        String numOfRows = "20";
        String pageNo = String.valueOf(page);
        Log.e(TAG, pageNo);

        api.getNoticeInfo(serviceKey, returnType, numOfRows, pageNo).enqueue(new Callback<NoticeInfo>() {
            @Override
            public void onResponse(@NonNull Call<NoticeInfo> call, @NonNull Response<NoticeInfo> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        NoticeInfo noticeInfo = response.body();
                        if(page == 1) {
                            noticeList = noticeInfo.getData();
                        } else {
                            noticeList.addAll(noticeInfo.getData());
                        }
                    }
                }
                if(page == 1) {
                    lastAction(page);
                } else {
                    lastAction(page);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NoticeInfo> call, @NonNull Throwable t) {
                if(page == 1) {
                    lastAction(page);
                } else {
                    lastAction(page);
                }
            }
        });
    }

    private void lastAction(int cnd) {
        if (cnd == 1) {
            presenter.getList();
        } else {
            presenter.adapterUpdate();
        }
    }

    public List<NoticeData> getNoticeList() { return noticeList; }

    public void loadMoreData(NoticeAdapter adapter) {
        noticeList.add(null);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            adapter.notifyItemInserted(noticeList.size() - 1);

            noticeList.remove(noticeList.size() - 1);
            int scrollPosition = getNoticeList().size();
            adapter.notifyItemRemoved(scrollPosition);
            page++;
            getFirstData();
        }, 3000);
    }
}
