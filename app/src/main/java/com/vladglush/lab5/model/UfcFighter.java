package com.vladglush.lab5.model;

public class UfcFighter {
    private String last_name;
    private String thumbnail;

    public UfcFighter(String last_name, String thumbnail) {
        this.last_name = last_name;
        this.thumbnail = thumbnail;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}


