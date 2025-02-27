package edu.ntnu.idi.idatt.model;

public class DeckOfCards {
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private PlayingCard[] cards = new PlayingCard[52];

  public DeckOfCards() {
    cards = generateDeck();
  }

  private PlayingCard[] generateDeck() {
    PlayingCard[] deck = new PlayingCard[52];
    int i = 0;
    for (char s : suit) {
      for (int f = 1; f <= 13; f++) {
        deck[i++] = new PlayingCard(s, f);
      }
    }
    return deck;
  }

  public PlayingCard[] dealHand(int n) {
    if (n < 0 || n > 52) {
      throw new IllegalArgumentException("Invalid number of cards requested");
    }

    shuffle();
    int r;
    PlayingCard[] hand = new PlayingCard[n];
    for (int i = 0; i < n; i++) {
      do {
        r = (int) (Math.random() * 52);
      } while (hand[i] != null && hand[i].hashCode() == cards[r].hashCode());
      hand[i] = cards[r];
    }
    return hand;
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
