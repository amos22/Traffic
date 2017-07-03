package com.sumo.traffic;

public class Model {

    private int imgId;
    private String name;

    static int test;

    public Model(int id, String name) {
        this.imgId = id;
        this.name = name;
    }

    public int getImgId() {


        return imgId;

    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
        test = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}