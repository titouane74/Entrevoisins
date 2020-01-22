package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position : integer : position de l'item sélectionné
     * @return : objet : instance du fragment sélectionné
     */
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return NeighbourFragment.newInstance();
        } else {
            return FavoriFragment.newInstance();
        }
    }
    /**
     * get the number of pages
     * @return : integer : retourne le nombre de page du viewpage
     */
    @Override
    public int getCount() {
        return 2;
    }
}