package global;

/**
 * Klassen används för att representera en
 * destination som hämtats från databasen.
 * @author Simon Länsberg
 * @version 2021-03-04
 */
public class Destination {
    private String destination;
    private String abv;

    /**
     * Konstruktorn används för att skapa en
     * ny destination med hjälp av två parametrar.
     * @param fullN Fullständigt namn på destinationen.
     * @param shortN Förkortning av destinationen.
     */
    public Destination(String fullN, String shortN){
        this.destination = fullN;
        this.abv = shortN;
    }

    /**
     * @return Fullständigt namn på destinationen.
     */
    public String getDestination(){
        return this.destination;
    }

    /**
     * @return Förkortning av destinationen.
     */
    public String getAbv(){
        return this.abv;
    }
}
