package com.hb.demo.model.db.place;

/**
 * Created by hb on 2017/7/15.
 */

public class City {
    private int    id;
    private String name;
    private String cityNum;

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

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }
}
