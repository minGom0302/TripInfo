package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.adapter.SafetyNoticeAdapter;
import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.presenter.WarningPresenter;

import java.util.List;

public class SafetyNoticeActivity extends AppCompatActivity implements WarningContract.View {
    FrameLayout noDataLayout;
    RecyclerView recyclerView;
    TextView countryTv;
    WarningContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_notice);

        presenter = new WarningPresenter(this, this, 4);

        init();
    }

    private void init() {
        noDataLayout = findViewById(R.id.safetyNt_noDataLayout);
        recyclerView = findViewById(R.id.safetyNt_adRv);
        countryTv = findViewById(R.id.safetyNt_countryNameTv);

        presenter.getData();
    }

    @Override
    public void setLayout(WarningInfo warningData) {
        if(warningData.getData() != null) {
            if (warningData.getData().size() != 0) {
                recyclerView.setVisibility(View.VISIBLE);
                noDataLayout.setVisibility(View.GONE);

                List<WarningInfo.Warning> data = warningData.getData();
                SafetyNoticeAdapter adapter = new SafetyNoticeAdapter(data);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                noDataLayout.setVisibility(View.VISIBLE);
            }
        } else {
            recyclerView.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setError() {
        recyclerView.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.VISIBLE);
        Toast.makeText(this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
    }
}