package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tripinfo.R;

public class EarlyActivity extends AppCompatActivity {
    AppCompatButton countryBtn, noticeBtn, homeBtn;
    AppCompatButton nowBtn;
    private long backspacePressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_early);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEnabled(true, nowBtn);
    }

    private void init() {
        countryBtn = findViewById(R.id.early_searchCountryBtn);
        noticeBtn = findViewById(R.id.early_noticeBtn);
        homeBtn = findViewById(R.id.early_homePageBtn);
        nowBtn = countryBtn;

        countryBtn.setOnClickListener(v -> changeLayout(0, countryBtn));
        noticeBtn.setOnClickListener(v -> changeLayout(1, noticeBtn));
        homeBtn.setOnClickListener(v -> changeLayout(2, homeBtn));
    }

    private void changeLayout(int cnd, AppCompatButton button) {
        nowBtn = button;
        setEnabled(false, button);

        Intent intent = null;
        switch (cnd) {
            case 0 :
                intent = new Intent(this, CountrySearchActivity.class);
                break;
            case 1 :
                intent = new Intent(this, NoticeActivity.class);
                break;
            case 2 :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.0404.go.kr/dev/main.mofa"));
                break;
        }
        startActivity(intent);
    }

    private void setEnabled(boolean btnValue, AppCompatButton btn) {
        btn.setEnabled(btnValue);
    }


    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "'\'뒤로가기\' 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
        if (System.currentTimeMillis() > backspacePressedTime + 2000) {
            backspacePressedTime = System.currentTimeMillis();
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backspacePressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }
}