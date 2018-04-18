package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

public class TimeZone {
    @SerializedName("NextOffsetChange")
    private Object nextOffsetChange;
    @SerializedName("GmtOffset")
    private int gmtOffset;
    @SerializedName("Code")
    private String code;
    @SerializedName("IsDaylighSaving")
    private boolean isDaylightSaving;
    @SerializedName("Name")
    private String name;

    public void setNextOffsetChange(Object nextOffsetChange) {
        this.nextOffsetChange = nextOffsetChange;
    }

    public Object getNextOffsetChange() {
        return nextOffsetChange;
    }

    public void setGmtOffset(int gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public int getGmtOffset() {
        return gmtOffset;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setIsDaylightSaving(boolean isDaylightSaving) {
        this.isDaylightSaving = isDaylightSaving;
    }

    public boolean isIsDaylightSaving() {
        return isDaylightSaving;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
