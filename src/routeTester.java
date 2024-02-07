import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class routeTester {
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
        TrainSelector<City> nc = new TrainSelector<>();
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
