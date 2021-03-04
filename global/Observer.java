package global;
/**
 * Interfacet används av klasser som ska kunna
 * observera andra klasser.
 * @author William Husar, Simon Länsberg
 * @version 2021-02-18
 */
public interface Observer {
    public void update(String message);
}
