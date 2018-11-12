package com.reotyranny.semeru;


import com.reotyranny.semeru.model.Donation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DonationConstructorTest {

    private String goodPlace = "Donation Location";
    private String goodShortDes = "This is a valid short description.";
    private String goodLongDes = "This is a valid long description."
            + "It is longer than the short description.";
    private float goodValue = 1.00f;
    private String goodCategory = "Hat";
    private String goodComments = "Valid comments";
    //private String badShortDes = "This is not a good short description"
            //+ " because it is longer than the long description.";
    private String newCateogry = "Dog";
    private String goodImageUrl = "validURL";


    @Test
    public void testEquals(){
        Donation d1 = new Donation(goodPlace, goodShortDes, goodLongDes,
                goodValue, goodCategory, goodComments);
        Donation d2 = new Donation(goodPlace, goodShortDes, goodLongDes,
                goodValue, goodCategory, goodComments);
        Donation d3 = new Donation(goodPlace, goodShortDes, goodLongDes,
                goodValue, goodCategory, goodComments, goodImageUrl);

        assertEquals(d1, d2);
        assertEquals(d2, d1);
        assertNotEquals(d1, d3);
        assertNotEquals(d3, d1);
        assertNotEquals(d1, null);
        assertNotEquals(d3, null);
    }

    @Test
    public void testExistingCategory(){
        Donation d4 = new Donation(goodPlace, goodShortDes, goodLongDes,
                goodValue, goodCategory, goodComments);

        assertEquals(goodCategory, "Hat");
        assertTrue(Donation.getCategories().size() == 6);
    }

    @Test
    public void testNewCategory() {
        Donation d5 = new Donation(goodPlace, goodShortDes, goodLongDes,
                goodValue, newCateogry, goodComments);

        assertEquals(newCateogry, "Dog");
        assertTrue(Donation.getCategories().size() == 7);
        assertTrue(Donation.getCategories().contains(newCateogry));
    }
}
