import java.util.Scanner;

public class UserInterface {

    private static final String WELCOME_MSG = "Welcome to the Italian Train Route Selector";

    private static final String DESCRIPTION = "Pick any two Italian cities from the list below and the program will" +
                                                "find the shortest way to get from one city to the other via major Italian train lines";



    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        RouteState state = RouteState.MAIN_MENU;
        TrainSelector graph = new TrainSelector();
        Scanner scanner = new Scanner(System.in);

        while (!state.isDone()) {
            state = state.runState(scanner, graph);
        }

    }

}
