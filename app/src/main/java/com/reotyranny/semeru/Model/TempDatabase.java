package com.reotyranny.semeru.Model;
import java.util.ArrayList;

public class TempDatabase {
    private ArrayList<Object> database;
    public TempDatabase(){
        database = new ArrayList<>();
    }
    public void addToDatabase(Object object){
        database.add(object);
    }
    public ArrayList getDatabase(){
        return database;
    }
}
