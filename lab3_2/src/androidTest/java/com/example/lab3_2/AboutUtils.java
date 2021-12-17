package com.example.lab3_2;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.Gravity;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.DrawerActions;

public class AboutUtils {

    private static void openAboutViaDrawer1() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.
        onView(withId(R.id.drawer_layout)).perform((ViewAction) DrawerActions.open(Gravity.LEFT)); // Open Drawer


        // Start the screen of your activity.
        onView(withId(R.id.nav_home)).perform(click());
    }

    private static void openAboutViaDrawer2() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout2)).check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.
        onView(withId(R.id.drawer_layout2)).perform((ViewAction) DrawerActions.open(Gravity.LEFT)); // Open Drawer


        // Start the screen of your activity.
        onView(withId(R.id.nav_home)).perform(click());
    }

    private static void openAboutViaDrawer3() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout3)).check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.
        onView(withId(R.id.drawer_layout3)).perform((ViewAction) DrawerActions.open(Gravity.LEFT)); // Open Drawer


        // Start the screen of your activity.
        onView(withId(R.id.nav_home)).perform(click());
    }

    public static void openAbout(int a) {
        if (a == 1) openAboutViaDrawer1();
        else if (a == 2) openAboutViaDrawer2();
        else openAboutViaDrawer3();
    }
}

