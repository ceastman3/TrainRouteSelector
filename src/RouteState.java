import java.util.Locale;
import java.util.Scanner;

public enum RouteState {
    MAIN_MENU {
        /**
         * Returns whether the state is DONE
         *
         * @return false
         */
        public boolean isDone() {
            return false;
        }

        public RouteState runState(Scanner scanner, TrainSelector graph) {
            int num;
            System.out.println("\n[1] Learn about a city");
            System.out.println("[2] Find the Fastest Route between Cities");
            System.out.println("[0] Quit Program");
            String cmd = scanner.nextLine();

            try {
                num = Integer.parseInt(cmd);
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid command \"" + cmd + "\"");
                return MAIN_MENU;
            }

            if (num == 1) {
                return LEARN;
            }
            else if (num == 2) {
                return FIND_ROUTE;
            }
            else if (num == 0) {
                System.out.println("Ciao!");
                return DONE;
            }
            else {
                System.out.println("Warning: Invalid command \"" + cmd + "\"");
                return MAIN_MENU;
            }

        }

    },


    DONE {
        /**
         * Returns whether the state is DONE
         *
         * @return true
         */
        public boolean isDone() {
            return true;
        }

        /**
         * Does nothing, as the application has ended
         *
         * @param scanner scanner for user input
         * @return the next state of the AssignmentPlanner
         */
        public RouteState runState(Scanner scanner, TrainSelector graph) {
            return DONE;
        }
    },


    FIND_ROUTE {
        /**
         * Returns whether the state is DONE
         *
         * @return false
         */
        public boolean isDone() {
            return false;
        }

        public RouteState runState(Scanner scanner, TrainSelector graph) {
            boolean exists = false;
            System.out.println("Enter starting location:");
            String start = scanner.nextLine().toLowerCase();
//            if (!exists) {
//                System.out.println("Invalid City: Try again");
//                // TODO: Add option to list cities (Optional)
//                return FIND_ROUTE;
//            }

            System.out.println("Enter desired destination:");
            String dest = scanner.nextLine().toLowerCase();

            System.out.println("Shortest Path has a distance of: " +
                    graph.dijkstrasShortestPath(start, dest).distance +
                    "km");

            return MAIN_MENU;
        }

    },


    LEARN {
        /**
         * Returns whether the state is DONE
         *
         * @return false
         */
        public boolean isDone() {
            return false;
        }

        public RouteState runState(Scanner scanner, TrainSelector graph) {
            // TODO: Have Cities to select from
            System.out.println("COMING SOON!");
            return MAIN_MENU;
        }


    },

    CITIES {
        /**
         * Returns whether the state is DONE
         *
         * @return false
         */
        public boolean isDone() {
            return false;
        }

        public RouteState runState(Scanner scanner, TrainSelector graph) {
            // TODO: List Cities
            System.out.println("COMING SOON!");
            return MAIN_MENU;
        }

    },

    CITY_TREE {
        /**
         * Returns whether the state is DONE
         *
         * @return false
         */
        public boolean isDone() {
            return false;
        }
        public RouteState runState(Scanner scanner, TrainSelector graph) {
            // TODO: Find City
            return DONE;
        }
    };

    /**
     * Returns whether the state is DONE
     *
     * @return true if this assignment is DONE
     */
    public abstract boolean isDone();

    /**
     * Performs the behavior of this state by utilizing the values given by the scanner, updating the
     * queue, and returning the next state
     *
     * @param scanner scanner for user input
     * @param graph   current graph
     * @return the next state of the AssignmentPlanner
     */
    public abstract RouteState runState(Scanner scanner, TrainSelector graph);


}
