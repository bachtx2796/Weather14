package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationDTO {
    @SerializedName("AdministrativeArea")
    private AdministrativeArea administrativeArea;
    @SerializedName("LocalizedName")
    private String localizedName;
    @SerializedName("Rank")
    private int rank;
    @SerializedName("IsAlias")
    private boolean isAlias;
    @SerializedName("Type")
    private String type;
    @SerializedName("TimeZone")
    private TimeZone timeZone;
    @SerializedName("Version")
    private int version;
    @SerializedName("PrimaryPostalCode")
    private String primaryPostalCode;
    @SerializedName("Region")
    private Region region;
    @SerializedName("Country")
    private Country country;
    @SerializedName("GeoPosition")
    private GeoPosition geoPosition;
    @SerializedName("Key")
    private String key;
    @SerializedName("EnglishName")
    private String englishName;

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public int getRank() {
        return rank;
    }

    public boolean isAlias() {
        return isAlias;
    }

    public String getType() {
        return type;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public int getVersion() {
        return version;
    }

    public String getPrimaryPostalCode() {
        return primaryPostalCode;
    }

    public Region getRegion() {
        return region;
    }

    public Country getCountry() {
        return country;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public String getKey() {
        return key;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setAlias(boolean alias) {
        isAlias = alias;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPrimaryPostalCode(String primaryPostalCode) {
        this.primaryPostalCode = primaryPostalCode;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}