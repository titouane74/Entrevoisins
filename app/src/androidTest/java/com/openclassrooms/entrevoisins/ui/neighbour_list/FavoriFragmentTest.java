package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
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

import java.sql.SQLOutput;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;


import android.support.test.espresso.contrib.RecyclerViewActions;


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

/*    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }*/

    private Matcher<View> childOfViewAtPositionMatcher(int childId, int position, Matcher<View> childMatcher) {

        return new TypeSafeMatcher<View>() {

            @Override
            protected boolean matchesSafely(View item) {

                RecyclerView lViewHolder = item.findViewById(R.id.list_favori);
                Matcher<View> lMatcher = hasDescendant(allOf(withId(childId),childMatcher));

                System.out.println("lViewHolder.getChildCount() " + lViewHolder.getChildCount());
                return lViewHolder != null && lMatcher.matches(lViewHolder.findViewHolderForItemId(position));
            }

            @Override
            public void describeTo(Description description) {}
        };
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
                boolean isInstanceGroup = false;
                boolean isMatchParent = false;
                boolean isViewEqual = false;

                if (parent instanceof ViewGroup ) {
                    isInstanceGroup=true;
                }
                if (parentMatcher.matches(parent)) {
//                    System.out.println("PARENTMATCHER  " + parentMatcher.toString());
                    isMatchParent=true;
                }
                if (view.equals(((ViewGroup) parent).getChildAt(position))) {
                    System.out.println("VIEW-VIEW-PARENT :  " + view.getParent());
                    System.out.println("VIEW-POSITION :  " + position);
                    System.out.println("VIEW-VIEW :  " + ((ViewGroup) parent).getChildAt(position));
                    isViewEqual=true;
                }

                return isInstanceGroup && isMatchParent && isViewEqual;
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
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

        // Méthode 1 : avec ouverture du layout du détail du voisin à chaque fois
        onView(ViewMatchers.withId(R.id.list_favori))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withText(mFavoriList.get(0).getName())).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar_detail)).perform(pressBack());
        onView(ViewMatchers.withId(R.id.list_favori))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        onView(withText(mFavoriList.get(1).getName())).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar_detail)).perform(pressBack());
        onView(ViewMatchers.withId(R.id.list_favori))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        onView(withText(mFavoriList.get(2).getName())).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar_detail)).perform(pressBack());
        onView(ViewMatchers.withId(R.id.list_favori))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3,click()));
        onView(withText(mFavoriList.get(3).getName())).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar_detail)).perform(pressBack());

        // Méthode 2 : sans ouverture du layout du détail du voisin
        onView(allOf(withId(R.id.item_list_name_fav),
                // 2ème temps : Dans la vue item_list_cl, retourne la vue-enfant en position 1 => item_list_name_fav
                childAtPosition(
                        // 1er temps : Dans la vue list_favori, retourne la vue-enfant de l'item en position 0 => item_list_cl
                            allOf(withId(R.id.item_list_cl),
                                childAtPosition(withId(R.id.list_favori), 0)),
                        1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(0).getName())));

        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(
                        allOf(withId(R.id.item_list_cl),
                            childAtPosition(withId(R.id.list_favori), 1)), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(1).getName())));

        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(
                        allOf(withId(R.id.item_list_cl),
                            childAtPosition(withId(R.id.list_favori), 2)), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(2).getName())));

        onView(allOf(withId(R.id.item_list_name_fav),
                childAtPosition(
                        allOf(withId(R.id.item_list_cl),
                         childAtPosition(withId(R.id.list_favori), 3)), 1),
                isDisplayed()))
                .check(matches(withText(mFavoriList.get(3).getName())));

    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
        mService = null;
    }
}