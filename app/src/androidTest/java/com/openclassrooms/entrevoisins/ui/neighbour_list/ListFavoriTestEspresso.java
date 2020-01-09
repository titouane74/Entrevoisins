package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListFavoriTestEspresso {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void listFavoriTestEspresso() {
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(withId(R.id.tabs),0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_favori),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.cardNameNeighbour), withText("Caroline"),
                        childAtPosition(
                                allOf(withId(R.id.cardInfoNeighbour_rl),
                                        childAtPosition(
                                                withId(R.id.cardInfoNeighbour),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Caroline")));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_detail),
                                        childAtPosition(
                                                withId(R.id.layout_activity_neighbour),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list_favori),
                        withParent(withId(R.id.container))));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.cardNameNeighbour), withText("Jack"),
                        childAtPosition(
                                allOf(withId(R.id.cardInfoNeighbour_rl),
                                        childAtPosition(
                                                withId(R.id.cardInfoNeighbour),
                                                0)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Jack")));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_detail),
                                        childAtPosition(
                                                withId(R.id.layout_activity_neighbour),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.list_favori),
                        withParent(withId(R.id.container))));
        recyclerView3.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.cardNameNeighbour), withText("Dan"),
                        childAtPosition(
                                allOf(withId(R.id.cardInfoNeighbour_rl),
                                        childAtPosition(
                                                withId(R.id.cardInfoNeighbour),
                                                0)),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Dan")));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_detail),
                                        childAtPosition(
                                                withId(R.id.layout_activity_neighbour),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.list_favori),
                        withParent(withId(R.id.container))));
        recyclerView4.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.cardNameNeighbour), withText("Patrick"),
                        childAtPosition(
                                allOf(withId(R.id.cardInfoNeighbour_rl),
                                        childAtPosition(
                                                withId(R.id.cardInfoNeighbour),
                                                0)),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Patrick")));

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_detail),
                                        childAtPosition(
                                                withId(R.id.layout_activity_neighbour),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatImageButton4.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
