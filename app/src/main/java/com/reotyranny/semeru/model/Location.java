package com.reotyranny.semeru.model;

public class Location {

    private int key;

    private float longitude;

    private float latitude;

    private String name;

    private String city;

    private String address;

    private String state;

    private String zip;

    private String type;

    private String phone;

    private String website;

    public Location() {
        // empty constructor required for firebase!
    }

    public Location(int key, String name, float longitude, float latitude, String address,
            String city, String state, String zip, String type, String phone, String website) {

        this.key = key;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

// --Commented out by Inspection START (10/28/18, 11:29):
//    public String getCity() {
//        return city;
//    }
// --Commented out by Inspection STOP (10/28/18, 11:29)

    public int getKey() {
        return key;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
