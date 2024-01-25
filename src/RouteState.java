import java.util.Enumeration;
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
            System.out.println("\n[1] Find the Fastest Route between Cities");
            //System.out.println("[2] Learn about a city");
            System.out.println("[0] Quit Program");
            String cmd = scanner.nextLine();

            try {
                num = Integer.parseInt(cmd);
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid command \"" + cmd + "\"");
                return MAIN_MENU;
            }
            if (num == 1) {
                return FIND_ROUTE;
            }
//            else if (num == 2) {
//                return LEARN;
//            }
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
         * @param graph   current graph
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

        /**
         * This method takes a starting City and a destination City as
         * input and then finds the shortest possible path between
         * the cities using Dijkstra's shortest path algorithm
         *
         * @param scanner scanner for user input
         * @param graph   current graph
         * @return the shortest path between the two cities
         */
        public RouteState runState(Scanner scanner, TrainSelector graph) {
            Enumeration e = graph.vertices.elements();
            System.out.println("Enter starting location:");
            //String s = new City(scanner.nextLine().toLowerCase().replaceAll("\\s", ""));
            String start = scanner.nextLine().toLowerCase().replaceAll("\\s", "");


//            for (int i=0; i< graph.getVertexCount(); i++) {
//                if (c.compareTo(graph.get(i)));
//            }


            if (!graph.vertices.containsValue(start)) {
                System.out.println("Invalid City: Try again");
                return FIND_ROUTE;
            }

            System.out.println("Enter desired destination:");
            String dest = scanner.nextLine().toLowerCase();
            if (!graph.vertices.containsValue(dest)) {
                System.out.println("Invalid City: Try again");
                return FIND_ROUTE;
            }

            System.out.println("Your shortest path is:");
            System.out.println("Just hop on a train and hope for the best! (Coming Soon)");
            //System.out.println(graph.dijkstrasShortestPath(graph.vertices.getValue(s), graph.vertices.get(dest)).toString());
            System.out.println();


            return MAIN_MENU;
        }

    },


    // LEARN {
    //     /**
    //      * Returns whether the state is DONE
    //      *
    //      * @return false
    //      */
    //     public boolean isDone() {
    //         return false;
    //     }

    //     public RouteState runState(Scanner scanner, TrainSelector graph) {
    //         // TODO: Have Cities to select from
    //         System.out.println("COMING SOON!");
    //         return MAIN_MENU;
    //     }

        //},

//    CITIES {
//        /**
//         * Returns whether the state is DONE
//         *
//         * @return false
//         */
//        public boolean isDone() {
//            return false;
//        }
//
//        public RouteState runState(Scanner scanner, TrainSelector<T> graph, City c) {
//            // TODO: List Cities
//            System.out.println("COMING SOON!");
//            return MAIN_MENU;
//        }
//
//    },

//    CITY_TREE {
//        /**
//         * Returns whether the state is DONE
//         *
//         * @return false
//         */
//        public boolean isDone() {
//            return false;
//        }
//        public RouteState runState(Scanner scanner, TrainSelector<T> graph, City c) {
//            // TODO: Find City
//            return DONE;
//        }
    // };

    /**
     * Returns whether the state is DONE
     *
     * @return true if this assignment is DONE
     */
    // public abstract boolean isDone();

    /**
     * Performs the behavior of this state by utilizing the values given by the scanner, updating the
     * queue, and returning the next state
     *
     * @param scanner scanner for user input
     * @param graph   current graph
     * @return the next state of the AssignmentPlanner
     */
    // public abstract RouteState runState(Scanner scanner, TrainSelector graph);


}
