package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.R;

import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Florence LE BOURNOT on 02/01/2020
 */
public class NeighbourActivityTest {
    private NeighbourActivity mActivity = null;

    @Test
    public void testNameIsNotNull() {

        assertThat(mActivity.findViewById(R.id.cardNameNeighbour),notNullValue());
    }


}