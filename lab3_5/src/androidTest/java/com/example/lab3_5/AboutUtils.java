package com.example.lab3_5;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.Gravity;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;

public class AboutUtils {

    private static void openAboutViaDrawer() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.
        onView(withId(R.id.drawer_layout)).perform((ViewAction) DrawerActions.open(Gravity.LEFT)); // Open Drawer

        // Start the screen of your activity.
        onView(withId(R.id.drawer_nav_view))
                .perform((ViewAction) NavigationViewActions.navigateTo(R.id.activity_about));
    }

    static void openAbout() {openAboutViaDrawer();}
}
