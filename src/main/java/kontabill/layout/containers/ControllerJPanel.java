package main.java.kontabill.layout.containers;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.elements.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 */
public class ControllerJPanel extends JPanel implements Observable {

    /**
     * Represent kontabill instance app.
     */
    private Kontabill kontabill;

    public ControllerJPanel(Kontabill kontabill) {
        super();
        this.kontabill = kontabill;
        initControllerJPanel();
    }

    private void initControllerJPanel()
    {
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
    }

    public void removeComponents()
    {
        // notify observers for this action
        notifyObservers(ObservableEvent.ALL_COMPONENTS_REMOVED);

        removeAll();

        // remove observers for this action
        removeObservers(ObservableEvent.ALL_COMPONENTS_REMOVED);
    }

    public void repaintComponents()
    {
        repaint();
        validate();
    }


    // Observable behaviour
    private Map<ObservableEvent, Map<Observer, Observer>> observerMap = new HashMap<>();

    @Override
    public void notifyObservers(ObservableEvent observableEvent) {
        // get current observers
        Map<Observer, Observer> observersMap = observerMap.get(observableEvent);

        if (observersMap instanceof Map &&
                observersMap.size() > 0) {
            Set<Observer> observers = observersMap.keySet();

            for (Observer observer:observers) {
                observer.update(observableEvent);
            }
        }
    }

    @Override
    public void registerObserver(ObservableEvent observableEvent, Observer observer) {
        // get current observer for observableEvent
        Map<Observer, Observer> observers = observerMap.get(observableEvent);

        if (observers instanceof Map) {
            observers.put(observer, observer);
        } else {
            Map<Observer, Observer> newObservers = new HashMap<Observer, Observer>();
            newObservers.put(observer, observer);
            observerMap.put(observableEvent, newObservers);
        }
    }

    @Override
    public void removeObservers(ObservableEvent observableEvent) {
        observerMap.put(observableEvent, new HashMap<Observer, Observer>());
    }

    @Override
    public void removeObserver(ObservableEvent observableEvent, Observer observer) {
        Map<Observer, Observer> observers = observerMap.get(observableEvent);
        if (observers instanceof Map) {
            observers.remove(observer);
        }
    }
}
