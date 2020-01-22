package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {


    private static final String TEXTE_COURT = "Nunc at quam posuere, aliquam urna nec, mollis elit. Praesent convallis est tincidunt purus placerat accumsan.";
    private static final String TEXTE_LONG = "Victus universis caro ferina est lactisque abundans copia qua sustentantur, et herbae multiplices et siquae alites capi per aucupium possint, et plerosque mos vidimus frumenti usum et vini penitus ignorantes.\n" +
            "\n" + "Ut enim benefici liberalesque sumus, non ut exigamus gratiam (neque enim beneficium faeneramur sed natura propensi ad liberalitatem sumus), sic amicitiam non spe mercedis adducti sed quod omnis eius fructus in ipso amore inest, expetendam putamus.\n" +
            "\n" + "Abusus enim multitudine hominum, quam tranquillis in rebus diutius rexit, ex agrestibus habitaculis urbes construxit multis opibus firmas et viribus, quarum ad praesens pleraeque licet Graecis nominibus appellentur, quae isdem ad arbitrium inposita sunt conditoris, primigenia tamen nomina non amittunt, quae eis Assyria lingua institutores veteres indiderunt.";


    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d",
                    "Charenton-Le-Pont","+33 6 68 95 72 34","www.facebook.fr/Caroline",
                    true, TEXTE_COURT),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",
                    "Ivry-sur-Seine","+33 6 95 95 58 30","www.facebook.fr/Jack",
                    true,TEXTE_LONG),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f",
                    "Vincennes","+33 6 25 96 74 35","www.facebook.fr/Chloé",
                    false,TEXTE_COURT),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a",
                    "Saint-Mandé","+33 6 77 22 59 33","www.facebook.fr/Vincent",
                    false,TEXTE_COURT),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b",
                    "Paris","+33 6 48 52 96 75","www.facebook.fr/Elodie",
                    false,TEXTE_COURT),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c",
                    "Saint-Maurice","+33 6 36 98 25 55","www.facebook.fr/Sylvain",
                    false,TEXTE_LONG),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d",
                    "Maisons-Alfort","+33 6 00 44 85 31","www.facebook.fr/Laetitia",
                    false,TEXTE_COURT),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",
                    "Vincennes","+33 6 05 69 34 75","www.facebook.fr/Dan",
                    true,TEXTE_COURT),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d",
                    "Saint-Mandé","+33 6 05 63 04 00 ","www.facebook.fr/Joseph",
                    false,TEXTE_LONG),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d",
                    "Charenton-Le-Pont","+33 6 69 53 04 77","www.facebook.fr/Emma",
                    false,TEXTE_COURT),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d",
                    "Alfortville","+33 6 08 05 49 99","www.facebook.fr/Patrick",
                    true,TEXTE_LONG),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d",
                    "Paris","+33 6 57 84 88 10","www.facebook.fr/Ludovic",
                    false,TEXTE_COURT)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }

}
