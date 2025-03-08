package edu.ntnu.idi.idatt.view.controller;

import edu.ntnu.idi.idatt.model.Hand;
import edu.ntnu.idi.idatt.model.PlayingCard;
import java.util.Arrays;

public class HandController {
  private final Hand<PlayingCard> hand;

  public HandController(Hand<PlayingCard> hand) {
    if (hand.getHand().length == 0 || hand.getHand().length > 5) {
      throw new IllegalArgumentException("Invalid number of cards in hand");
    }
    this.hand = hand;
  }

  public void changeHand() {
    hand.newHand();
  }

  public PlayingCard[] getHand() {
      return hand.getHand();
  }

  public boolean hasFlush() {
    char firstSuit = hand.getHand()[0].getSuit();
    return Arrays.stream(hand.getHand()).allMatch(c -> c.getSuit() == (firstSuit));
  }
}
