import java.util.*;

public class TrainSelector<T> implements GraphADT<T> {

    /**
     * Vertex objects group a data field with an adjacency list of weighted
     * directed edges that lead away from them.
     */
    protected class Vertex {
        public T data; // vertex label or application specific data
        public LinkedList<Edge> edgesLeaving;

        public Vertex(T data) {
            this.data = data;
            this.edgesLeaving = new LinkedList<>();
        }

    }

    /**
     * Edge objects are stored within their source vertex, and group together
     * their target destination vertex, along with an integer weight.
     */
    protected class Edge {
        public Vertex target;
        public int weight;

        public Edge(Vertex target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    protected Hashtable<T, Vertex> vertices; // holds graph vertices, key=data
    public TrainSelector() { vertices = new Hashtable<>(); }

    /**
     * Insert a new vertex into the graph.
     *
     * @param data the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is
     *     already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(T data) {
        if(data == null)
            throw new NullPointerException("Cannot add null vertex");
        if(vertices.containsKey(data)) return false; // duplicate values are not allowed
        vertices.put(data, new Vertex(data));
        return true;
    }

    /**
     * Remove a vertex from the graph.
     * Also removes all edges adjacent to the vertex from the graph (all edges
     * that have the vertex as a source or a destination vertex).
     *
     * @param data the data item stored in the vertex to remove
     * @return true if a vertex with *data* has been removed, false if it was not in the graph
     * @throws NullPointerException if data is null
     */
    public boolean removeVertex(T data) {
        if(data == null) throw new NullPointerException("Cannot remove null vertex");
        Vertex removeVertex = vertices.get(data);
        if(removeVertex == null) return false; // vertex not found within graph
        // search all vertices for edges targeting removeVertex
        for(Vertex v : vertices.values()) {
            Edge removeEdge = null;
            for(Edge e : v.edgesLeaving)
                if(e.target == removeVertex)
                    removeEdge = e;
            // and remove any such edges that are found
            if(removeEdge != null) v.edgesLeaving.remove(removeEdge);
        }
        // finally remove the vertex and all edges contained within it
        return vertices.remove(data) != null;
    }

    /**
     * Insert a new directed edge with a positive edge weight into the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive integer)
     * @return true if the edge could be inserted or its weight updated, false
     *     if the edge with the same weight was already in the graph
     * @throws IllegalArgumentException if either source or target or both are not in the graph,
     *     or if its weight is < 0
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean insertEdge(T source, T target, int weight) {
        if(source == null || target == null)
            throw new NullPointerException("Cannot add edge with null source or target");
        Vertex sourceVertex = this.vertices.get(source);
        Vertex targetVertex = this.vertices.get(target);
        if(sourceVertex == null || targetVertex == null)
            throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
        if(weight < 0)
            throw new IllegalArgumentException("Cannot add edge with negative weight");
        // handle cases where edge already exists between these verticies
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex) {
                if(e.weight == weight) return false; // edge already exists
                else e.weight = weight; // otherwise update weight of existing edge
                return true;
            }
        // otherwise add new edge to sourceVertex
        sourceVertex.edgesLeaving.add(new Edge(targetVertex,weight));
        targetVertex.edgesLeaving.add(new Edge(sourceVertex,weight));
        return true;
    }

    /**
     * Remove an edge from the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge could be removed, false if it was not in the graph
     * @throws IllegalArgumentException if either source or target or both are not in the graph
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean removeEdge(T source, T target) {
        if(source == null || target == null) throw new NullPointerException("Cannot remove edge with null source or target");
        Vertex sourceVertex = this.vertices.get(source);
        Vertex targetVertex = this.vertices.get(target);
        if(sourceVertex == null || targetVertex == null) throw new IllegalArgumentException("Cannot remove edge with vertices that do not exist");
        // find edge to remove
        Edge removeEdge = null;
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex)
                removeEdge = e;
        if(removeEdge != null) { // remove edge that is successfully found
            sourceVertex.edgesLeaving.remove(removeEdge);
            return true;
        }
        return false; // otherwise return false to indicate failure to find
    }

    /**
     * Check if the graph contains a vertex with data item *data*.
     *
     * @param data the data item to check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *data* is null
     */
    public boolean containsVertex(T data) {
        if(data == null) throw new NullPointerException("Cannot contain null data vertex");
        return vertices.containsKey(data);
    }

    /**
     * Check if edge is in the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean containsEdge(T source, T target) {
        if(source == null || target == null) throw new NullPointerException("Cannot contain edge adjacent to null data");
        Vertex sourceVertex = vertices.get(source);
        Vertex targetVertex = vertices.get(target);
        if(sourceVertex == null) return false;
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex)
                return true;
        return false;
    }

    /**
     * Return the weight of an edge.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return the weight of the edge (0 or positive integer)
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     * @throws NoSuchElementException if edge is not in the graph
     */
    public int getWeight(T source, T target) {
        if(source == null || target == null) throw new NullPointerException("Cannot contain weighted edge adjacent to null data");
        Vertex sourceVertex = vertices.get(source);
        Vertex targetVertex = vertices.get(target);
        if(sourceVertex == null || targetVertex == null) throw new IllegalArgumentException("Cannot retrieve weight of edge between vertices that do not exist");
        for(Edge e : sourceVertex.edgesLeaving)
            if(e.target == targetVertex)
                return e.weight;
        throw new NoSuchElementException("No edge found between these vertices");
    }

    /**
     * Return the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int getEdgeCount() {
        int edgeCount = 0;
        for(Vertex v : vertices.values())
            edgeCount += v.edgesLeaving.size();
        return edgeCount;
    }

    /**
     * Return the number of vertices in the graph
     *
     * @return the number of vertices in the graph
     */
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Check if the graph is empty (does not contain any vertices or edges).
     *
     * @return true if the graph does not contain any vertices or edges, false otherwise
     */
    public boolean isEmpty() {
        return vertices.size() == 0;
    }


    /**
     * Path objects store a discovered path of vertices and the overal distance of cost
     * of the weighted directed edges along this path. Path objects can be copied and extended
     * to include new edges and verticies using the extend constructor. In comparison to a
     * predecessor table which is sometimes used to implement Dijkstra's algorithm, this
     * eliminates the need for tracing paths backwards from the destination vertex to the
     * starting vertex at the end of the algorithm.
     */
    protected class Path implements Comparable<Path> {
        public Vertex start; // first vertex within path
        public int distance; // sumed weight of all edges in path
        public List dataSequence; // ordered sequence of data from vertices in path
        public Vertex end; // last vertex within path

        /**
         * Creates a new path containing a single vertex.  Since this vertex is both
         * the start and end of the path, it's initial distance is zero.
         * @param start is the first vertex on this path
         */
        public Path(Vertex start) {
            this.start = start;
            this.distance = 0;
            this.dataSequence = new LinkedList<>();
            this.dataSequence.add(start.data);
            this.end = start;
        }

        /**
         * This extension constructor makes a copy of the path passed into it as an argument
         * without affecting the original path object (copyPath). The path is then extended
         * by the Edge object extendBy.
         * @param copyPath is the path that is being copied
         * @param extendBy is the edge the copied path is extended by
         */
        public Path(Path copyPath) {
            this.start = copyPath.start;
            this.distance = copyPath.distance;
            this.end = copyPath.end;
            this.dataSequence = new LinkedList<>();

            Iterator<T> iter = copyPath.dataSequence.iterator();

            while(iter.hasNext()) {
                this.dataSequence.add(iter.next());
            }

        }

        /**
         * Extends the current path by a single edge and vertex.
         * The distance, dataSequence, and end of this path are all updated as a result.
         * @param edge is the directed edge that is being added to this path
         */
        public void extend(Edge edge) {
          dataSequence.add(edge.target.data);
          distance += edge.weight;
          end = edge.target;
        }

        /**
         * Allows the natural ordering of paths to be increasing with path distance.
         * When path distance is equal, the string comparison of end vertex data is used to break ties.
         * @param other is the other path that is being compared to this one
         * @return -1 when this path has a smaller distance than the other,
         *         +1 when this path has a larger distance that the other,
         *         and the comparison of end vertex data in string form when these distances are tied
         */
        public int compareTo(Path other) {
            int cmp = this.distance - other.distance;
            if(cmp != 0) return cmp; // use path distance as the natural ordering
            // when path distances are equal, break ties by comparing the string
            // representation of data in the end vertex of each path
            return this.end.data.toString().compareTo(other.end.data.toString());
        }

        public String toString() {
            String path = "";
            int s = this.dataSequence.size() - 1;
            for (int i = 0; i < s; i++) {
                City n = (City) this.dataSequence.get(i);

                path += n.getName() + " -> ";

            }
            City last = (City) this.dataSequence.get(s);
            path += last.getName();
            return path;
        }
    }

    public City geCity(City city, TrainSelector<T> graph) {
        for (Vertex v : graph.vertices.values()) {
            City othr = (City) v.data;
            if (city.compareTo(othr) == 0) {
                return (City) v.data;
            }
        }
        return null; // Returns null if City is not found
    }

    /**
     * Uses Dijkstra's shortest path algorithm to find and return the shortest path
     * between two vertices in this graph: start and end. This path contains an ordered list
     * of the data within each node on this path, and also the distance or cost of all edges
     * that are a part of this path.
     * 
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the shortest path from start to end, as computed by Dijkstra's algorithm
     * @throws NoSuchElementException when no path from start to end can be found,
     *     including when no vertex containing start or end can be found
     */
    protected Path dijkstrasShortestPath(T start, T end) {
        int numVtx = this.getVertexCount();
        int numEdges = this.getEdgeCount();
        if (numVtx == 0 || numEdges == 0) throw new NullPointerException("NullPointerException: This Graph has no vertices or edges");
        if (start == null || end == null) throw new NullPointerException("NullPointerException: Start or End is null");

        // Visited nodes
        LinkedList<Vertex> visited = new LinkedList<>();
        //Priority Queue of paths
        PriorityQueue<Path> paths_pq = new PriorityQueue<Path>();
        
        Path first_Path = new Path(vertices.get(start));
        paths_pq.add(first_Path);
        visited.add(vertices.get(start));
        
        //while pq not empty, remove and look at at highest priority path
        while(!paths_pq.isEmpty()) {
            Path currentPath = paths_pq.remove();
            
            // If current path ends at Target, return current path
            if(currentPath.end.equals(vertices.get(end))) {
                return currentPath;
            }
            // Loops through neighbors
            for(Edge edge : currentPath.end.edgesLeaving) {
                // See if neighbor visited
                boolean isVisited = false;
                for(Vertex vertex : visited) {
                    if(edge.target == vertex) {
                    isVisited = true;
                    }
                }
                // If vertex not visited, copy path and extend, then add to queue
                if(!isVisited) {
                    Path newPath = new Path(currentPath);
                    newPath.extend(edge);
                    paths_pq.add(newPath);
                    visited.add(edge.target);
                }
            }
        }
        
        // No path found
        throw new NoSuchElementException("No path exists"); 
    }


    /**
     * Returns the shortest path between start and end.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     *
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return list of data item in vertices in order on the shortest path between vertex
     * with data item start and vertex with data item end, including both start and end
     * @throws NoSuchElementException when no path from start to end can be found
     *     including when no vertex containing start or end can be found
     */
    public List<T> shortestPath(T start, T end) {
        return dijkstrasShortestPath(start,end).dataSequence;
    }

    /**
     * Uses Dijkstras shortest path algorithm to find shortest path.
     * Returns string representation of Path returned
     * 
     * @param start Starting data item in starting Vertex of path
     * @param end Final data item in path
     * @return String representaion of Cities in shortest path
     */
    public String getShortestPathString(T start, T end) {
        String final_path = "";

        Path short_path = dijkstrasShortestPath(start,end);
        final_path = short_path.toString();

        return final_path;
    }


    /**
     * Returns the cost of the path (sum over edge weights) between start and end.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     *
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the end vertex for the path
     * @return the cost of the shortest path between vertex with data item start
     * and vertex with data item end, including all edges between start and end
     * @throws NoSuchElementException when no path from start to end can be found
     *     including when no vertex containing start or end can be found
     */
    public int getPathCost(T start, T end) {
        return dijkstrasShortestPath(start, end).distance;
    }

    public TrainSelector<City> graphSetup() {
        TrainSelector<City> cities = new TrainSelector<>();


        // Creates City objects for each City
        City ancona = new City("Ancona", "Marche", 473865);
        City bari = new City("Bari", "Puglia", 1247303);
        City bergamo = new City("Bergamo", "Lombardia", 1103768);
        City bologna = new City("Bologna", "Emilia-Romagna", 1011658);
        City bolzano = new City("Bolzano", "Trentino-Alto Adige", 533267);

        City caserta = new City("Caserta", "Compania", 904921);
        City catanzaro = new City("Catanzaro", "Calabria", 359841);
        City cortina = new City("Cortina d'Ampezzo", "Veneto", 5546);
        City cremona = new City("Cremona", "Lombardia", 357623);
        City fiumicino = new City("Fiumicino", "Lazio", 81426);

        City florence = new City("Florence", "Tuscany", 984991);
        City foggia = new City("Foggia", "Puglia", 626072);
        City genoa = new City("Genoa", "Liguria", 855834);
        City laspezia = new City("La Spezia", "Liguria", 219330);
        City lameziaterme = new City("Lamezia Terme", "Calabria", 67026);

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
        City trieste = new City("Trieste", "Friuli-Venezia Giulia", 232601);
        City turin = new City("Turin", "Piemonte", 2247780);
        City venice = new City("Venice", "Veneto", 846962);
        City verona = new City("Verona", "Veneto", 923950);

        // Inserts Cities into graph
        cities.insertVertex(ancona);
        cities.insertVertex(bari);
        cities.insertVertex(bergamo);
        cities.insertVertex(bologna);
        cities.insertVertex(bolzano);

        cities.insertVertex(caserta);
        cities.insertVertex(catanzaro);
        cities.insertVertex(cortina);
        cities.insertVertex(cremona);
        cities.insertVertex(fiumicino);

        cities.insertVertex(florence);
        cities.insertVertex(foggia);
        cities.insertVertex(genoa);
        cities.insertVertex(laspezia);
        cities.insertVertex(lameziaterme);

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
        cities.insertVertex(trieste);
        cities.insertVertex(turin);
        cities.insertVertex(venice);
        cities.insertVertex(verona);


        cities.insertEdge(turin, milan, 141);
        cities.insertEdge(turin, genoa, 163);
        cities.insertEdge(milan, genoa, 141);
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

        cities.insertEdge(genoa, laspezia, 84);
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
        cities.insertEdge(salerno, lameziaterme, 282);
        cities.insertEdge(catanzaro, lameziaterme, 27);
        cities.insertEdge(reggiodicalabria, lameziaterme, 129);

        return cities;
    }


}
