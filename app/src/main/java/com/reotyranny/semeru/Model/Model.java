package com.reotyranny.semeru.Model;
import java.util.ArrayList;
/**
 *
 * This is our facade to the Model.  We are using a Singleton design pattern to allow
 * access to the model from each controller.
*
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all registered accounts */
    private ArrayList<Account> registeredAccounts;
    public ArrayList<Location> places;

    /** the currently selected course, defaults to first course */
    private Account currentAccount;

    /** Null Object pattern, returned when no account is found */
    public final Account theNullAccount = new User("na", "na", "na", "na");

    /**
     * make a new model
     */
    private Model() {
        registeredAccounts = new ArrayList<>();
        loadDummyData();
        places = new ArrayList<>();
        //locationDummy();
    }

    /**
     * populate the model with some dummy data.
     */
    private void loadDummyData() {
        registeredAccounts.add(new User("test-user", "user@gmail.com", "abc", "user@gmail.com"));
        registeredAccounts.add(new Admin("test-admin", "admin@gmail.com", "def", "admin@gmail.com"));
        registeredAccounts.add(new Manager("test-manager", "manager@gmail.com", "ghi", "manager@gmail.com"));
        registeredAccounts.add(new Employee("test-employee", "employee@gmail.com", "klm", "employee@gmail.com", "Atlanta"));
    }

    public void locationDummy(){
        places.add(new Location(1,"free",0,0,"jottunheim","o",
                "u","Ruarai is awsome","have","a nice","day"));
        places.add(new Location(2,"I",0,0,"Love","Meatballs",
                "On","Pizza","I","am","raf"));
        places.add(new Location(3,"I2",0,0,"Love2","Meatballs2",
                "On2","Pizza2","I2","am2","raf2"));


    }

    public void addAccount(Account account){
        // TODO: check if username already exists
        registeredAccounts.add(account);
    }

    public void addLocation(Location location){
        // TODO: check if username already exists
        places.add(location);
    }


    public Account checkLogin(String username, String password) {
        for ( Account acc : registeredAccounts) {
            if (acc.getEmail().equals(username) & acc.getPassword().equals(password))
                return acc;
        }
        return theNullAccount;
    }

    /**
     *
     * @return  the currently selected account
     */
    public Account getCurrentAccount() { return currentAccount;}

    public void setCurrentAccount(Account account) { currentAccount = account; }

}
