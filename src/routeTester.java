import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class routeTester {

//    @BeforeEach
//    public void init() {
//        TrainSelector<City> nc = new TrainSelector<City>();
//    }

    /**
     * Sets up graph of Italian cities and train lines
     * @return graph
     */
    public TrainSelector<City> graphSetup() {
        TrainSelector<City> cities = new TrainSelector<>();


        // Creates City objects for each City
        City ancona = new City("Ancona", "Marche", 473865);
        City bologna = new City("Bologna", "Emilia-Romagna", 1011658);
        City bari = new City("Bari", "Puglia", 1247303);
        City bergamo = new City("Bergamo", "Lombardia", 1103768);
        City bolzano = new City("Bolzano", "Trentino-Alto Adige", 533267);

        City caserta = new City("Caserta", "Compania", 904921);
        City catanzaro = new City("Catanzaro", "Calabria", 359841);
        City cortina = new City("Cortina", "Veneto", 5546);
        City cremona = new City("Cremona", "Lombardia", 357623);
        City florence = new City("Florence", "Tuscany", 984991);

        City fiumicino = new City("Fiumicino", "Lazio", 81426);
        City foggia = new City("Foggia", "Puglia", 626072);
        City genova = new City("Genova", "Liguria", 855834);
        City laspezia = new City("La Spezia", "Liguria", 219330);
        City lamenziaterme = new City("Lamenzia Terme", "Calabria", 67026);

        City lecce = new City("Lecce", "Puglia", 802018);
        City matera = new City("Matera", "Basilicata", 200101);
        City milan = new City("Milan", "Lombardia", 3219391);
        City naples = new City("Naples", "Compania", 3054956);
        City padova = new City("Padova", "Veneto", 928374);

        City perugia = new City("Perugia", "Umbria", 655844);
        City pescara = new City("Pescara", "Abruzzo", 314661);
        City piacenza = new City("Piacenza", "Emilia-Romagna", 284616);
        City pisa = new City("Pisa", "Tuscany", 416323);
        City potenza = new City("Potenza", "Basilicata", 377935);

        City ravenna = new City("Ravenna", "Emilia-Romagna", 155751);
        City reggiodicalabria = new City("Reggio di Calabria", "Calabria", 550967);
        City rome = new City("Rome", "Lazio", 4216553);
        City salerno = new City("Salerno", "Compania", 134840);
        City siena = new City("Siena", "Tuscany", 266621);

        City taranto = new City("Taranto", "Puglia", 584649);
        City torino = new City("Torino", "Piemonte", 2247780);
        City trieste = new City("Trieste", "Friuli-Venezia Giulia", 232601);
        City venice = new City("Venice", "Veneto", 846962);
        City verona = new City("Verona", "Veneto", 923950);

        // Inserts Cities into graph
        cities.insertVertex(ancona);
        cities.insertVertex(bologna);
        cities.insertVertex(bari);
        cities.insertVertex(bergamo);
        cities.insertVertex(bolzano);

        cities.insertVertex(caserta);
        cities.insertVertex(catanzaro);
        cities.insertVertex(cortina);
        cities.insertVertex(cremona);
        cities.insertVertex(florence);

        cities.insertVertex(fiumicino);
        cities.insertVertex(foggia);
        cities.insertVertex(genova);
        cities.insertVertex(laspezia);
        cities.insertVertex(lamenziaterme);

        cities.insertVertex(lecce);
        cities.insertVertex(matera);
        cities.insertVertex(milan);
        cities.insertVertex(naples);
        cities.insertVertex(padova);

        cities.insertVertex(perugia);
        cities.insertVertex(pescara);
        cities.insertVertex(piacenza);
        cities.insertVertex(pisa);
        cities.insertVertex(potenza);

        cities.insertVertex(ravenna);
        cities.insertVertex(reggiodicalabria);
        cities.insertVertex(rome);
        cities.insertVertex(salerno);
        cities.insertVertex(siena);

        cities.insertVertex(taranto);
        cities.insertVertex(torino);
        cities.insertVertex(trieste);
        cities.insertVertex(venice);
        cities.insertVertex(verona);


        cities.insertEdge(torino, milan, 141);
        cities.insertEdge(torino, genova, 163);
        cities.insertEdge(milan, genova, 141);
        cities.insertEdge(milan, piacenza, 70);
        cities.insertEdge(milan, cremona, 87);

        cities.insertEdge(cremona, bergamo, 99);
        cities.insertEdge(cremona, verona, 115);
        cities.insertEdge(verona, bolzano, 146);
        cities.insertEdge(verona, padova, 82);
        cities.insertEdge(verona, bologna, 115);

        cities.insertEdge(padova, bologna, 123);
        cities.insertEdge(padova, venice, 37);
        cities.insertEdge(venice, cortina, 168);
        cities.insertEdge(venice, trieste, 153);
        cities.insertEdge(piacenza, bologna, 147);

        cities.insertEdge(genova, laspezia, 84);
        cities.insertEdge(bologna, florence, 91);
        cities.insertEdge(bologna, ravenna, 82);
        cities.insertEdge(laspezia, pisa, 74);
        cities.insertEdge(florence, pisa, 78);

        cities.insertEdge(florence, siena, 93);
        cities.insertEdge(florence, rome, 261);
        cities.insertEdge(florence, perugia, 151);
        cities.insertEdge(ravenna, ancona, 143);
        cities.insertEdge(ancona, rome, 277);

        cities.insertEdge(rome, fiumicino, 32);
        cities.insertEdge(ancona, pescara, 146);
        cities.insertEdge(rome, caserta, 198);
        cities.insertEdge(pescara, foggia, 173);
        cities.insertEdge(caserta, foggia, 158);

        cities.insertEdge(caserta, naples, 34);
        cities.insertEdge(naples, salerno, 51);
        cities.insertEdge(foggia, bari, 122);
        cities.insertEdge(bari, lecce, 149);
        cities.insertEdge(salerno, potenza, 111);

        cities.insertEdge(potenza, matera, 104);
        cities.insertEdge(potenza, taranto, 151);
        cities.insertEdge(salerno, lamenziaterme, 282);
        cities.insertEdge(catanzaro, lamenziaterme, 27);
        cities.insertEdge(reggiodicalabria, lamenziaterme, 129);

        return cities;
    }


    /**
     * Checks to ensure that City shows 
     * corresponding data when prompted
     */
    @Test
    public void cityTester() {
        TrainSelector<City> nc = new TrainSelector<City>();

        City Ancona = new City("Ancona");
        City Rome = new City("Rome");
        City Florence = new City("Florence");

        City lecce = new City("Lecce", "Puglia", 802018);
        City matera = new City("Matera", "Basilicata", 200101);
        City milan = new City("Milan", "Lombardia", 3219391);

        nc.insertVertex(Ancona);
        nc.insertVertex(Rome);
        nc.insertVertex(Florence);

        nc.insertVertex(lecce);
        nc.insertVertex(matera);
        nc.insertVertex(milan);

        Assertions.assertTrue(nc.containsVertex(Ancona));
        Assertions.assertTrue(nc.containsVertex(Rome));
        Assertions.assertTrue(nc.containsVertex(Florence));

        Assertions.assertTrue(nc.containsVertex(lecce));
        Assertions.assertTrue(nc.containsVertex(matera));
        Assertions.assertTrue(nc.containsVertex(milan));

        assertEquals("Ancona", Ancona.getName());
        assertEquals("Rome", Rome.getName());
        assertEquals("Florence", Florence.getName());

        assertEquals("Lecce", lecce.getName());
        assertEquals("Matera", matera.getName());
        assertEquals("Milan", milan.getName());

        assertEquals("Puglia", lecce.getRegion());
        assertEquals("Basilicata", matera.getRegion());
        assertEquals("Lombardia", milan.getRegion());

        assertEquals(802018, lecce.getPop());
        assertEquals(200101, matera.getPop());
        assertEquals(3219391, milan.getPop());

    }

    /**
     * Checks graph of Italy contains correct 
     * number of nodes and edges
     */
    @Test
    public void graphSetupTester() {
        TrainSelector<City> nc = graphSetup();

        assertEquals(35, nc.getVertexCount());
        assertEquals(80, nc.getEdgeCount());

    }


    /**
     * Checks if code handles invalid inputs correctly
     */
    @Test
    public void invalidInputTester() { 
        TrainSelector<City> nc = graphSetup();
        City n = new City("Start");
        City d = new City("End");
        City s = new City("Stop1");

        nc.insertVertex(n);
        // Checks when wanting to create edge to null vertex
        Throwable e = assertThrows(IllegalArgumentException.class, ()-> { nc.insertEdge(n, d, 10); });
        assertEquals("IllegalArgumentException: Cannot add edge with vertices that do not exist", e.getMessage());

        nc.insertVertex(d);

        // Checks negative weight
        e = assertThrows(IllegalArgumentException.class, ()-> { nc.insertEdge(n, d, -5); });
        assertEquals("IllegalArgumentException: Cannot add edge with negative weight", e.getMessage());

        // Checks if s is detected as not in graph
        assertThrows(IllegalArgumentException.class, ()-> { nc.removeEdge(n, s); });
        assertThrows(IllegalArgumentException.class, ()-> { nc.getWeight(n, s); });

        // Throws NoSuchElementException if no edge between nodes
        assertThrows(NoSuchElementException.class, ()-> { nc.getWeight(n, d); });

    }


    /**
     * Checks if code handles null inputs correctly
     */
    @Test
    public void nullInputTester() {
        TrainSelector<City> nc = graphSetup();
        City n = new City("Start");
        City d = new City("End");

        // Checks Vertex
        assertThrows(NullPointerException.class, ()-> { nc.insertVertex(null); });
        assertThrows(NullPointerException.class, ()-> { nc.removeVertex(null); });
        


        // Checks edges
        assertThrows(NullPointerException.class, ()-> { nc.insertEdge(null, null, 10); });
        assertThrows(NullPointerException.class, ()-> { nc.removeEdge(null, null); });
        
        // Contains Vertex or Edge
        assertThrows(NullPointerException.class, ()-> { nc.containsEdge(null); });
        assertThrows(NullPointerException.class, ()-> { nc.containsVertex(null); });
        
        
        Throwable e = assertThrows(NullPointerException.class, ()-> { nc.dijkstrasShortestPath(n, d);} );
        assertEquals("NullPointerException: This Graph has no vertices or edges", e.getMessage());

        nc.insertVertex(n);
        nc.insertVertex(d);

        e = assertThrows(NullPointerException.class, ()-> { nc.dijkstrasShortestPath(n, d);} );
        assertEquals("NullPointerException: This Graph has no vertices or edges", e.getMessage());

        nc.insertEdge(n, d, 10);

        assertThrows(NullPointerException.class, ()-> { nc.dijkstrasShortestPath(n, null);} );
        
    }

}
