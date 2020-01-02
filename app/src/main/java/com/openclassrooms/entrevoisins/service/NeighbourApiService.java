package com.openclassrooms.entrevoisins.service;

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
    List<Neighbour> getNeighboursFavoriInit();

    /**
     * Get all my Neighbours from uploaded list
     * @return {@link List}
     */
    List<Neighbour> getNeighboursFavori(List<Neighbour> pNeighbourList);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
}
