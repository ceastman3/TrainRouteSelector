import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class routeTester {

    // Checks to ensure that City shows corresponding data
    // when prompted
    @Test
    public void cityInfoTester() {
        TrainSelector<City> nc = new TrainSelector<City>();

        City Ancona = new City("And");
        //System.out.println(Ancona.toString());
        nc.insertVertex(Ancona);

        nc.vertices.get(Ancona);

    }

    // Checks correct paths are returned
    @Test
    public void accuracyTester() {}

    // Checks if code handles invalid inputs correctly
    @Test
    public void invalidInputTester() {}

    // Checks handling of null inputs
    @Test
    public void nullInputTester() {}

    // Checks if code properly recognizes if city has already been visited
    // and prompts user
    @Test
    public void cityTrackerTester() {}

}
