package com.example.tripinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripinfo.R;
import com.example.tripinfo.adapter.MainAdapter;
import com.example.tripinfo.contract.MainContract;
import com.example.tripinfo.dto.CountryInfo;
import com.example.tripinfo.presenter.MainPresenter;

import java.util.List;

public class CountrySearchActivity extends AppCompatActivity implements MainContract.View {
    EditText countryEt;
    RecyclerView countryRv;
    AppCompatButton searchBtn;
    AppCompatImageButton clearBtn;
    InputMethodManager imm;

    MainContract.Presenter presenter;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);

        presenter = new MainPresenter(this);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        init();
    }

    private void init() {
        countryEt = findViewById(R.id.main_countryEt);
        countryRv = findViewById(R.id.main_countryRv);
        searchBtn = findViewById(R.id.main_searchBtn);
        clearBtn = findViewById(R.id.main_clearBtn);

        searchBtn.setOnClickListener(v -> dataSearch());
        clearBtn.setOnClickListener(v -> {
            countryEt.setText("");
            countryEt.setFocusable(true);
            countryEt.requestFocus();
            imm.showSoftInput(countryEt,0);
        });

        // 키보드에서 엔터키 눌렀을 때 설정
        countryEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                imm.hideSoftInputFromWindow(countryEt.getWindowToken(), 0);
                dataSearch();
            }
            return true;
        });
        // 입력이 됐을 때 이벤트 처리
        countryEt.addTextChangedListener(textWatcher);
    }

    private void dataSearch() {
        String country = countryEt.getText().toString();
        presenter.searchCountry(country);
        searchBtn.setEnabled(false);
    }

    @Override
    public void setCountryRv(List<CountryInfo> countryList) {
        if(countryList != null) {
            adapter = new MainAdapter(countryList);
            countryRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            countryRv.setAdapter(adapter);
            searchBtn.setEnabled(true);
        } else {
            runOnUiThread(() ->
                Toast.makeText(this, "인터넷 연결을 확인해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
            );
        }
    }

    // 현재 포커스 받고있는 view 영역 외에서 터치 이벤트가 발생하면 InputMethodManager 를 통해 키보드를 내림
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 텍스트에 이벤트가 발생했을 때 처리하기 위한 메서드
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!TextUtils.isEmpty(charSequence.toString())) {
                clearBtn.setVisibility(View.VISIBLE);
            } else {
                clearBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}