package com.openclassrooms.entrevoisins.service;

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
//        System.out.println("TEST : Initialize");
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
        List<Neighbour> expectedNeighboursDel = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_DEL;
        assertFalse(expectedNeighboursDel.contains(neighbourToDelete));

    }

    @Test
    public void getNeighbourFavoriWithSuccess() {

        Neighbour lNeighbourAdd = service.getNeighbours().get(2);
        lNeighbourAdd.setFavori(true);

        List<Neighbour> neighboursFavoriAfterAdd = service.getNeighboursFavori();

//        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI;
        assertTrue(neighboursFavoriAfterAdd.contains(lNeighbourAdd));


/*
        System.out.println("NeighboursFavori " + neighboursFavori.size());
        for ( int i=0 ; i < service.getNeighboursFavori().size();i++) {
            System.out.println("NeighboursFavori " + service.getNeighboursFavori().get(i).getName() + " ID " + service.getNeighboursFavori().get(i).getId());
        }
        for ( int i=0 ; i < expectedNeighbours.size();i++) {
            System.out.println("NeighboursExpectedFavori " + expectedNeighbours.get(i).getName() + " ID " + expectedNeighbours.get(i).getId());
        }

*/
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
        Neighbour lNeighbourAdd = service.getNeighbours().get(9);
        lNeighbourAdd.setFavori(true);

        assertEquals(DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_ADD.size(),service.getNeighboursFavori().size());

        //TODO vérifié que le voisin est présant dans la liste des Favoris
        List<Neighbour> expectedNeighboursAdd = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_ADD;
        assertTrue(expectedNeighboursAdd.contains(lNeighbourAdd));
    }

    @Test
    public void delNeighbourFavoriWithSuccess() {

        int sizeNeighbourFavori = service.getNeighboursFavori().size();

        Neighbour lNeighbourDel = service.getNeighbours().get(3);
        lNeighbourDel.setFavori(true);
        //TODO service.getNeighboursFavori().size() = service.getNeighboursFavori().size() + 1
        assertEquals(sizeNeighbourFavori +1,service.getNeighboursFavori().size());
        //assertEquals(DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_ADD.size(),service.getNeighboursFavori().size());


        for ( int i=0 ; i < service.getNeighboursFavori().size();i++) {
            System.out.println("NeighboursFavori " + service.getNeighboursFavori().get(i).getName() + " ID " + service.getNeighboursFavori().get(i).getId());
        }

//        Neighbour lNeighbourDel = service.getNeighbours().get(9);
        lNeighbourDel.setFavori(false);
        //TODO service.getNeighboursFavori().size() = service.getNeighboursFavori().size() - 1

        for ( int i=0 ; i < service.getNeighboursFavori().size();i++) {
            System.out.println("NeighboursFavori " + service.getNeighboursFavori().get(i).getName() + " ID " + service.getNeighboursFavori().get(i).getId());
        }

        assertEquals(sizeNeighbourFavori ,service.getNeighboursFavori().size());

        //assertEquals(DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_DEL.size(),service.getNeighboursFavori().size());

        //TODO vérifié que le voisin n'est plus dans la liste des favoris
        List<Neighbour> expectedNeighboursDel = DummyNeighbourGenerator.DUMMY_NEIGHBOURS_FAVORI_DEL;
        assertFalse(expectedNeighboursDel.contains(lNeighbourDel));


        //TODO vérifié que le voisin est toujours dans la liste des voisins
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertTrue(expectedNeighbours.contains(lNeighbourDel));


    }

}
