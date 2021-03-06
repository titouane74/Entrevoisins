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
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Changement de valeur du statut de favori ou non
     * @param pNeighbour : objet : voisin sélectionné
     * @param isFavori : indicateur de valeur du statut de favori
     */
    void changeStatutFavori(Neighbour pNeighbour, boolean isFavori);


}
