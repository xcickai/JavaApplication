package dsw.gerumap.app.core.observer;

public interface Publisher {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(Object obj); // Object that has changed is being sent
}
