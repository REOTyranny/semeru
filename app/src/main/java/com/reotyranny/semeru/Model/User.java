package com.reotyranny.semeru.Model;
import java.util.List;
import java.util.LinkedList;

/**
 * Represents a User.
 * @author rpaulucci3
 */
public class User extends Account {

    private List<Donation> personalDonations = new LinkedList<>();
    public User(String name, String username, String password, String email){
        super(name,username,password,email, false);
    }

    public List<Donation> getPersonalDonations() {
        return personalDonations;
    }

    public void setPersonalDonations(List<Donation> personalDonations) {
        this.personalDonations = personalDonations;
    }
    public void donate(Donation stuffers, Location place){
        place.addDonation(stuffers);
    }
}

