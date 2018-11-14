package com.reotyranny.semeru;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.*;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import com.reotyranny.semeru.controller.RegistrationScreenActivity;
import com.reotyranny.semeru.model.AccountType;
import java.util.Random;
import org.junit.*;
import org.junit.runner.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FirebaseUserTest {

    @Rule
    public ActivityTestRule mRegistrationScreenActivityActivityTestRule = new ActivityTestRule<>(
            RegistrationScreenActivity.class, true, false);

    private Random rand = new Random();

    private int randomNumber = rand.nextInt(100);


    @Before
    public void intent() {
        Intent intent = new Intent();
        AccountType a = AccountType.DONOR;
        intent.putExtra("type", a);
        mRegistrationScreenActivityActivityTestRule.launchActivity(intent);
        // Continue with your test
    }

    @Test
    public void testInvalidEmail() {
        fillName();
        //Check if email field is displayed
        onView(withId(R.id.editText_Email)).check(matches((isDisplayed())));
        fillInvalidEmail();
        fillValidPassword();
        Espresso.closeSoftKeyboard();
        onView(allOf(withId(R.id.button_Register))).perform(click());
        //Check if invalid email toast is displayed
        onView(withText("Invalid e-mail or password")).inRoot(withDecorView(
                not(is(mRegistrationScreenActivityActivityTestRule.getActivity()
                        .getWindow()
                        .getDecorView()))))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testInvalidPassword() {
        fillName();
        //Check if email field is displayed
        onView(withId(R.id.editText_Email)).check(matches((isDisplayed())));
        fillValidEmail();
        fillInvalidPassword();
        Espresso.closeSoftKeyboard();
        onView(allOf(withId(R.id.button_Register))).perform(click());
        //Check if invalid email toast is displayed
        onView(withText("Invalid e-mail or password")).inRoot(withDecorView(
                not(is(mRegistrationScreenActivityActivityTestRule.getActivity()
                        .getWindow()
                        .getDecorView()))))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testUserCreation() {

        //Check if email field is displayed
        onView(withId(R.id.editText_Email)).check(matches((isDisplayed())));
        //Check if email field is displayed
        onView(withId(R.id.editText_Name)).check(matches((isDisplayed())));
        //Check if password field is displayed
        onView(withId(R.id.editText_Password)).check(matches((isDisplayed())));
        fillName();
        fillValidEmail();
        fillValidPassword();
        Espresso.closeSoftKeyboard();
        //Click the register button
        onView(allOf(withId(R.id.button_Register))).perform(click());
        //Check if toast is displayed
        onView(withText("Registered successfully")).inRoot(withDecorView(
                not(is(mRegistrationScreenActivityActivityTestRule.getActivity()
                        .getWindow()
                        .getDecorView()))))
                .check(matches(isDisplayed()));

    }

    private void fillInvalidEmail() {
        onView(withId(R.id.editText_Email)).perform(
                clearText(), typeText("rafael"));
    }

    private void fillInvalidPassword() {
        pauseTestFor(501);
        onView(withId(R.id.editText_Password)).perform(
                replaceText("x"));
    }

    private void fillName() {

        onView(withId(R.id.editText_Name)).check(matches((isDisplayed())));
        onView(withId(R.id.editText_Name)).perform(clearText(), typeText(
                "Lil Pump " + randomNumber));
    }

    private void fillValidEmail() {
        onView(withId(R.id.editText_Email)).perform(
                clearText(), typeText(
                        "rafael" + randomNumber + "@test.com"));
    }

    private void fillValidPassword() {
        //Type a valid password
        pauseTestFor(500);
        onView(withId(R.id.editText_Password)).check(matches((isDisplayed())));
        onView(withId(R.id.editText_Password)).perform(
                replaceText("Test123aa@!"), pressKey(KeyEvent.KEYCODE_TAB));
    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
