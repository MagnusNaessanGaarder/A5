package edu.ntnu.idi.idatt.model;

import java.util.Arrays;

public class Hand<T extends PlayingCard> implements Dealable<T> {
  private final DeckOfCards deck = new DeckOfCards();
  private PlayingCard[] hand = new PlayingCard[5];

  public Hand() {
    hand = newHand();
  }
  public Hand(PlayingCard[] hand) {
    if (hand.length == 0 || hand.length > 5) {
      throw new IllegalArgumentException("Invalid number of cards in hand");
    }
    this.hand = hand;
  }

  @Override
  public PlayingCard[] newHand() {
    return deck.dealHand(hand.length);
  }
  @Override
  public PlayingCard[] getHand() {
    return hand;
  }

  private boolean hasFlush() {
    char firstSuit = hand[0].getSuit();
    return Arrays.stream(hand).allMatch(c -> c.getSuit() == (firstSuit));
  }
}
