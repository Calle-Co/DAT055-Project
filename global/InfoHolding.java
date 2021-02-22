package global;

public class InfoHolding {
    private String owner = null;
    private String age = null;
    private String name = null;

    public InfoHolding(String name, String age, String owner) {
        this.name = name;
        this.age  = age;
        this.owner = owner;
    }

    public String getName(){
        return this.name;
    }

    public String getAge(){
        return this.age;       
    }

    public String toPrint(){
        return ("Name : " + this.name+ " \nAge: "+this.age);
    }
    
}
