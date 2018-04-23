package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationDTO {
    @SerializedName("LocalizedName")
    private String localizedName;
    @SerializedName("GeoPosition")
    private GeoPosition geoPosition;
    @SerializedName("Key")
    private String key;

    public String getLocalizedName() {
        return localizedName;
    }
    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public String getKey() {
        return key;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public void setKey(String key) {
        this.key = key;
    }

}