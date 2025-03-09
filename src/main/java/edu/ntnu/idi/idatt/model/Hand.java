package edu.ntnu.idi.idatt.model;

import java.util.Arrays;

public class Hand<T extends PlayingCard> implements Dealable<T> {
  private final DeckOfCards deck = new DeckOfCards();
  private PlayingCard[] hand = new PlayingCard[5];

  public Hand() {
    hand = newHand();
  }

  public Hand(PlayingCard[] hand) {
    if (hand.length != 5) {
      throw new IllegalArgumentException("Hand must contain 5 cards");
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

  @Override
  public boolean equals(Object o) {
      if (this == o || hand == o) {
      return true;
      }
      if (o == null || getClass() != o.getClass()) {
      return false;
      }

      Hand<?> otherHand = (Hand<?>) o;
      for (PlayingCard card : hand) {
        if (!Arrays.asList(otherHand.getHand()).contains(card)) {
          return false;
        }
      }
      return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 0;
    for (PlayingCard card : hand) {
      hashCode += card.hashCode();
    }
    return hashCode;
  }

  @Override
    public String toString() {
        return Arrays.stream(hand).map(PlayingCard::toString).reduce("", (a, b) -> a + b);
    }
}
