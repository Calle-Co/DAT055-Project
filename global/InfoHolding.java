package global;

/**
 * Simple way of representing a person who is a part of a booking
 */
public class InfoHolding {
    private String owner = null;
    private String age = null;
    private String name = null;

    /**
     * The constructor is used to create a new representation of a person
     * @param name Name of the person
     * @param age Age of th person
     * @param owner Username of the "main" person who peforms the booking
     */
    public InfoHolding(String name, String age, String owner) {
        this.name = name;
        this.age  = age;
        this.owner = owner;
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

    /**
     * a edited toString function that returns both name age and owner of this instance.
     * @return
     */
    public String toPrint(){
        return ("Name : " + this.name+ " \nAge: "+this.age);
    }
    
}
