package global;

public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver();
    public void notifyObservers(String message);
}
