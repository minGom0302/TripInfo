package com.example.tripinfo.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WarningInfo {
    @SerializedName("currentCount")
    @Expose
    int currentCount;
    @SerializedName("data")
    @Expose
    List<Warning> data;
    @SerializedName("numOfRows")
    @Expose
    int numOfRows;
    @SerializedName("pageNo")
    @Expose
    int pageNo;
    @SerializedName("resultCode")
    @Expose
    int resultCode;
    @SerializedName("resultMsg")
    @Expose
    String resultMsg;
    @SerializedName("totalCount")
    @Expose
    int totalCount;

    public List<Warning> getData() {
        return data;
    }

    public static class Warning {
        @SerializedName("alarm_lvl")
        @Expose
        int alarm_lvl;
        @SerializedName("country_nm")
        @Expose
        String country_nm;
        @SerializedName("dang_map_download_url")
        @Expose
        String dang_map_download_url; // 이미지
        @SerializedName("region_ty")
        @Expose
        String region_ty;
        @SerializedName("remark")
        @Expose
        String remark;
        @SerializedName("file_download_url")
        @Expose
        String file_download_url; // 이미지
        @SerializedName("title")
        @Expose
        String title;
        @SerializedName("txt_origin_cn")
        @Expose
        String txt_origin_cn;
        @SerializedName("wrt_dt")
        @Expose
        String wrt_dt;
        @SerializedName("evacuate_rcmnd_remark")
        @Expose
        String evacuate_rcmnd_remark;
        @SerializedName("ctgy_nm")
        @Expose
        String ctgy_nm;
        @SerializedName("news")
        @Expose
        String news;
        String lvl;
        String lvlInfo;

        public int getAlarm_lvl() {
            return alarm_lvl;
        }

        public String getCountry_nm() {
            return country_nm;
        }

        public String getDang_map_download_url() {
            return dang_map_download_url;
        }

        public String getRegion_ty() {
            return region_ty;
        }

        public String getRemark() {
            return remark;
        }

        public String getFile_download_url() {
            return file_download_url;
        }

        public String getTitle() {
            return title;
        }

        public String getTxt_origin_cn() {
            return txt_origin_cn;
        }

        public void setTxt_origin_cn(String txt_origin_cn) {
            this.txt_origin_cn = txt_origin_cn;
        }

        public String getLvl() {
            return lvl;
        }

        public void setLvl(String lvl) {
            this.lvl = lvl;
        }

        public void setLvlInfo(String lvlInfo) {
            this.lvlInfo = lvlInfo;
        }

        public String getLvlInfo() {
            return lvlInfo;
        }

        public String getWrt_dt() {
            return wrt_dt;
        }

        public String getEvacuate_rcmnd_remark() {
            return evacuate_rcmnd_remark;
        }

        public String getCtgy_nm() {
            return ctgy_nm;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getNews() {
            return news;
        }
    }
}
