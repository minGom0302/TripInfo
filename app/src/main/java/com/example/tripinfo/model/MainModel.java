package com.example.tripinfo.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tripinfo.contract.MainContract;
import com.example.tripinfo.dto.CountryInfo;
import com.example.tripinfo.etc.ServiceKeyGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainModel {
    MainContract.Presenter presenter;

    List<CountryInfo> countryList = new ArrayList<>();
    String country;

    public MainModel(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getCountryInfo(String country) {
        this.country = country;
        MainAsyncTask mainAsyncTask = new MainAsyncTask();
        mainAsyncTask.execute();
    }


    @SuppressLint("StaticFieldLeak")
    public class MainAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String firstURL = "https://apis.data.go.kr/1262000/CountryBasicService/getCountryBasicList?serviceKey=";
            String serviceKey = ServiceKeyGroup.getMainKey();
            String secURL;
            if (country.length() == 0) {
                secURL = "&numOfRows=200&pageNo=1";
            } else {
                secURL = "&numOfRows=200&pageNo=1&countryName="+country;
            }

            try {
                URL url = new URL(firstURL+serviceKey+secURL);

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = xmlPullParserFactory.newPullParser();

                InputStream is = url.openStream();
                parser.setInput(new InputStreamReader(is, StandardCharsets.UTF_8));

                int eventType = parser.getEventType();
                String tagName = "";
                String countryName = "";
                String countryEnName = "";
                String id = "";
                String basic = "";
                String continent = "";
                String imgUrl = "";
                String wrtDt;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            tagName = parser.getName();
                            break;
                        case XmlPullParser.END_TAG:
                            tagName = null;
                            break;
                        case XmlPullParser.TEXT:
                            switch (Objects.requireNonNull(tagName)) {
                                case "resultMsg" :
                                    Log.i("MainModel", parser.getText());
                                    break;
                                case "countryEnName" :
                                    countryEnName = parser.getText();
                                    break;
                                case "countryName" :
                                    countryName = parser.getText().replaceAll("\\(중국\\)", "");
                                    break;
                                case "id" :
                                    id = parser.getText();
                                    break;
                                case "basic" :
                                    basic = parser.getText();
                                    break;
                                case "continent" :
                                    continent = parser.getText();
                                    break;
                                case "imgUrl" :
                                    imgUrl = parser.getText();
                                    break;
                                case "wrtDt" :
                                    wrtDt = parser.getText();
                                    CountryInfo countryInfo = new CountryInfo(countryName, countryEnName, basic, continent, id, imgUrl, wrtDt);
                                    countryList.add(countryInfo);
                                    break;
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (Exception e) {
                presenter.viewSetCountry(null);
            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            presenter.viewSetCountry(countryList);
        }
    }

}
