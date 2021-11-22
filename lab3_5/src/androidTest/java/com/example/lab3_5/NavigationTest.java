package com.example.lab3_5;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule mainRule = new ActivityScenarioRule(
            MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void aboutTest() {
        AboutUtils.openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
    }

    //проходим вперед по кнопкам, затем возвращаемся pressBack'ом (навигация назад)

    @Test
    public void testFrom1to3toAboutBack2back1back() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));

        AboutUtils.openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        assertSame(mainRule.getScenario().getState(), Lifecycle.State.DESTROYED);
    }

    // исключительно кнопочки

    @Test
    public void testAllButtons() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        AboutUtils.openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
    }

    // навигация вверх

    @Test
    public void testFrom1to3toAboutUp3Up2Up1() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));

        AboutUtils.openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()));

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        assertTrue(mainRule.getScenario().getState() == Lifecycle.State.DESTROYED);
    }

    //тестируем различные пути к About + возвращение

    @Test
    public void testFrom1to2back1toAbout() {


        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));

        AboutUtils.openAbout();
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        assertTrue(mainRule.getScenario().getState() == Lifecycle.State.DESTROYED);
    }

    //Для проверки работоспособности кнопок после смены ориентации экрана

    private void checkButton(int id, int text) {
        onView(withId(id)).check(matches(isDisplayed()));
        onView(withId(id)).check(matches(isClickable()));
        onView(withId(id)).check(matches(withText(text)));
    }
}