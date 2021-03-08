package global;
/**
 * Klassen används för att representera
 * flyg som hämtats från databasen.
 * @author Simon Länsberg, William Husar
 * @version 2021-03-03
 */
public class Flight {
    private String flight_id;
    private String from;
    private String to;
    private String date;
    private String time;
    private String model;
    
    /**
     * Konstruktorn används för att skapa
     * ett nytt flyg med flera parametrar.
     * @param id Unikt id för flyget.
     * @param from Avgående destination.
     * @param to Ankommande destination.
     * @param date Datum för avgång.
     * @param time Tid för avgång.
     * @param model Flygplansmodell.
     */
    public Flight(String id, String from, String to, String date, String time, String model){
        this.flight_id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.model = model;
    }

    /**
     * Konstruktorn används för att skapa
     * ett nytt flyg med en parameter.
     * @param model Flygplansmodell.
     */
    public Flight(String model){
        this.model = model;
    }

    /**
     * @return Unikt id för flyget.
     */
    public String getFlight(){
        return this.flight_id;
    }
    
    /**
     * @return Avgående destination.
     */
    public String getFrom(){
        return this.from;  
    }

    /**
     * @return Ankommande destination.
     */
    public String getTo(){
        return this.to;
    }

    /**
     * @return Datum för avgång.
     */
    public String getDate(){
        return this.date;
    }

    /**
     * @return Tid för avgång.
     */
    public String getTime(){
        return this.time;
    }

    /**
     * @return Flygplansmodell.
     */
    public String getModel(){
        return this.model;     
    }
}
