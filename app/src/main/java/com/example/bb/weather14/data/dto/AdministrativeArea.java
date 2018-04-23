package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

public class AdministrativeArea {
    @SerializedName("CountryID")
    private String countryID;
    @SerializedName("LocalizedType")
    private String localizedType;
    @SerializedName("LocalizedName")
    private String localizedName;
    @SerializedName("Level")
    private int level;
    @SerializedName("ID")
    private String iD;
    @SerializedName("EnglishType")
    private String englishType;
    @SerializedName("EnglishName")
    private String englishName;

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setLocalizedType(String localizedType) {
        this.localizedType = localizedType;
    }

    public String getLocalizedType() {
        return localizedType;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getID() {
        return iD;
    }

    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    public String getEnglishType() {
        return englishType;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

}
