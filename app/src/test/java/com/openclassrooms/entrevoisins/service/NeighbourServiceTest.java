package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));

        //TODO vérifié que le voisin supprimer de la liste des voisins n'est plus non plus dans la liste des favoris
    }

    @Test
    public void getNeighbourFavoriWithSuccess() {
        List<Neighbour> neighbours = service.getNeighboursFavori();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void changeStatutFavori() {
        Neighbour lNeighbourTrue = service.getNeighbours().get(5);
        service.changeStatutFavori(lNeighbourTrue,true);
        assertEquals(true, lNeighbourTrue.isFavori());

        Neighbour lNeighbourFalse = service.getNeighbours().get(0);
        service.changeStatutFavori(lNeighbourFalse,false);
        assertEquals(false, lNeighbourFalse.isFavori());

    }

    @Test
    public void addNeighbourFavoriWithSuccess() {
        // Add
        List<Neighbour> neighboursAdd = service.getNeighbours();
        Neighbour lNeighbourAdd = neighboursAdd.get(9);
        lNeighbourAdd.setFavori(true);

        int countAdd = service.getNeighboursFavori().size();
        int expectedCountAdd = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_ADD.size();
        assertEquals(expectedCountAdd,countAdd);

        //TODO vérifié que le voisin est présant dans la liste des Favoris
    }

    @Test
    public void delNeighbourFavoriWithSuccess() {
        List<Neighbour> neighboursDel = service.getNeighbours();
        Neighbour lNeighbourDel = neighboursDel.get(0);
        lNeighbourDel.setFavori(false);

        int countDel = service.getNeighboursFavori().size();
        int expectedCountDel = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_DEL.size();
        assertEquals(expectedCountDel,countDel);

        //TODO vérifié que le voisin n'est plus dans la liste des voisins

        //TODO vérifié que le voisin est toujours dans la liste des voisins

    }

}
