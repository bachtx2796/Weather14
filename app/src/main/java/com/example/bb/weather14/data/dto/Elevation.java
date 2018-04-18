package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

public class Elevation {
    @SerializedName("Metric")
    private Metric metric;
    @SerializedName("Imperial")
    private Imperial imperial;

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setImperial(Imperial imperial) {
        this.imperial = imperial;
    }

    public Imperial getImperial() {
        return imperial;
    }

}
