package com.calibrage.a3ffarmerapp.Model;

public class visitModel {
    private String plotId;
    private String farmerId;
    private String farmerName;
    private String plotArea;
    private String plotStatus;
    private String plotVillage;
    private String plotMandal;
    private String plotDistrict;
    private String comments;
    private String dateOfRequests;


    public visitModel(String plotId, String farmerId, String farmerName, String plotArea, String plotStatus, String plotVillage, String plotMandal, String plotDistrict, String comments, String dateOfRequests) {
        this.plotId = plotId;
        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.plotArea = plotArea;
        this.plotStatus = plotStatus;
        this.plotVillage = plotVillage;
        this.plotMandal = plotMandal;
        this.plotDistrict = plotDistrict;
        this.comments = comments;
        this.dateOfRequests = dateOfRequests;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public String getPlotStatus() {
        return plotStatus;
    }

    public void setPlotStatus(String plotStatus) {
        this.plotStatus = plotStatus;
    }

    public String getPlotVillage() {
        return plotVillage;
    }

    public void setPlotVillage(String plotVillage) {
        this.plotVillage = plotVillage;
    }

    public String getPlotMandal() {
        return plotMandal;
    }

    public void setPlotMandal(String plotMandal) {
        this.plotMandal = plotMandal;
    }

    public String getPlotDistrict() {
        return plotDistrict;
    }

    public void setPlotDistrict(String plotDistrict) {
        this.plotDistrict = plotDistrict;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDateOfRequests() {
        return dateOfRequests;
    }

    public void setDateOfRequests(String dateOfRequests) {
        this.dateOfRequests = dateOfRequests;
    }
}
