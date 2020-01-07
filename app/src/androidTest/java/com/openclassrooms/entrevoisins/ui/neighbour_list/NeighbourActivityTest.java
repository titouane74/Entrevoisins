package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Florence LE BOURNOT on 27/12/2019
 */
public class NeighbourActivityTest {

    //Voisin qui doit s'afficher à l'écran
    private static int idNeighbourToTest = 0;

    private NeighbourActivity mActivity = null;
    private NeighbourApiService mService = null ;

    @Rule
    public ActivityTestRule<NeighbourActivity> mActivityTestRule = new ActivityTestRule<NeighbourActivity>(NeighbourActivity.class);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity,notNullValue());

        mService = DI.getNewInstanceApiService();
        assertThat(mService,notNullValue());
    }

/*
    @Test
    public void testNameIsNotNull() {
        onView(withId(R.id.cardNameNeighbour)).check(matches(notNullValue()));
    }
*/
/*

    @Test
    public void clickListNeighbour_opensNeighbourDetail() {
        //locate and click on the login button
//        onView(withId(R.id.list_neighbours)).perform(click());

        //check if the sign up screen is displayed by asserting that the first name edittext is displayed
//        onView(withId(R.id.cardNameNeighbour)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_activity_neighbour)), isDisplayed())));
    }
*/


    @Test
    public void neighbourDetailsActivity_isDisplayed() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(idNeighbourToTest, click()));

        onView(withId(R.id.layout_activity_neighbour)).check(matches(isDisplayed()));
    }

    @Test
    public void neighbourDetailsActivity_nameIsDisplayed() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(idNeighbourToTest, click()));

        onView(withId(R.id.layout_activity_neighbour)).check(matches(isDisplayed()));
//        onView(withId(R.id.collapsingToolbarLayout)).check(matches(withText(mService.getNeighbours().get(idNeighbourToTest).getName())));
        onView(withId(R.id.cardNameNeighbour)).check(matches(withText(mService.getNeighbours().get(idNeighbourToTest).getName())));
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
        mService = null;
    }



}