package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    private static final String TEXTE_COURT = "Procedente igitur mox tempore cum adventicium nihil inveniretur, relicta ora maritima in Lycaoniam adnexam Isauriae se contulerunt ibique densis intersaepientes itinera praetenturis provincialium et viatorum opibus pascebantur.";
    private static final String TEXTE_LONG = "Victus universis caro ferina est lactisque abundans copia qua sustentantur, et herbae multiplices et siquae alites capi per aucupium possint, et plerosque mos vidimus frumenti usum et vini penitus ignorantes.\n" +
            "\n" +
            "Ut enim benefici liberalesque sumus, non ut exigamus gratiam (neque enim beneficium faeneramur sed natura propensi ad liberalitatem sumus), sic amicitiam non spe mercedis adducti sed quod omnis eius fructus in ipso amore inest, expetendam putamus.\n" +
            "\n" +
            "Abusus enim multitudine hominum, quam tranquillis in rebus diutius rexit, ex agrestibus habitaculis urbes construxit multis opibus firmas et viribus, quarum ad praesens pleraeque licet Graecis nominibus appellentur, quae isdem ad arbitrium inposita sunt conditoris, primigenia tamen nomina non amittunt, quae eis Assyria lingua institutores veteres indiderunt.\n" +
            "\n" +
            "Cuius acerbitati uxor grave accesserat incentivum, germanitate Augusti turgida supra modum, quam Hannibaliano regi fratris filio antehac Constantinus iunxerat pater, Megaera quaedam mortalis, inflammatrix saevientis adsidua, humani cruoris avida nihil mitius quam maritus; qui paulatim eruditiores facti processu temporis ad nocendum per clandestinos versutosque rumigerulos conpertis leviter addere quaedam male suetos falsa et placentia sibi discentes, adfectati regni vel artium nefandarum calumnias insontibus adfligebant.\n" +
            "\n" +
            "Quo cognito Constantius ultra mortalem modum exarsit ac nequo casu idem Gallus de futuris incertus agitare quaedam conducentia saluti suae per itinera conaretur, remoti sunt omnes de industria milites agentes in civitatibus perviis.";



    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d",true, TEXTE_COURT),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",true,TEXTE_LONG),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f",false,TEXTE_COURT),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a",false,TEXTE_COURT),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b",false,TEXTE_COURT),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c",false,TEXTE_LONG),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d",false,TEXTE_COURT),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",true,TEXTE_COURT),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d",false,TEXTE_LONG),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d",false,TEXTE_COURT),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d",true,TEXTE_LONG),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d",false,TEXTE_COURT)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }


    public static List<Neighbour> DUMMY_NEIGHBOURS_FAVORI = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d",true,TEXTE_COURT),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",true,TEXTE_LONG),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",true,TEXTE_COURT),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d",true,TEXTE_LONG)
    );

    public static List<Neighbour> DUMMY_NEIGHBOURS_FAVORI_ADD = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d",true,TEXTE_COURT),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",true,TEXTE_LONG),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",true,TEXTE_COURT),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d",true,TEXTE_COURT),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d",true,TEXTE_LONG)
    );

    public static List<Neighbour> DUMMY_NEIGHBOURS_FAVORI_DEL= Arrays.asList(
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e",true,TEXTE_LONG),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b",true,TEXTE_COURT),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d",true,TEXTE_LONG)
    );
}
