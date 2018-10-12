package com.reotyranny.semeru.Model;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Donation {
    private String shortDes, longDes,category,fulltime;
    private Location place ;
    private float value;
    private String comments;

    public Donation ( Location place, String shortDes, String longDes,
                      float value, String category, String comments){
        this.fulltime = getTimeStamp();
        this.place = place;
        this.shortDes = shortDes;
        this.longDes = longDes;
        this.value = value;
        this.category = category;
        this.comments = comments;

    }


    public String getTimeStamp(){
        String timer;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        timer = formatter.format(date);
        return timer;

    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getfullTime() {
        return fulltime;
    }

    public void setfullTime(String time) {
        this.fulltime = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLongDes() {
        return longDes;
    }

    public void setLongDes(String longDes) {
        this.longDes = longDes;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public Location getPlace() {
        return place;
    }

    public void setPlace(Location place) {
        this.place = place;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getFulltime() {
        return fulltime;
    }
}
