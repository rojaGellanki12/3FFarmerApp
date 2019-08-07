package com.calibrage.a3ffarmerapp.Model;

public class FertilizerModel {
    private String plotCode;
    private String age;

    private String village;
    private String size;
    private String landmark;
    private String Fertilizer1;
    private String Fertilizer2;
    private String Fertilizer3;

    public FertilizerModel(String plotCode, String age, String village, String size, String landmark, String fertilizer1, String fertilizer2, String fertilizer3) {
        this.plotCode = plotCode;
        this.age = age;
        this.village = village;
        this.size = size;
        this.landmark = landmark;

        Fertilizer1 = fertilizer1;
        Fertilizer2 = fertilizer2;
        Fertilizer3 = fertilizer3;
    }

    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFertilizer1() {
        return Fertilizer1;
    }

    public void setFertilizer1(String fertilizer1) {
        Fertilizer1 = fertilizer1;
    }

    public String getFertilizer2() {
        return Fertilizer2;
    }

    public void setFertilizer2(String fertilizer2) {
        Fertilizer2 = fertilizer2;
    }

    public String getFertilizer3() {
        return Fertilizer3;
    }

    public void setFertilizer3(String fertilizer3) {
        Fertilizer3 = fertilizer3;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
