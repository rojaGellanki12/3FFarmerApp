package com.calibrage.a3ffarmerapp.Model;


        import java.io.Serializable;

/**
 * Created by Ayo on 17/04/2017.
 */

public class Album implements Serializable {

    private  String mName;
    private int mAmount;
    private int mQuantity;
    private int thumbnail;
    private String disc;
    private String size;

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }



    public Album(String mName, int mAmount, int thumbnail, String disc, String size) {
        this.mName = mName;
        this.mAmount = mAmount;
        this.thumbnail = thumbnail;
        this.disc = disc;
        this.size = size;
        this.mQuantity = 1;
    }

    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public int getmAmount() {
        return mAmount;
    }

    public int getmQuantity(){
        return mQuantity;
    }

    public void addToQuantity(){
        this.mQuantity += 1;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 1){
            this.mQuantity -= 1;
        }
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

