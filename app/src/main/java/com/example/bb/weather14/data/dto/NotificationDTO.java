package com.example.bb.weather14.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by administrator on 4/11/18.
 */

public class NotificationDTO {

    @SerializedName("temp")
    private String temp;
    @SerializedName("location")
    private String location;
    @SerializedName("description")
    private String description;

    public NotificationDTO(String temp, String location, String description) {
        this.temp = temp;
        this.location = location;
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
