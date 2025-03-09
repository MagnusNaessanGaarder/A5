package edu.ntnu.idi.idatt.view.controller;

import edu.ntnu.idi.idatt.model.Hand;
import edu.ntnu.idi.idatt.model.PlayingCard;
import java.util.Arrays;

public class HandController {
  private Hand<PlayingCard> hand;

  public HandController(Hand<PlayingCard> hand) {
    if (hand.getHand().length == 0 || hand.getHand().length > 5) {
      throw new IllegalArgumentException("Invalid number of cards in hand");
    }
    this.hand = hand;
  }

  public void changeHand() {
    this.hand = new Hand<>(hand.newHand());
  }

  public PlayingCard[] getHand() {
      return hand.getHand();
  }

  public boolean hasFlush() {
    char firstSuit = hand.getHand()[0].getSuit();
    return Arrays.stream(hand.getHand()).allMatch(c -> c.getSuit() == (firstSuit));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HandController otherHandController = (HandController) o;
    return this.hand.equals(otherHandController.hand);
  }

  @Override
  public String toString() {
    return Arrays.stream(hand.getHand()).map(PlayingCard::toString).reduce("", (a, b) -> a + b);
  }
}
