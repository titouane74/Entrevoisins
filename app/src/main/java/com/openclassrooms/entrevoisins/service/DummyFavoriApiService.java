package com.openclassrooms.entrevoisins.service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyFavoriRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourActivity;

import java.util.ArrayList;
import java.util.List;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.mNeighbours;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourActivity.PARENT_FAVORI;

/**
 * Dummy mock for the Api
 */
public class DummyFavoriApiService implements  FavoriApiService {
    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;

    @Override
    public void putFavori(Neighbour pNeighbour) {
        Log.d("API", "putFavori: ");
        pNeighbour.setFavori(!pNeighbour.isFavori());


    }
    @Override
    public void getNeighbourFavori(Context pContext, Neighbour pNeighbour, int pPosition) {

        Intent lIntentNeighbourActvitity = new Intent(pContext, NeighbourActivity.class);
        lIntentNeighbourActvitity.putExtra("name", pNeighbour.getName());
        lIntentNeighbourActvitity.putExtra("avatarUrl", pNeighbour.getAvatarUrl());
        lIntentNeighbourActvitity.putExtra("favori", pNeighbour.isFavori());
        lIntentNeighbourActvitity.putExtra("position",pPosition);
        lIntentNeighbourActvitity.putExtra("parent", PARENT_FAVORI);
        pContext.startActivity(lIntentNeighbourActvitity);

    }

}
