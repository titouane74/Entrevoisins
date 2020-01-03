package com.openclassrooms.entrevoisins.service;

import android.content.Context;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Florence LE BOURNOT on 03/01/2020
 */
public interface FavoriApiService {


    void putFavori( Neighbour pNeighbour) ;

    void getNeighbourFavori(Context pContext, Neighbour pNeighbour, int pPosition);
}
