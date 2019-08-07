package com.calibrage.a3ffarmerapp.Model;

public class RecommendationModel {
    private String plotId;
    private String palmArea;
    private String location;
    private String status;

    public RecommendationModel(String plotId, String palmArea, String location, String status) {
        this.plotId = plotId;
        this.palmArea = palmArea;
        this.location = location;
        this.status = status;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getPalmArea() {
        return palmArea;
    }

    public void setPalmArea(String palmArea) {
        this.palmArea = palmArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
