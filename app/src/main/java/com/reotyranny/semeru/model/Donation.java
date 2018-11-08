package com.reotyranny.semeru.model;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


public class Donation {

    public enum Category {Clothing, Hat, Kitchen, Electronics, Households, Others}

    private String category;

    private final Collection<String> categoryChoices = new LinkedList<>();

    private String comments;

    private String imageUrl;

    private String longDes;

    private String place;

    private String shortDes;

    private String timestamp;

    private float value;

    public Donation() {
        // this empty constructor is required for firebase!! do not remove !
    }

    public Donation(String place, String shortDes, String longDes,
            float value, String category, String comments) {
        this.timestamp = retrieveTimestamp();
        this.place = place;
        this.shortDes = shortDes;
        this.longDes = longDes;
        this.value = value;
        this.comments = comments;

        this.category = category;
        if (!categoryChoices.contains(category)) {
            categoryChoices.add(category);
        }
    }

    public Donation(String place, String shortDes, String longDes,
            float value, String category, String comments, String imageUrl) {
        this(place, shortDes, longDes, value, category, comments);
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLongDes() {
        return longDes;
    }

    public void setLongDes(String longDes) {
        this.longDes = longDes;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void initialCategories() {
        categoryChoices.add("Kitchen");
        categoryChoices.add("Clothes");
        categoryChoices.add("Hat");
        categoryChoices.add("Other");
    }

    private String retrieveTimestamp() {
        String timer;
        Date date = new Date();
        timer = DateFormat.getDateTimeInstance().format(date);
        return timer;

    }

}
