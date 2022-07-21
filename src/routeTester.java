// --== CS400 File Header Information ==--
// Name: Connor Eastman
// Email: ceastman
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;

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
        City ancona = new City("Ancona");
        City bologna = new City("Bologna");
        City bari = new City("Bari");
        City bergamo = new City("Bergamo");
        City bolzano = new City("Bolzano");

        City caserta = new City("Caserta");
        City catanzaro = new City("Catanzaro");
        City cortina = new City("Cortina");
        City cremona = new City("Cremona");
        City florence = new City("Florence");

        City fiumicino = new City("Fiumicino");
        City foggia = new City("Foggia");
        City genova = new City("Genova");
        City laspezia = new City("La Spezia");
        City lamenziaterme = new City("Lamenzia Terme");

        City lecce = new City("Lecce");
        City matera = new City("Matera");
        City milan = new City("Milan");
        City naples = new City("Naples");
        City padova = new City("Padova");

        City perugia = new City("Perugia");
        City pescara = new City("Pescara");
        City piacenza = new City("Piacenza");
        City pisa = new City("Pisa");
        City potenza = new City("Potenza");

        City ravenna = new City("Ravenna");
        City reggiodicalabria = new City("Reggio di Calabria");
        City rome = new City("Rome");
        City salerno = new City("Salerno");
        City siena = new City("Siena");

        City taranto = new City("Taranto");
        City torino = new City("Torino");
        City trieste = new City("Trieste");
        City venice = new City("Venice");
        City verona = new City("Verona");

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
     * Checks to ensure that City shows corresponding data
     *     // when prompted
     */
    @Test
    public void cityTester() {
        TrainSelector<City> nc = new TrainSelector<City>();

        City Ancona = new City("Ancona");
        City Rome = new City("Rome");
        City Florence = new City("Florence");

        nc.insertVertex(Ancona);
        nc.insertVertex(Rome);
        nc.insertVertex(Florence);

        Assertions.assertTrue(nc.containsVertex(Ancona));
        Assertions.assertTrue(nc.containsVertex(Rome));
        Assertions.assertTrue(nc.containsVertex(Florence));

        assertEquals("Ancona", Ancona.getName());
        assertEquals("Rome", Rome.getName());
        assertEquals("Florence", Florence.getName());

    }

    /**
     * Checks graph of Italy is properly setup
     */
    @Test
    public void graphSetupTester() {
        //TrainSelector<City> nc = new TrainSelector<City>();
        TrainSelector<City> nc = graphSetup();

        assertEquals(35, nc.getVertexCount());
        assertEquals(80, nc.getEdgeCount());

    }

    /**
     * Checks that paths are properly created
     */
    @Test
    public void pathConstructorTester() {
        TrainSelector<City> nc = new TrainSelector<City>();

    }


    /**
     * Checks if code handles invalid inputs correctly
     */
    @Test
    public void invalidInputTester() {}


    /**
     * Checks if code handles null inputs correctly
     */
    @Test
    public void nullInputTester() {
        TrainSelector<City> nc = graphSetup();
        City n = new City(null);
        City d = new City(null);

        Throwable e = assertThrows(NullPointerException.class, ()-> {nc.dijkstrasShortestPath(n, d);} );

        assertEquals("NullPointerException: Start or End is null", e.getMessage());
    }

    /**
     * Checks if code properly recognizes if city has already been visited
     * and prompts user
     */
    @Test
    public void cityTrackerTester() {}

}
