package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BB on 4/7/2018.
 */

public class SugguestLocation {

    @SerializedName("description")
    private String description;

    public SugguestLocation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
