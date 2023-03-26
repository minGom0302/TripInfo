package com.example.tripinfo.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImInfo {
    @SerializedName("currentCount")
    @Expose
    int currentCount;
    @SerializedName("data")
    @Expose
    List<ImData> data;
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

    public List<ImData> getData() {
        return data;
    }

    public void setData(List<ImData> data) {
        this.data = data;
    }

    public static class ImData {
        @SerializedName("country_eng_nm")
        @Expose
        String country_eng_nm;
        @SerializedName("country_iso_alp2")
        @Expose
        String country_iso_alp2;
        @SerializedName("country_nm")
        @Expose
        String country_nm;
        @SerializedName("dplmt_pspt_visa_cn")
        @Expose
        String dplmt_pspt_visa_cn;
        @SerializedName("dplmt_pspt_visa_yn")
        @Expose
        String dplmt_pspt_visa_yn;
        @SerializedName("gnrl_pspt_visa_cn")
        @Expose
        String gnrl_pspt_visa_cn;
        @SerializedName("gnrl_pspt_visa_yn")
        @Expose
        String gnrl_pspt_visa_yn;
        @SerializedName("have_yn")
        @Expose
        String have_yn;
        @SerializedName("id")
        @Expose
        String id;
        @SerializedName("nvisa_entry_evdc_cn")
        @Expose
        String nvisa_entry_evdc_cn;
        @SerializedName("ofclpspt_visa_cn")
        @Expose
        String ofclpspt_visa_cn;
        @SerializedName("ofclpspt_visa_yn")
        @Expose
        String ofclpspt_visa_yn;
        @SerializedName("remark")
        @Expose
        String remark;

        public String getCountry_nm() {
            return country_nm;
        }

        public String getDplmt_pspt_visa_cn() {
            return dplmt_pspt_visa_cn;
        }

        public void setDplmt_pspt_visa_cn(String dplmt_pspt_visa_cn) {
            this.dplmt_pspt_visa_cn = dplmt_pspt_visa_cn;
        }

        public String getDplmt_pspt_visa_yn() {
            return dplmt_pspt_visa_yn;
        }

        public void setDplmt_pspt_visa_yn(String dplmt_pspt_visa_yn) {
            this.dplmt_pspt_visa_yn = dplmt_pspt_visa_yn;
        }

        public String getGnrl_pspt_visa_cn() {
            return gnrl_pspt_visa_cn;
        }

        public void setGnrl_pspt_visa_cn(String gnrl_pspt_visa_cn) {
            this.gnrl_pspt_visa_cn = gnrl_pspt_visa_cn;
        }

        public String getGnrl_pspt_visa_yn() {
            return gnrl_pspt_visa_yn;
        }

        public void setGnrl_pspt_visa_yn(String gnrl_pspt_visa_yn) {
            this.gnrl_pspt_visa_yn = gnrl_pspt_visa_yn;
        }

        public String getHave_yn() {
            return have_yn;
        }

        public void setHave_yn(String have_yn) {
            this.have_yn = have_yn;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNvisa_entry_evdc_cn() {
            return nvisa_entry_evdc_cn;
        }

        public void setNvisa_entry_evdc_cn(String nvisa_entry_evdc_cn) {
            this.nvisa_entry_evdc_cn = nvisa_entry_evdc_cn;
        }

        public String getOfclpspt_visa_cn() {
            return ofclpspt_visa_cn;
        }

        public void setOfclpspt_visa_cn(String ofclpspt_visa_cn) {
            this.ofclpspt_visa_cn = ofclpspt_visa_cn;
        }

        public String getOfclpspt_visa_yn() {
            return ofclpspt_visa_yn;
        }

        public void setOfclpspt_visa_yn(String ofclpspt_visa_yn) {
            this.ofclpspt_visa_yn = ofclpspt_visa_yn;
        }

        public String getRemark() {
            return remark;
        }

    }
}
