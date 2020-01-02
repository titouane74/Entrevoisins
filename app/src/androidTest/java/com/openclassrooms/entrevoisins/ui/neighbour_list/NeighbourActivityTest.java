package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Florence LE BOURNOT on 27/12/2019
 */
public class NeighbourActivityTest {
    @Rule
    public ActivityTestRule<NeighbourActivity> mActivityTestRule = new ActivityTestRule<NeighbourActivity>(NeighbourActivity.class);

    private NeighbourActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {

        assertNotNull(mActivity.findViewById(R.id.cardNameNeighbour));
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}