package com.example.tripinfo.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TelInfo {
    @SerializedName("currentCount")
    @Expose
    int currentCount;
    @SerializedName("data")
    @Expose
    List<dataInfo> data;
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

    public List<dataInfo> getaDta() {
        return data;
    }


    public static class dataInfo {
        @SerializedName("contact_remark")
        @Expose
        String contact_remark;

        public String getContact_remark() {
            return contact_remark;
        }

        public void setContact_remark(String contact_remark) {
            this.contact_remark = contact_remark;
        }

    }
}
