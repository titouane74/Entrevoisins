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
public class DummyNeighbourApiService implements  NeighbourApiService {
    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;



    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


    /**
     * Génère la liste des favoris à partir de la liste chargée dans le Dummy
     * @return : List<Neighbour> :retourne la liste des voisins qui sont parmis les favoris
     */
    @Override
    public List<Neighbour> getNeighboursFavori() {
        return triFavori(neighbours);
    }

    /**
     * Génère la liste des favoris à partir de la liste passée en paramètre
     * @param pNeighbourList : List<Neighbour> : liste des voisins
     * @return : List<Neighbour> :retourne la liste des voisins qui sont parmis les favoris
     */
    @Override
    public List<Neighbour> getNeighboursFavori(List<Neighbour> pNeighbourList) {

        return triFavori(pNeighbourList);
    }

    /**
     * Tri la liste passé en paramètre
     * @param pNeighbourList : List<Neighbour> : Liste de voisins à trier
     * @return : List<Neighbour> :retourne la liste triée
     */
    private List<Neighbour> triFavori(List<Neighbour> pNeighbourList) {
        List<Neighbour> lFavori = new ArrayList<>();

        for (int i = 0; i < pNeighbourList.size(); i++) {
            if (pNeighbourList.get(i).isFavori()){
                lFavori.add(pNeighbourList.get(i));
            }
        }
        return lFavori;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
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
