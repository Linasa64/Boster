package com.example.boster;

import androidx.annotation.NonNull;

public class Deplacement {

    private String mode;
    private String date;
    private String ville;
    private String distance;
    private String co2;

    public Deplacement(String mode, String date, String ville, String distance, String co2) {
        this.mode = mode;
        this.date = date;
        this.ville = ville;
        this.distance = distance;
        this.co2 = co2;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDistance() {
        return distance;
    }

    public String getDate() {
        return date;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return "Deplacement du " + this.date + ": " + this.distance + " km, " + this.co2 + " non émis";
    }
}
