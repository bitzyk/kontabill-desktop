package main.java.kontabill.layout.containers;

import main.java.kontabill.layout.elements.Observer;

/**
 *
 */
public interface Observable {

    public void notifyObservers(ObservableEvent observableEvent);

    public void registerObserver(ObservableEvent observableEvent, Observer observer);

    public void removeObservers(ObservableEvent observableEvent);

    public void removeObserver(ObservableEvent observableEvent, Observer observer);
}
