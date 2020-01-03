package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyFavoriGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS_FAVORI = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d",true),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",true),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",true),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d",true)
    );

}
