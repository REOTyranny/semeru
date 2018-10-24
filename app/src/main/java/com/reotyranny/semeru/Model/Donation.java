package com.reotyranny.semeru.Model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Donation {
    //TODO: Integrate with Firebase
    private String shortDes, longDes,fulltime;
    private String place ;
    private float value;
    private String comments;
    private String category;
    private List<String> categoryChoices = new LinkedList<>();

    public Donation() {}

    public Donation ( String place, String shortDes, String longDes,
                      float value, String category, String comments){
        this.fulltime = getTimeStamp();
        this.place = place;
        this.shortDes = shortDes;
        this.longDes = longDes;
        this.value = value;
        this.comments = comments;

        this.category = category;
        if (!categoryChoices.contains(category)){
            categoryChoices.add(category);
        }
    }

    public enum Category{Clothing,Hat,Kitchen,Electronics,Households,Others}


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

//    public void setfullTime(String time) {
//        this.fulltime = time;
//    }

    public String getCategory() {
        return category;
    }
    public void addCategory(String newCat){
        categoryChoices.add(newCat);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void initialCategories(){
        categoryChoices.add("Kitchen");
        categoryChoices.add("Clothes");
        categoryChoices.add("Hat");
        categoryChoices.add("Other");
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
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
