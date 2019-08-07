package com.calibrage.a3ffarmerapp.Model;

import java.util.ArrayList;

public class MovieModal {
    private String collectionId;
    private String date;
    private String weight;
    private String cc;
    private String status;
    private ArrayList<MovieModal> powers;
    public MovieModal() {
        this.collectionId = collectionId;
        this.date = date;
        this.weight = weight;
        this.cc = cc;
        this.status = status;
    }


    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    public ArrayList<MovieModal> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<MovieModal> powers) {
        this.powers = powers;
    }
}
