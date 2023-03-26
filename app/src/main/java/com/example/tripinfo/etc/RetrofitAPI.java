package com.example.tripinfo.etc;

import com.example.tripinfo.dto.ImInfo;
import com.example.tripinfo.dto.NoticeInfo;
import com.example.tripinfo.dto.TelInfo;
import com.example.tripinfo.dto.WarningInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("NoticeService2/getNoticeList2")
    Call<NoticeInfo> getNoticeInfo(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo
    );

    @GET("EntranceVisaService2/getEntranceVisaList2")
    Call<ImInfo> getImInfo(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("TravelAlarmService2/getTravelAlarmList2")
    Call<WarningInfo> getWarning(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("CountryHistoryService2/getCountryHistoryList2")
    Call<WarningInfo> getWarningAdjust(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("LocalContactService2/getLocalContactList2")
    Call<TelInfo> getTel(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("SptravelWarningService2/getSptravelWarningList2")
    Call<WarningInfo> getSpecialWarning(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew")
    Call<WarningInfo> getSafetyInfo(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("CountrySafetyService3/getCountrySafetyList3")
    Call<WarningInfo> getSafetyNoticeInfo(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );

    @GET("CountryAccidentService2/CountryAccidentService2")
    Call<WarningInfo> getAccident(
            @Query("serviceKey") String serviceKey,
            @Query("returnType") String returnType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("cond[country_nm::EQ]") String countryName
    );
}
