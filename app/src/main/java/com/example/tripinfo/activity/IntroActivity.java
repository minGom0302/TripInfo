package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tripinfo.R;
import com.example.tripinfo.etc.RetrofitClient;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Retrofit 통신 설정
        RetrofitClient.setGsonAndRetrofit();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(IntroActivity.this, EarlyActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}