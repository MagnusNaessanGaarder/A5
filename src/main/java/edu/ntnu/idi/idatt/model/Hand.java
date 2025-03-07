package edu.ntnu.idi.idatt.model;

import java.util.Arrays;

public class Hand {
  private PlayingCard[] hand = new PlayingCard[5];
  public Hand() {
    hand = newHand();
  }

  public PlayingCard[] newHand() {
    DeckOfCards deck = new DeckOfCards();
    return deck.dealHand(hand.length);
  }

  public PlayingCard[] getHand() {
    return hand;
  }

  public boolean hasFlush() {
    char firstSuit = hand[0].getSuit();
    return Arrays.stream(hand).allMatch(c -> c.getSuit() == (firstSuit));
  }
}
