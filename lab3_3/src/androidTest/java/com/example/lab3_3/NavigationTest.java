package com.example.lab3_3;

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

import android.content.pm.ActivityInfo;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
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
            MainActivity.class, true, true);

    @Test
    public void aboutTest() {
        AboutUtils.openAbout(1);
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
    }

    //проходим вперед по кнопкам, затем возвращаемся pressBack'ом (навигация назад)

    @Test
    public void testFrom1to3toAboutBack2back1back() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        AboutUtils.openAbout(3);
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        assertSame(mRule.getActivity().getLifecycle().getCurrentState(), Lifecycle.State.DESTROYED);
    }

    // исключительно кнопочки

    @Test
    public void testAllButtons() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        AboutUtils.openAbout(2);
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));
    }

    // навигация вверх

    @Test
    public void testFrom1to3toAboutUp3Up2() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        AboutUtils.openAbout(3);
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));
    }

    //тестируем различные пути к About + возвращение

    @Test
    public void testFrom1to2back1toAboutUp() {

        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        AboutUtils.openAbout(1);
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        assertSame(mRule.getActivity().getLifecycle().getCurrentState(), Lifecycle.State.DESTROYED);
    }

    //Для проверки работоспособности кнопок после смены ориентации экрана

    private void checkButton(int id, int text) {
        onView(withId(id)).check(matches(isDisplayed()));
        onView(withId(id)).check(matches(isClickable()));
        onView(withId(id)).check(matches(withText(text)));
    }

    private void landscapeOrientation() throws InterruptedException {
        mRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Thread.sleep(1000);
    }

    private void portraitOrientation() throws InterruptedException {
        mRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Thread.sleep(1000);
    }

    //Переворачиваем экраны на каждом фрагменте

    @Test
    public void testRotate1() throws InterruptedException {
        landscapeOrientation();
        checkButton(R.id.bnToSecond, R.string.title_to_second);
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        portraitOrientation();
        checkButton(R.id.bnToSecond, R.string.title_to_second);
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        landscapeOrientation();
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));
    }

    @Test
    public void testRotate2Back() throws InterruptedException {
        onView(withId(R.id.bnToSecond)).perform(click());

        landscapeOrientation();
        checkButton(R.id.bnToFirst, R.string.title_to_first);
        checkButton(R.id.bnToThird, R.string.title_to_third);
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        portraitOrientation();
        checkButton(R.id.bnToFirst, R.string.title_to_first);
        checkButton(R.id.bnToThird, R.string.title_to_third);
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));

        landscapeOrientation();
        onView(withId(R.id.bnToThird)).perform(click());
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout2)).check(matches(isDisplayed()));
        landscapeOrientation();

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testRotate3() throws InterruptedException {
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());

        landscapeOrientation();
        checkButton(R.id.bnToFirst, R.string.title_to_first);
        checkButton(R.id.bnToSecond, R.string.title_to_second);
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        portraitOrientation();
        checkButton(R.id.bnToFirst, R.string.title_to_first);
        checkButton(R.id.bnToSecond, R.string.title_to_second);
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));

        landscapeOrientation();
        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testRotateAboutUp() throws InterruptedException {
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());

        AboutUtils.openAbout(3);
        landscapeOrientation();

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(click());
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));
        checkButton(R.id.bnToFirst, R.string.title_to_first);
        checkButton(R.id.bnToSecond, R.string.title_to_second);

        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
        checkButton(R.id.bnToSecond, R.string.title_to_second);
    }

    @Test
    public void testRotateAboutBack() throws InterruptedException {
        onView(withId(R.id.bnToSecond)).perform(click());
        onView(withId(R.id.bnToThird)).perform(click());

        AboutUtils.openAbout(3);
        landscapeOrientation();

        pressBackUnconditionally();
        onView(withId(R.id.drawer_layout3)).check(matches(isDisplayed()));
        checkButton(R.id.bnToFirst, R.string.title_to_first);
        checkButton(R.id.bnToSecond, R.string.title_to_second);

        onView(withId(R.id.bnToFirst)).perform(click());
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
        checkButton(R.id.bnToSecond, R.string.title_to_second);
    }
}