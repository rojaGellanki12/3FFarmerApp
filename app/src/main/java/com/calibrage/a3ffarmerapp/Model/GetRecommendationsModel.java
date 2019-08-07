package com.calibrage.a3ffarmerapp.Model;

import java.util.ArrayList;

public class GetRecommendationsModel {
    private String fertilizer;
    private String uoM;
    private String year1;
    private String year17Above;
    private String year2;
    private String year3;

    private String year4And5;
    private String year6And7;
    private String year8To17;
    private String remarks;
    private ArrayList<String> powers;

    public GetRecommendationsModel() {
        this.fertilizer = fertilizer;
        this.uoM = uoM;
        this.year1 = year1;
        this.year17Above = year17Above;
        this.year2 = year2;
        this.year3 = year3;
        this.year4And5 = year4And5;
        this.year6And7 = year6And7;
        this.year8To17 = year8To17;
        this.remarks = remarks;
        this.powers = powers;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getUoM() {
        return uoM;
    }

    public void setUoM(String uoM) {
        this.uoM = uoM;
    }

    public String getYear1() {
        return year1;
    }

    public void setYear1(String year1) {
        this.year1 = year1;
    }

    public String getYear17Above() {
        return year17Above;
    }

    public void setYear17Above(String year17Above) {
        this.year17Above = year17Above;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }

    public String getYear3() {
        return year3;
    }

    public void setYear3(String year3) {
        this.year3 = year3;
    }

    public String getYear4And5() {
        return year4And5;
    }

    public void setYear4And5(String year4And5) {
        this.year4And5 = year4And5;
    }

    public String getYear6And7() {
        return year6And7;
    }

    public void setYear6And7(String year6And7) {
        this.year6And7 = year6And7;
    }

    public String getYear8To17() {
        return year8To17;
    }

    public void setYear8To17(String year8To17) {
        this.year8To17 = year8To17;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ArrayList<String> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<String> powers) {
        this.powers = powers;
    }
}
