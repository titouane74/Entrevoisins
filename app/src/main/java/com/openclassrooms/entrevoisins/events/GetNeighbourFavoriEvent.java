package com.openclassrooms.entrevoisins.events;

import android.content.Context;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Florence LE BOURNOT on 02/01/2020
 */
public class GetNeighbourFavoriEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;
    public Context mContext;
    public int mPosition;
    /**
     * Constructor.
     * @param pNeighbour
     */
    public GetNeighbourFavoriEvent(Context pContext,Neighbour pNeighbour, int pPosition) {

        this.neighbour = pNeighbour;
        mContext = pContext;
        mPosition = pPosition;
    }
}
