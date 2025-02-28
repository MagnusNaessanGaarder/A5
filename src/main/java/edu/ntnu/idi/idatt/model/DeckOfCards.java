package edu.ntnu.idi.idatt.model;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DeckOfCards {
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private final PlayingCard[] cards;

  public DeckOfCards() {
    cards = generateDeck();
  }

  private PlayingCard[] generateDeck() {
    return IntStream.range(0, suit.length) // Iterate over suit indices
        .mapToObj(i -> suit[i]) // Convert indices to suit chars
        .flatMap(s -> IntStream.rangeClosed(1, 13) // Iterate over ranks 1-13
            .mapToObj(rank -> new PlayingCard(s, rank))) // Create PlayingCard
        .toArray(PlayingCard[]::new);
  }

  public PlayingCard[] dealHand(int n) {
    if (n < 0 || n > 52) {
      throw new IllegalArgumentException("Invalid number of cards requested");
    }

    shuffle();
    return IntStream.range(0, suit.length) // Iterate over suit indices
        .mapToObj(i -> suit[i]) // Convert indices to suit chars
        .flatMap(s -> IntStream.rangeClosed(1, 13) // Iterate over ranks 1-13
            .mapToObj(rank -> new PlayingCard(s, rank))) // Create PlayingCard
        .toArray(PlayingCard[]::new); // Collect into an array
  }

  public void shuffle() {
    for (int i = 0; i < cards.length; i++) {
      int r = i + (int)(Math.random() % (cards.length - i));
      PlayingCard temp = cards[r];
      cards[r] = cards[i];
      cards[i] = temp;
    }
  }
}
