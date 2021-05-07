package com.tgsdco.fyp10.Model;

import java.util.Date;

public class User_crops {

    private int id;
    private int cropid;
    private Date date_planted;
    private String cropName;

    private Crops crop;

    public Crops getCrop() {
        return crop;
    }

    public void setCrop(Crops crop) {
        this.crop = crop;
    }



    public User_crops() {
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCropid() {
        return cropid;
    }

    public void setCropid(int cropid) {
        this.cropid = cropid;
    }

    public Date getDate_planted() {
        return date_planted;
    }

    public void setDate_planted(Date date_planted) {
        this.date_planted = date_planted;
    }
    @Override
    public String toString() {
        return this.cropName;
    }
}
