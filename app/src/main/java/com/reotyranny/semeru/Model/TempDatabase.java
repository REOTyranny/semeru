package com.reotyranny.semeru.Model;
import java.util.ArrayList;

public class TempDatabase {
    public static final TempDatabase _instance = new TempDatabase();
    public static TempDatabase getInstance() { return _instance; }

    private ArrayList<Account> database;
    public TempDatabase(){
        database = new ArrayList<>();
    }
    public void addToDatabase(Account account){
        database.add(account);
    }
    public ArrayList<Account> getDatabase(){
        return database;
    }

}
