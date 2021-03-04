package global;

/**
 * Klassen används för att representera en
 * person som ingår i en bokning
 * @author Simon Länsberg
 * @version 2021-03-03
 */
public class InfoHolding {
    private String owner = null;
    private String age = null;
    private String name = null;
    private String seat = null;
    private String fid = null;

    /**
     * Konstruktorn används för att skapa en person som
     * ingår i en bokning med hjälp av flera parametrar.
     * @param name Fullständigt namn på personen.
     * @param age Ålder på personen.
     * @param owner Användarnamn på den som bokat.
     * @param seat Personens stolsnummer.
     * @param fid Unikt id för flyget.
     */
    public InfoHolding(String name, String age, String owner, String seat, String fid) {
        this.name = name;
        this.age  = age;
        this.owner = owner;
        this.seat = seat;
        this.fid = fid;
    }

    /**
     * @return Fullständigt namn på personen.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * @return Ålder på personen.
     */
    public String getAge(){
        return this.age;       
    }

    /**
     * @return Användarnamn på den som bokat.
     */
    public String getOwner(){
        return this.owner;
    }

    /**
     * @return Personens stolsnummer.
     */
    public String getSeat(){
        return this.seat;
    }

    /**
     * @return Unikt id för flyget.
     */
	public String getFID() {
		return this.fid;
	}
    
}
