package edu.ntnu.idi.idatt.model;

public interface Dealable <T> {
    PlayingCard[] newHand();
    PlayingCard[] getHand();
}
