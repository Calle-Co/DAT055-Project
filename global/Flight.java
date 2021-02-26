package global;
/**
 * This is a class for representing the flights of the application
 * @author Simon Länsberg, William Husar
 * @version 2021-02-26
 */
public class Flight {
    private String flight_id;
    private String from;
    private String to;
    private String date;
    private String time;
    private String model;
    
    public Flight(String id, String from, String to, String date, String time, String model){
        this.flight_id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.model = model;
    }

    public String getFlight(){
        return this.flight_id;
    }
    
    public String getFrom(){
        return this.from;  
    }

    public String getTo(){
        return this.to;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public String getModel(){
        return this.model;     
    }
}
