package com.calibrage.a3ffarmerapp.Model;

public class Model {

    private String ploteCode;
    private String fertilizer;
    private String dosage;
    private String comments;
    private String issue;
    private String recommendedBy;
    private String recommededOn;


    public Model(String ploteCode, String fertilizer, String dosage, String comments, String issue, String recommendedBy, String recommededOn) {
        this.ploteCode = ploteCode;
        this.fertilizer = fertilizer;
        this.dosage = dosage;
        this.comments = comments;
        this.issue = issue;
        this.recommendedBy = recommendedBy;
        this.recommededOn = recommededOn;
    }

    public String getPloteCode() {
        return ploteCode;
    }

    public void setPloteCode(String ploteCode) {
        this.ploteCode = ploteCode;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getRecommededOn() {
        return recommededOn;
    }

    public void setRecommededOn(String recommededOn) {
        this.recommededOn = recommededOn;
    }
}
