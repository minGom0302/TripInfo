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
import com.example.tripinfo.adapter.AdjustAdapter;
import com.example.tripinfo.contract.WarningContract;
import com.example.tripinfo.dto.WarningInfo;
import com.example.tripinfo.presenter.WarningPresenter;

import java.util.List;

public class AccidentActivity extends AppCompatActivity implements WarningContract.View {
    TextView countryTv;
    RecyclerView recyclerView;
    FrameLayout noDataLayout;

    WarningContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);

        presenter = new WarningPresenter(this, this, 5);

        init();
    }

    private void init() {
        countryTv = findViewById(R.id.accident_countryNameTv);
        recyclerView = findViewById(R.id.accident_adRv);
        noDataLayout = findViewById(R.id.accident_noDataLayout);

        presenter.getData();
    }

    @Override
    public void setLayout(WarningInfo warningData) {
        if(warningData.getData().size() != 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);

            List<WarningInfo.Warning> data = warningData.getData();
            countryTv.setText(data.get(0).getCountry_nm());
            AdjustAdapter adapter = new AdjustAdapter(data, null);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);
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