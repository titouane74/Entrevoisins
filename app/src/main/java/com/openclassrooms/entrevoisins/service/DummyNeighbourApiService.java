package com.openclassrooms.entrevoisins.service;

import android.util.Log;
import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.ArrayList;
import java.util.List;

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


    /**
     * Change le statut du voisin en favori ou non en fonction de la valeur passée en paramètre
     * @param pNeighbour : objet : voisin concerné par le changement de statut
     * @param pValue : boolean : nouvelle valeur que doit prendre le voisin
     */
    @Override
    public void changeStatutFavori(Neighbour pNeighbour, boolean pValue) {
        try {
            neighbours.get(neighbours.indexOf(pNeighbour)).setFavori(pValue);
        } catch (ArrayIndexOutOfBoundsException pE) {
            Log.d("API", "changeStatutFavori: " + pNeighbour.getId() +  " - " + neighbours.get(0).getId());
        }

    }

}
