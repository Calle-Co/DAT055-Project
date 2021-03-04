package global;

/**
 * Interfacet används av klasser som ska kunna
 * bli observerade av andra klasser.
 * @author William Husar, Simon Länsberg
 * @version 2021-02-18
 */
public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver();
    public void notifyObservers(String message);
}