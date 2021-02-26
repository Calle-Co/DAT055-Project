package global;


/**
 * This is a class used to represent the destination of our flight application
 * @author Simon Länsberg
 * @version 2021-02-26
 */
public class Destination {
    private String destination;
    private String abv;
    private String[] set = new String[2];

    public Destination(String fullN, String shortN){
        this.destination = fullN;
        this.abv = shortN;
        set[0] = fullN;
        set[1] = shortN;
    }

    public String getDestination(){
        return this.destination;
    }

    public String getAbv(){
        return this.abv;
    }

    public String[] getSet(){
        return this.set;
    }
    
}
