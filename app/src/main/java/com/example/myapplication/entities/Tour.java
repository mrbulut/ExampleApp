package com.example.myapplication.entities;

import org.json.JSONArray;
import org.json.JSONObject;

public class Tour  {

    private int id;
    private int discount;
    private Double point;
    private String tourName;
    private String tourType;
    private String tourLocation;
    private String instructions;

    private String tourStatus;
    private JSONArray tourImageArray;
    private JSONObject tourDescArray;
    private JSONObject scheduleArray;



    private JSONObject priceArray;
    private JSONObject incAndExcArray;
    private JSONObject addInfoArray;

    public Tour() {

    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(String tourStatus) {
        this.tourStatus = tourStatus;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getTourLocation() {
        return tourLocation;
    }

    public void setTourLocation(String tourLocation) {
        this.tourLocation = tourLocation;
    }

    public JSONArray getTourImageArray() {
        return tourImageArray;
    }

    public void setTourImageArray(JSONArray tourImageArray) {
        this.tourImageArray = tourImageArray;
    }

    public JSONObject getTourDescArray() {
        return tourDescArray;
    }

    public void setTourDescArray(JSONObject tourDescArray) {
        this.tourDescArray = tourDescArray;
    }


    public JSONObject getScheduleArray() {
        return scheduleArray;
    }

    public void setScheduleArray(JSONObject scheduleArray) {
        this.scheduleArray = scheduleArray;
    }

    public JSONObject getPriceArray() {
        return priceArray;
    }

    public void setPriceArray(JSONObject priceArray) {
        this.priceArray = priceArray;
    }

    public JSONObject getIncAndExcArray() {
        return incAndExcArray;
    }

    public void setIncAndExcArray(JSONObject incAndExcArray) {
        this.incAndExcArray = incAndExcArray;
    }

    public JSONObject getAddInfoArray() {
        return addInfoArray;
    }

    public void setAddInfoArray(JSONObject addInfoArray) {
        this.addInfoArray = addInfoArray;
    }


}

