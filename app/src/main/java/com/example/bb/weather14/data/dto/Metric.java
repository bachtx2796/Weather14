package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

public class Metric {
    @SerializedName("UnitType")
    private int unitType;
    @SerializedName("Value")
    private int value;
    @SerializedName("Unit")
    private String unit;

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

}
