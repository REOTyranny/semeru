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
        locationDummy();
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
        places.add(new Location(1,"f",0,0,"y","o",
                "u","Ruari","have","a nice","day"));
        places.add(new Location(1,"I",0,0,"Love","Meatballs",
                "On","Pizza","I","am","raf"));

    }

    public void addAccount(Account account){
        // TODO: check if username already exists
        registeredAccounts.add(account);
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
