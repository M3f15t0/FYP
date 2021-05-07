package com.tgsdco.fyp10.Model;

import java.util.Date;

public class Crops {

    private int id;
    private String name;
    private int planting_start_month;
    private int planting_start_day;
    private int planting_end_month;
    private int planting_end_day;
    private int growing_days;
    private int min_planting_temp;


    public Crops() {
    }

    public Crops(int id, String name, int planting_start_month, int planting_start_day, int planting_end_month, int planting_end_day, int growing_days, int min_planting_temp) {
        this.id = id;
        this.name = name;
        this.planting_start_month = planting_start_month;
        this.planting_start_day = planting_start_day;
        this.planting_end_month = planting_end_month;
        this.planting_end_day = planting_end_day;
        this.growing_days = growing_days;
        this.min_planting_temp = min_planting_temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlanting_start_month() {
        return planting_start_month;
    }

    public void setPlanting_start_month(int planting_start_month) {
        this.planting_start_month = planting_start_month;
    }

    public int getPlanting_start_day() {
        return planting_start_day;
    }

    public void setPlanting_start_day(int planting_start_day) {
        this.planting_start_day = planting_start_day;
    }

    public int getPlanting_end_month() {
        return planting_end_month;
    }

    public void setPlanting_end_month(int planting_end_month) {
        this.planting_end_month = planting_end_month;
    }

    public int getPlanting_end_day() {
        return planting_end_day;
    }

    public void setPlanting_end_day(int planting_end_day) {
        this.planting_end_day = planting_end_day;
    }

    public int getGrowing_days() {
        return growing_days;
    }

    public void setGrowing_days(int growing_days) {
        this.growing_days = growing_days;
    }

    public int getMin_planting_temp() {
        return min_planting_temp;
    }

    public void setMin_planting_temp(int min_planting_temp) {
        this.min_planting_temp = min_planting_temp;
    }
    @Override
    public String toString() {
        return this.name;
    }
}