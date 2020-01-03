package com.openclassrooms.entrevoisins.service;

import android.content.Context;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my Neighbours from Dummy initialisation
     * @return {@link List}
     */
    List<Neighbour> getNeighboursFavori();

    /**
     * Get all my Neighbours from uploaded list
     * @return {@link List}
     */
    List<Neighbour> getNeighboursFavori(List<Neighbour> pNeighbourList);


    List<Neighbour> triFavori(List<Neighbour> pNeighbourList);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    void getNeighbourFavori(Context pContext, Neighbour pNeighbour, int pPosition);

    void changeStatutFavori(Neighbour pNeighbour, boolean isFavori);
}
