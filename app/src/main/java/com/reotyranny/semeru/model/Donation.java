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

    /**
     * Creates a new Donation object
     *
     * @param place where the item was donated
     * @param shortDes short description of item
     * @param longDes full-length description of item
     * @param value monetary value of item
     * @param category what type of item the donated item is
     * @param comments any additional information about the item in text form
     */
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

    /**
     * Creates a new Donation object
     *
     * @param place where the item was donated
     * @param shortDes short description of item
     * @param longDes full-length description of item
     * @param value monetary value of item
     * @param category what type of item the donated item is
     * @param comments any additional information about the item in text form
     * @param imageUrl an image of the item
     */
    public Donation(String place, String shortDes, String longDes,
            float value, String category, String comments, String imageUrl) {
        this(place, shortDes, longDes, value, category, comments);
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the type of Donation
     *
     * @return the type of Donation
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the type for the Donation object
     *
     * @param category the type for the Donation object
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the additional comments associated with the Donation object
     *
     * @return the comments associated withe the Donation object
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the additional comments assoicated with the Donation object
     *
     * @param comments the above stated additional comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the url associated with the image of the Donation object
     *
     * @return the url associated with the image of the Donataion object
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the url associated with the image of the Donation object
     *
     * @param imageUrl the url associated with the image of the Donation object
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the full description of the Donation object
     *
     * @return the full description of the Donation object
     */
    public String getLongDes() {
        return longDes;
    }

    /**
     * Sets the full description of the Donation object
     *
     * @param longDes the full description of the Donation object
     */
    public void setLongDes(String longDes) {
        this.longDes = longDes;
    }

    /**
     * Get the location where the item was donated
     *
     * @return the location where the item was donated
     */
    public String getPlace() {
        return place;
    }

    /**
     * Sets the place where the item was donated
     *
     * @param place where the item was donated
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Gets the short version of the description of the item
     *
     * @return short description of the item
     */
    public String getShortDes() {
        return shortDes;
    }

    /**
     * Sets the short version of the description of the item
     *
     * @param shortDes short version of the description
     */
    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    /**
     * Gets the timestamp for the time the item was donated
     *
     * @return the time the item was donated
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for the time the item was donated
     * @param timestamp the timestamp for the time the item was donated
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the monetary value of the Donation object
     *
     * @return the monetary value of the Donation object
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the monetary value of the Donation object
     *
     * @param value the monetary value of the Donation object
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Creates an initial list of categories for any given Donation object
     */
    public void initialCategories() {
        categoryChoices.add("Kitchen");
        categoryChoices.add("Clothes");
        categoryChoices.add("Hat");
        categoryChoices.add("Other");
    }

    /**
     * Gets the timestamp from the computer at the time an item is donated
     * 
     * @return the timestamp from the computer at the time the item was donated
     */
    private String retrieveTimestamp() {
        String timer;
        Date date = new Date();
        timer = DateFormat.getDateTimeInstance().format(date);
        return timer;

    }

}
