package com.openclassrooms.entrevoisins.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

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
}
