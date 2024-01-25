import java.util.*;

public class City implements Comparable<City> {
    private String Name;    // Name of City
    private String Region;    // Region that City resides
    private int Pop;    // Population of City
    //private static int idGenerator = 0;
    //public final int ID;

    private static final String[] CITIES = {
        "Ancona",    "Balogna",           "Bari",      "Bergamo",   "Bolzano",
        "Caserta",   "Catanzaro",          "Cortina",  "Cremano",   "Florence",
        "Fiumicino", "Foggia",             "Genova",   "La Spezia", "Lamenzia Terme",
        "Lecce",     "Matera",             "Milan",    "Naples",    "Padova",
        "Perugia",   "Pescara",            "Piacanza", "Pisa",      "Potenza",
        "Ravenna",   "Reggio di Calabria", "Roma",     "Salerno",   "Siena",
        "Taranto",   "Torino",             "Trieste",  "Venice",    "Verona"
    };

    /**
     * Creates a new City with the given name, region, and population
     *
     * @param name name of city
     */
    public City(String name) {
        this.Name = name;
        this.Region = "Italy";
        this.Pop = Integer.MAX_VALUE;
    }

    /**
     * Creates a new City with the given name, region, and population
     *
     * @param name name of city
     * @param region region that city resides
     * @param pop population of city
     */
    public City(String name, String region, int pop) {
        this.Name = name;
        this.Region = region;
        this.Pop = pop;
    }

    /**
     * Returns the name of the City
     *
     * @return the name of the City
     */
    public String getName() { return this.Name; }

    /**
     * Returns the Region of the City
     *
     * @return the Region of the City
     */
    public String getRegion() { return this.Region; }

    /**
     * Returns the population of the City
     *
     * @return the population of the City
     */
    public int getPop() { return this.Pop; }

    /**
     * Checks whether this City equals another object passed as input
     *
     * @param o Object to compare to
     * @return {@code true} if the given Object is a City and has the same name, region, and population as
     *         this Assignment
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof City)) {
            return false;
        }
        City other = (City) o;
        return other.Name.equals(this.Name) &&
                other.Region.equals(this.Region) &&
                 other.Pop == this.Pop;
    }

    /**
     * Compares this City to another City based on their name
     *
     * @param o City being compared to
     * @return 0 if Names are the same
     *         -1 if names are different
     */
    @Override
    public int compareTo(City o) {
        if (this.Name.equalsIgnoreCase(o.Name)) {
            return 0;
        }
        else {
            return -1;
        }
    }

    /**
     * Returns a String representing this City containing its name, region, and population.
     *
     * @return a String representing this City
     */
    @Override
    public String toString() {
        return "City: " + this.Name + "\n" +
                "Region: " + this.Region + "\n" +
                "Population: " + this.Pop + "\n";
    }


}
