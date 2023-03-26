package com.example.tripinfo.dto;

public class CountryInfo {
    String countryName;
    String countryEnName;
    String basic;
    String continent;
    String id;
    String imgUrl;
    String wrtDt;

    public CountryInfo(String countryName, String countryEnName, String basic, String continent, String imgUrl) {
        this.countryName = countryName;
        this.countryEnName = countryEnName;
        this.basic = basic;
        this.continent = continent;
        this.imgUrl = imgUrl;
    }

    public CountryInfo(String countryName, String countryEnName, String basic, String continent, String id, String imgUrl, String wrtDt) {
        this.countryName = countryName;
        this.countryEnName = countryEnName;
        this.basic = basic;
        this.continent = continent;
        this.id = id;
        this.imgUrl = imgUrl;
        this.wrtDt = wrtDt;
    }

    public String getBasic() {
        return basic;
    }

    public String getContinent() {
        return continent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryEnName() {
        return countryEnName;
    }
}
