package com.example.tripinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.adapter.NoticeAdapter;
import com.example.tripinfo.contract.NoticeContract;
import com.example.tripinfo.dto.NoticeData;
import com.example.tripinfo.presenter.NoticePresenter;

import java.util.List;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {
    NoticeContract.Presenter presenter;
    RecyclerView noticeRv;
    NoticeAdapter adapter;
    ProgressBar loadingBar;
    TextView noDataLayout;

    boolean isLoading = false;
    List<NoticeData> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        presenter = new NoticePresenter(this);

        init();
    }

    private void init() {
        noticeRv = findViewById(R.id.notice_noticeRv);
        loadingBar = findViewById(R.id.notice_progressBar);
        noDataLayout = findViewById(R.id.notice_noDataLayout);
        presenter.getFirstData();
        noticeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();

                if(!isLoading) {
                    if(lm != null && lm.findLastCompletelyVisibleItemPosition() == noticeList.size() -1) {
                        loadingBar.setVisibility(View.VISIBLE);
                        presenter.loadMoreData(adapter);
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void setLayout(List<NoticeData> noticeList) {
        this.noticeList = noticeList;
        if(noticeList.size() != 0) {
            noDataLayout.setVisibility(View.GONE);
            noticeRv.setVisibility(View.VISIBLE);

            adapter = new NoticeAdapter(noticeList);
            noticeRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            noticeRv.setAdapter(adapter);
        } else {
            noDataLayout.setVisibility(View.VISIBLE);
            noticeRv.setVisibility(View.GONE);
        }
    }

    @Override
    public void noData() {
        Toast.makeText(this, "검색된 데이터가 없습니다.\n잠시 후 다시 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void setIsLoading(List<NoticeData> noticeList) {
        adapter.updateItem(noticeList);
        adapter.notifyDataSetChanged();
        isLoading = false;
        loadingBar.setVisibility(View.GONE);
    }
}