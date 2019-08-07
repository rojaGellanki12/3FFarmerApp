package com.calibrage.a3ffarmerapp.Model;

public class RecommendationListModel {
    private String plotCode;
    private String recommendedChemical;
    private String dosage;
    private String comments;
    private String issue;
    private String recommendedBy;
    private String recommendedOn;

    public RecommendationListModel(String plotCode, String recommendedChemical, String dosage, String comments, String issue, String recommendedBy, String recommendedOn) {
        this.plotCode = plotCode;
        this.recommendedChemical = recommendedChemical;
        this.dosage = dosage;
        this.comments = comments;
        this.issue = issue;
        this.recommendedBy = recommendedBy;
        this.recommendedOn = recommendedOn;
    }

    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
    }

    public String getRecommendedChemical() {
        return recommendedChemical;
    }

    public void setRecommendedChemical(String recommendedChemical) {
        this.recommendedChemical = recommendedChemical;
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

    public String getRecommendedOn() {
        return recommendedOn;
    }

    public void setRecommendedOn(String recommendedOn) {
        this.recommendedOn = recommendedOn;
    }
}
