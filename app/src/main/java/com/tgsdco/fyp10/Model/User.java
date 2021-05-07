package com.tgsdco.fyp10.Model;

public class User {
    private int id;
    private int user_cropsid;
    private String location;

    public User() {
    }

    public User(int id, int user_cropsid, String location) {
        this.id = id;
        this.user_cropsid = user_cropsid;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_cropsid() {
        return user_cropsid;
    }

    public void setUser_cropsid(int user_cropsid) {
        this.user_cropsid = user_cropsid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
