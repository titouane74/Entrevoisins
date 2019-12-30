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


    @Override
    public List<Neighbour> getNeighboursFavori() {
        //TODO - Filtre des favoris
        List<Neighbour> lFavori = new ArrayList<>();

        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).isFavori()){
                lFavori.add(neighbours.get(i));
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
