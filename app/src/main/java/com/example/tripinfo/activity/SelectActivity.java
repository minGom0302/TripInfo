package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tripinfo.R;

public class SelectActivity extends AppCompatActivity {
    AppCompatImageButton btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09, nowBtn;
    AppCompatButton moreBtn;
    TextView title;
    private String moreUri = "https://www.0404.go.kr/dev/country_view.mofa?idx=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEnabled(true, nowBtn);
    }


    private void init() {
        title = findViewById(R.id.selectTitle);
        btn01 = findViewById(R.id.selectBtn01);
        btn02 = findViewById(R.id.selectBtn02);
        btn03 = findViewById(R.id.selectBtn03);
        btn04 = findViewById(R.id.selectBtn04);
        btn05 = findViewById(R.id.selectBtn05);
        btn06 = findViewById(R.id.selectBtn06);
        btn07 = findViewById(R.id.selectBtn07);
        btn08 = findViewById(R.id.selectBtn08);
        btn09 = findViewById(R.id.selectBtn09);
        moreBtn = findViewById(R.id.select_moreBtn);
        nowBtn = btn01;

        btn01.setOnClickListener(v -> changeLayout(0, btn01));
        btn02.setOnClickListener(v -> changeLayout(1, btn02));
        btn03.setOnClickListener(v -> changeLayout(2, btn03));
        btn04.setOnClickListener(v -> changeLayout(3, btn04));
        btn05.setOnClickListener(v -> changeLayout(4, btn05));
        btn06.setOnClickListener(v -> changeLayout(5, btn06));
        btn07.setOnClickListener(v -> changeLayout(6, btn07));
        btn08.setOnClickListener(v -> changeLayout(7, btn08));
        btn09.setOnClickListener(v -> changeLayout(8, btn09));
        moreBtn.setOnClickListener(v -> changeLayout(9, null));


        moreUri += getIntent().getStringExtra("id");
        title.setText(getIntent().getStringExtra("countryName"));
    }

    private void changeLayout(int cnd, AppCompatImageButton btn) {
        if(btn != null) {
            setEnabled(false, btn);
        }

        Intent intent = null;
        switch (cnd) {
            case 0 :
                intent = new Intent(this, BasicActivity.class);
                break;
            case 1 :
                intent = new Intent(this, LocalTelMainActivity.class);
                break;
            case 2 :
                intent = new Intent(this, ImmigrationActivity.class);
                break;
            case 3 :
                intent = new Intent(this, WarningActivity.class);
                break;
            case 4 :
                intent = new Intent(this, WarningAdjustActivity.class);
                break;
            case 5 :
                intent = new Intent(this, SpecialWarningActivity.class);
                break;
            case 6 :
                intent = new Intent(this, SafetyInfoActivity.class);
                break;
            case 7 :
                intent = new Intent(this, SafetyNoticeActivity.class);
                break;
            case 8 :
                intent = new Intent(this, AccidentActivity.class);
                break;
            case 9 :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(moreUri));
                break;
        }
        startActivity(intent);
    }

    private void setEnabled(boolean btnValue, AppCompatImageButton btn) {
        btn.setEnabled(btnValue);
    }
}