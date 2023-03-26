package com.example.tripinfo.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticeInfo {
    @SerializedName("currentCount")
    int currentCount;
    @SerializedName("data")
    List<NoticeData> data;
    @SerializedName("numOfRows")
    int numOfRows;
    @SerializedName("pageNo")
    int pageNo;
    @SerializedName("resultCode")
    int resultCode;
    @SerializedName("resultMsg")
    String resultMsg;
    @SerializedName("totalCount")
    int totalCount;

    public List<NoticeData> getData() {
        return data;
    }

    public void setData(List<NoticeData> data) {
        this.data = data;
    }
}
