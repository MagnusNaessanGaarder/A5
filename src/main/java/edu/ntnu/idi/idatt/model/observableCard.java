package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;

public abstract class observableCard {
    private List<CardObserver> observerList = new ArrayList<>();

    public abstract void addObserver(CardObserver observer);
    public abstract void removeObserver(CardObserver observer);
    public abstract void notifyObservers();

    public List<CardObserver> getObserverList() {
        return observerList;
    }
}
