package com.example.cloudinary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.cloudinary.android.mvp.main.home.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.nytimes", appContext.getPackageName());
    }

    @Test
    public void testCase1(){
        onView(withId(R.id.btn)).perform(click());
    }

    @Test
    public void testCase2(){
        onView(withId(R.id.btn)).perform(click());
        onView(withText("Cancel")).check(matches(isDisplayed()));
    }

    @Test
    public void testCase3(){
        onView(withId(R.id.btn)).perform(click());
        onView(withText("Take Photo")).check(matches(isDisplayed()));
    }

    @Test
    public void testCase4(){
        onView(withId(R.id.btn)).perform(click());
        onView(withText("Choose from Gallery")).check(matches(isDisplayed()));
    }

    @Test
    public void testCase5(){
        onView(withId(R.id.upload)).perform(click());
    }

}
