package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.Testable;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckOfCardsTest implements Testable {
  private DeckOfCards deck;


  @BeforeEach
  void setUp() {
    deck = new DeckOfCards();
  }

  @AfterEach
  void tearDown() {
    deck = null;
  }

  @Test
  void dealHand() {
    DeckOfCards deck = new DeckOfCards();
    PlayingCard[] hand = deck.dealHand(5);
    assertEquals(5, hand.length);

    ArrayList<Integer> hashCodes = new ArrayList<>();
    for (PlayingCard card : hand) {
      assertNotNull(card);

      if(!hashCodes.contains(card.hashCode())) {
        hashCodes.add(card.hashCode());
      }
    }
    assertEquals(5, hashCodes.size(), "Expected 5 unique cards");
  }

  @Test
  void testNegativeDealHand() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(53));
  }

  @Test
  void generateDeck() {
    PlayingCard[] deck = this.deck.generateDeck();
    assertEquals(52, deck.length);
    ArrayList<Integer> hashCodes = new ArrayList<>();
    for (PlayingCard card : deck) {
      assertNotNull(card);
      if(!hashCodes.contains(card.hashCode())) {
        hashCodes.add(card.hashCode());
      } else {
        System.out.println("Duplicate card: " + card.getAsString());
      }
    }
    assertEquals(52, hashCodes.size(), "Expected 52 unique cards");
  }

  @Test
  void testEquals() {
    DeckOfCards deck1 = new DeckOfCards();
    DeckOfCards deck2 = new DeckOfCards();
    assertEquals(deck1, deck2, "Decks should be equal, but they are not!");
    deck1 = deck1.debugShuffle();
    assertNotEquals(deck1, deck2, "Decks should be different, but they are equal!");
  }

  @Test
  void shuffle() {
    PlayingCard[] originalDeck = deck.getDeck();
    deck = deck.debugShuffle();
    PlayingCard[] shuffledDeck = deck.getDeck();
    assertNotEquals(originalDeck, shuffledDeck);
  }

  @Override
  public void test() {
    this.setUp();
    this.dealHand();
    this.tearDown();

    this.setUp();
    this.testNegativeDealHand();
    this.tearDown();

    this.setUp();
    this.generateDeck();
    this.tearDown();

    this.setUp();
    this.testEquals();
    this.tearDown();

    this.setUp();
    this.shuffle();
    this.tearDown();
  }
}