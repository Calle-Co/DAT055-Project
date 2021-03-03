package global;

/**
 * Simple way of representing a person who is a part of a booking
 * @author Simon Länsberg
 * @version 2021-02-22
 */
public class InfoHolding {
    private String owner = null;
    private String age = null;
    private String name = null;
    private String seat = null;
    private String fid = null;

    /**
     * The constructor is used to create a new representation of a person
     * @param name Name of the person
     * @param age Age of the person
     * @param owner Username of the "main" person who peforms the booking
     */
    public InfoHolding(String name, String age, String owner, String seat, String fid) {
        this.name = name;
        this.age  = age;
        this.owner = owner;
        this.seat = seat;
        this.fid = fid;
    }

    /**
     * Getter funtion for the name
     * @return Returns the name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Getter function for the age
     * @return Returns the age
     */
    public String getAge(){
        return this.age;       
    }

    public String getOwner(){
        return this.owner;
    }

    /**
     * Getter function for the seat number
     * @return Returns the seatnumber
     */
    public String getSeat(){
        return this.seat;
    }

    /**
     * a edited toString function that returns both name age and owner of this instance.
     * @return
     */
    public String toPrint(){
        return ("Name : " + this.name+ " \nAge: "+this.age);
    }

	public String getFID() {
		return this.fid;
	}
    
}
