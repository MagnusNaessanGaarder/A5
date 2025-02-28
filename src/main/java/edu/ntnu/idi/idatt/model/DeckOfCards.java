package edu.ntnu.idi.idatt.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeckOfCards {
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private PlayingCard[] cards;

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

    return Arrays.stream(cards, 0, n)
        .toArray(PlayingCard[]::new);
  }

  private void shuffle() {
    List<PlayingCard> shuffledList = Arrays.stream(cards)
        .collect(Collectors.toList()); // Convert array to List

    Collections.shuffle(shuffledList); // Shuffle using built-in method

    cards = shuffledList.toArray(new PlayingCard[0]); // Convert back to array
  }
}
