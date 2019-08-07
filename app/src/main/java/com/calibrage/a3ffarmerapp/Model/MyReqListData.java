package com.calibrage.a3ffarmerapp.Model;

public class MyReqListData{
    private String description;
    private int imgId;
    public MyReqListData(String description, int imgId) {
        this.description = description;
        this.imgId = imgId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}