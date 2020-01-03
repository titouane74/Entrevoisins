package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Florence LE BOURNOT on 02/01/2020
 */
public class PutFavoriEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param pNeighbour
     */
    public PutFavoriEvent(Neighbour pNeighbour) {

        this.neighbour = pNeighbour;
    }
}
