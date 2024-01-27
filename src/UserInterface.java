// import java.awt.*;
// import java.io.FileInputStream;
import java.util.Scanner;

public class UserInterface {

    private static final String[] CITIES = {
            "Ancona", "Balogna", "Bari", "Bergamo", "Bolzano",
            "Caserta", "Catanzaro", "Cortina", "Cremano", "Florence",
            "Fiumicino", "Foggia", "Genova", "La Spezia", "Lamenzia Terme",
            "Lecce", "Matera", "Milan", "Naples", "Padova",
            "Perugia", "Pescara", "Piacanza", "Pisa", "Potenza",
            "Ravenna", "Reggio di Calabria", "Roma", "Salerno", "Siena",
            "Taranto", "Torino", "Trieste", "Venice", "Verona"

    };

    // private static final String WELCOME_MSG = "|             Welcome to the Italian Train Route Selector                 |";
    // private static final String DESCRIPTION = "Pick any two Italian cities from the list below and the program will find\n" +
    //                                           "the shortest way to get from one city to the other via major Italian train\n" +
    //                                           "lines. Below is the List of Cities you can choose from:";



    public static <T> City getNextCity(Scanner scnr, TrainSelector<T> graph) {
        String city_name;
        city_name = scnr.nextLine().toLowerCase().replaceAll("\\s", "");
        city_name = city_name.substring(0, 1).toUpperCase() + city_name.substring(1);

        City startCity = new City(city_name);
        City s = graph.geCity(startCity, graph);
        return s;
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("|                         Welcome to the Italian Train Route Selector                         |");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("    Pick any two Italian cities from the list below and the program will find the shortest\n" +
                           "    way to get from one city to the other via major Italian train lines.");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Below is the List of Cities you can choose from:");
        System.out.println();


        int rows = 7;
        int columns = 5;
        int cellWidth = 20; 

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = i * columns + j;
                if (index < CITIES.length) {
                    System.out.print(String.format("%-" + cellWidth + "s", CITIES[index]));
                } else {
                    System.out.print(String.format("%-" + cellWidth + "s", ""));
                }
            }
            System.out.println();
        }
        System.out.println();

        TrainSelector<City> graph = new TrainSelector<City>();
        try {
            graph = graph.graphSetup();
        }
        catch (Exception e) {
            System.out.println("Graph unsuccessfully made");
        }

        System.out.println();

        Scanner scanner = new Scanner(System.in);

        // TODO (Optional): Get RouteState working
        // RouteState state = RouteState.MAIN_MENU;
        // RouteState state = new RouteState(scanner, graph);
        // RouteState state = RouteState.MAIN_MENU.runState(scanner, graph);
        // while (!state) {
        //    state = state.MAIN_MENU.runState(scanner, graph);
        // }

        City start;
        City dest;


        System.out.println("Enter starting location:");

        // boolean valid = false;
        start = getNextCity(scanner, graph);
        
        while (start == null) {
            System.out.println("Invalid City, please try again"); 
            start = getNextCity(scanner, graph);
        }
        System.out.println("Starting Location: " + start);
        // valid = false;
    
        System.out.println("Enter Destination:");
       
        dest = getNextCity(scanner, graph);
        while (dest == null) {
            System.out.println("Invalid City, please try again"); 
            dest = getNextCity(scanner, graph);
        }
        System.out.println("Destination: " + dest);

        System.out.println();

        

        System.out.println("Got Cities");

        int cost = graph.getPathCost(start, dest);


        System.out.println("The shortest path is:");
        System.out.println(graph.shortestPath(start, dest).toString());
        System.out.println("Total Distance: " + cost);


        System.out.println("Done!");

    }



}
