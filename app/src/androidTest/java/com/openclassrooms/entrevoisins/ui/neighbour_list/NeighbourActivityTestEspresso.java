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
public class NeighbourActivityTestEspresso {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void neighbourActivityTestEspresso() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.layout_activity_neighbour),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                withId(R.id.action_bar_root),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.cardNameNeighbour), withText("Chloé"),
                        childAtPosition(
                                allOf(withId(R.id.cardInfoNeighbour_rl),
                                        childAtPosition(
                                                withId(R.id.cardInfoNeighbour),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Chloé")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.cardNameNeighbour), withText("Chloé"),
                        childAtPosition(
                                allOf(withId(R.id.cardInfoNeighbour_rl),
                                        childAtPosition(
                                                withId(R.id.cardInfoNeighbour),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

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
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.btnfloat_favoris),
                        childAtPosition(
                                allOf(withId(R.id.layout_activity_neighbour),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());
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
