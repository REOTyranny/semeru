package com.reotyranny.semeru.model;

/**
 * Represents a location.
 */
public class Location {

    private String address;

    private String city;

    private int key;

    private float latitude;

    private float longitude;

    private String name;

    private String phone;

    private String state;

    private String type;

    private String website;

    private String zip;

    /**
     * Instantiates a location.
     */
    public Location() {
        // empty constructor required for firebase!
    }

    /**
     * Instantiates a location with non-null values.
     *
     * @param key       Firebase key
     * @param name      location name
     * @param longitude location longitude in decimal
     * @param latitude  location latitude in decimal
     * @param address   location address
     * @param city      location city
     * @param state     location state (U.S.)
     * @param zip       location ZIP code
     * @param type      location type
     * @param phone     location phone (U.S. format)
     * @param website   location website
     */
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

    /**
     * Getter method for address.
     *
     * @return location address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param address new location address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Getter method for location key.
     *
     * @return location key
     */
    public int getKey() {
        return key;
    }

    /**
     * Getter method for location latitude.
     *
     * @return location latitude in decimal format
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Setter method for location latitude.
     *
     * @param latitude a latitude in decimal format
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter method for location longitude.
     *
     * @return location longitude in decimal format
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Setter method for location longitude.
     *
     * @param longitude location longitude in decimal format.
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter method for location name.
     *
     * @return location name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for location name.
     *
     * @param name location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for location phone.
     *
     * @return location phone in U.S. format
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for location phone.
     *
     * @param phone location phone in U.S. format
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter method for location state.
     *
     * @return location state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for location state.
     *
     * @param state location state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter method for location type.
     *
     * @return location type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for location type.
     *
     * @param type location type
     */
    public void setType(String type) {
        this.type = type;
    }
//Unused methods commented out
//    public String getWebsite() {
//        return website;
//    }
//
//    public void setWebsite(String website) {
//        this.website = website;
//    }
//
//    public String getZip() {
//        return zip;
//    }
//
//    public void setZip(String zip) {
//        this.zip = zip;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }

}
