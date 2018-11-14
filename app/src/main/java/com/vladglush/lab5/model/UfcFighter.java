package com.vladglush.lab5.model;

public class UfcFighter {
    private String first_name;
    private String last_name;
    private String thumbnail;
    private String weight_class;

    private int wins;
    private int losses;
    private int draws;

    public UfcFighter(String first_name, String last_name, String thumbnail, String weight_class, int wins, int losses, int draws) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.thumbnail = thumbnail;
        this.weight_class = weight_class;

        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getWeight_class() {
        return weight_class;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }
}
