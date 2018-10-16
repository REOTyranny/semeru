package com.reotyranny.semeru.Model;
import java.util.LinkedList;
import java.util.List;
public class Location {

    private int key;
    private String name;
    private float longitude;
    private float latitude;
    private String city;
    private String address;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    private List<Employee> employeeRoster = new LinkedList<>();
    private List<Donation> donationHistory = new LinkedList<>();

    public Location( int key, String name, float longitude, float latitude, String address,
                     String city, String state, String zip, String type, String phone, String website){

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

    private void setAll(int key, String name, float longitude, float latitude, String address,
                        String city, String state, String zip, String type, String phone, String website){
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

    private void addDonation(Donation stuff){
        this.donationHistory.add(stuff);
    }

    public List<Donation> getDonationHistory() {
        return this.donationHistory;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getKey() {
        return key;
    }

    public void addEmployee(Employee emp){
        this.employeeRoster.add(emp);
    }
    public List<Employee> getEmployeeRoster(){
        return employeeRoster;
    }

}
