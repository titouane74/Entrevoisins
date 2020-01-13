package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Florence LE BOURNOT on 07/01/2020
 */
public class FavoriFragmentTest {

    private ListNeighbourActivity mActivity = null;
    private NeighbourApiService mService = null ;

    //Liste des voisins favoris qui doivent s'afficher
    private List<Neighbour> mFavoriList;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<ListNeighbourActivity>(ListNeighbourActivity.class);


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


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity,notNullValue());

        mService = DI.getNewInstanceApiService();
        assertThat(mService,notNullValue());

        //Charge la liste des favoris
        mFavoriList = mService.getNeighboursFavori();
    }


    /**
     * Vérification que le recyclerview n'est pas vide et affiche au moins un item
     */
    @Test
    public void favoriList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_favori))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Vérification que la liste des favoris ne contient que des voisins marqués en favoris
     */
    @Test
    public void favoriList_shouldContainOnlyFavoriNeighbours() {
        //On se positionne sur l'onglet des favoris
        onView(withContentDescription("Favorites"))
                .perform(click());

        //On vérifie que la liste s'affiche avec 4 items
        onView(allOf(withId(R.id.list_favori), isDisplayed()))
                .check(withItemCount(4));

        //On vérifie pour chaque item qu'il est bien dans la liste des favoris en comparant le nom du voisin
        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(childAtPosition(withId(R.id.list_favori), 0), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(0).getName())));
        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(childAtPosition(withId(R.id.list_favori), 1), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(1).getName())));
        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(childAtPosition(withId(R.id.list_favori), 2), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(2).getName())));
        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(childAtPosition(withId(R.id.list_favori), 3), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(3).getName())));

    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
        mService = null;
    }
}