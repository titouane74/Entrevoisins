package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
        View view = mActivity.findViewById(R.id.nameNeighbour_tv);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}