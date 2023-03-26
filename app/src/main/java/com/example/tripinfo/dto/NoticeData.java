package com.example.tripinfo.dto;

import com.google.gson.annotations.SerializedName;

public class NoticeData {
    @SerializedName("file_download_url")
    String file_download_url;
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("txt_origin_cn")
    String txt_origin_cn;
    @SerializedName("written_dt")
    String written_dt;


    public String getFile_download_url() {
        return file_download_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getTxt_origin_cn() {
        return txt_origin_cn;
    }

    public String getWritten_dt() {
        return written_dt;
    }
}
