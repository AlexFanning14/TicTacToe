package com.example.alexfanning.tictactoe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringEndsWith.endsWith;

/**
 * Created by alex.fanning on 18/08/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityBasicTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivtyTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void clickAnyBoardComp_ChangeToX(){
        onView(withId(R.id.boardComp00)).check(matches(withContentDescription("empty")));

        onView(withId(R.id.boardComp00)).perform(click());

        onView(withId(R.id.boardComp00)).check(matches(withContentDescription("X")));

        onView(withId(R.id.boardComp01)).check(matches(withContentDescription("empty")));

        onView(withId(R.id.boardComp01)).perform(click());

        onView(withId(R.id.boardComp01)).check(matches(withContentDescription("Y")));
    }
}




