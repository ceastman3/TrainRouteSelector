// --== CS400 File Header Information ==--
// Name: Connor Eastman
// Email: ceastman
// Notes to Grader: <optional extra notes>

import java.awt.*;
import java.io.FileInputStream;
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

    private static final String WELCOME_MSG = "Welcome to the Italian Train Route Selector";
    private static final String DESCRIPTION = "Pick any two Italian cities from the list below and the program will\n" +
                                                "find the shortest way to get from one city to the other via major Italian train lines\n" +
                                                "Below is the List of Cities you can choose from:";


    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);
        System.out.println(DESCRIPTION);

        for (String name : CITIES) {
            System.out.println(name);
        }

        TrainSelector<City> graph = new TrainSelector<City>();
        graph = graph.graphSetup();


        RouteState state = RouteState.MAIN_MENU;
        Scanner scanner = new Scanner(System.in);

        while (!state.isDone()) {
            state = state.runState(scanner, graph);
        }

    }



}
