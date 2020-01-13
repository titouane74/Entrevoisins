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
        service = DI.getNewInstanceApiService();
    }


    /**
     * Récupération de la liste de tous les voisins
     */
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    /**
     * Suppression d'un voisin : dans la liste des voisins et des favoris
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));

        //Ajout d'un test complémentaire :
        //Vérification que le voisin supprimé de la liste des voisins n'est plus non plus dans la liste des favoris
        List<Neighbour> expectedNeighboursDel = service.getNeighboursFavori();
        assertFalse(expectedNeighboursDel.contains(neighbourToDelete));

    }

    /**
     * Récupération des voisins appartenant à la liste des favoris
     */
    @Test
    public void getNeighbourFavoriWithSuccess() {

        //Si la liste des favoris est vide, il faut d'abord ajouté un voisin
        Neighbour lNeighbourAdd = service.getNeighbours().get(2);
        lNeighbourAdd.setFavori(true);

        assertTrue(service.getNeighboursFavori().contains(lNeighbourAdd));
    }

    /**
     * Validation du changement de statut : de non favori à favori et de favori à non favori
     */
    @Test
    public void changeStatutFavori() {
        // Passage au statut de favori
        Neighbour lNeighbour = service.getNeighbours().get(5);
        service.changeStatutFavori(lNeighbour,true);
        assertTrue(lNeighbour.isFavori());

        // Passage au statut de non favori
        service.changeStatutFavori(lNeighbour,false);
        assertFalse(lNeighbour.isFavori());
    }

    /**
     * Ajout d'un voisin dans la liste des favoris
     */
    @Test
    public void addNeighbourFavoriWithSuccess() {
        int sizeNeighbourFavori = service.getNeighboursFavori().size();

        // Passage d'un voisin au statut favori
        Neighbour lNeighbourAdd = service.getNeighbours().get(9);
        lNeighbourAdd.setFavori(true);

        // Vérification qu'il y a un favori en plus
        assertEquals(sizeNeighbourFavori + 1,service.getNeighboursFavori().size());

        // Vérification que le voisin est présant dans la liste des Favoris
        assertTrue(service.getNeighboursFavori().contains(lNeighbourAdd));
    }

    /**
     * Suppression d'un voisi de la liste des favoris
     */
    @Test
    public void delNeighbourFavoriWithSuccess() {

        int sizeNeighbourFavori = service.getNeighboursFavori().size();

        //Ajout d'un voisin dans la liste des favoris en cas de liste vide
        Neighbour lNeighbour = service.getNeighbours().get(3);
        lNeighbour.setFavori(true);

        //Vérification qu'on a un voisin en plus
        assertEquals(sizeNeighbourFavori +1,service.getNeighboursFavori().size());

        //Suppression de ce même voisin de la liste des favoris
        lNeighbour.setFavori(false);

        //Vérification qu'on revenu au nombre initial de voisin
        assertEquals(sizeNeighbourFavori ,service.getNeighboursFavori().size());

        //Vérification que le voisin n'est plus dans la liste des favoris
        assertFalse(service.getNeighboursFavori().contains(lNeighbour));

        //Vérification que le voisin est toujours dans la liste des voisins
        assertTrue(service.getNeighbours().contains(lNeighbour));


    }

}
