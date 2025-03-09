package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.Testable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandTest implements Testable {
  private Hand<PlayingCard> hand;
  private PlayingCard[] tempHand;

  @BeforeEach
  void setUp() {
    hand = new Hand<>();
    tempHand = new PlayingCard[5];
    for (int i = 0; i < tempHand.length; i++) {
      tempHand[i] = hand.getHand()[i];
    }
  }

  @AfterEach
  void tearDown() {
    hand = null;
  }

  @Test
  void newHand() {
    // Creating a deep copy to compare with the hand after newHand()
    hand.newHand();
    assertNotEquals(tempHand, hand.getHand(), "Hand should not be the same after newHand()");
  }

  @Test
  void getHand() {
    assertNotNull(hand.getHand(), "Hand should not be null");
    assertEquals(5, hand.getHand().length, "Hand length should be 5");

    // Checking that all cards in the hand are unique
    for (int i = 0; i < hand.getHand().length; i++) {
      for (int j = i + 1; j < hand.getHand().length; j++) {
        assertNotEquals(hand.getHand()[i], hand.getHand()[j], "Cards in hand should be unique");
      }
    }
  }

  @Test
  void testEquals() {
    assertEquals(hand, hand, "Hand should be equal to itself");
    assertEquals(new Hand<>(tempHand), hand, "Hands should be equal if they contain the same cards");
  }

  @Test
  void negativeTestEquals() {
    // Changing one card in the hand
    tempHand[0] = new PlayingCard('S', 1);
    assertNotEquals(new Hand<>(tempHand), hand, "Hands should not be equal if they contain different cards");
    assertNotEquals(null, hand, "Hand should not be equal to null");
    assertNotEquals(new Object(), hand, "Hand should not be equal to an object of another class");
  }

  @Test
  void testHashCode() {
    // Deep copy of the hand
    assertEquals(new Hand<>(tempHand).hashCode(), hand.hashCode(), "Hash codes should be equal if the hands are equal");
  }

  @Test
  void testNegativeHashCode() {
    // Changing one card in the hand
    tempHand[0] = new PlayingCard('S', 1);
    assertNotEquals(new Hand<>(tempHand).hashCode(), hand.hashCode(), "Hash codes should not be equal if the hands are different");
  }

  @Override
  public void test() {
    this.setUp();
    this.newHand();
    this.tearDown();

    this.setUp();
    this.getHand();
    this.tearDown();

    this.setUp();
    this.testEquals();
    this.tearDown();

    this.setUp();
    this.negativeTestEquals();
    this.tearDown();

    this.setUp();
    this.testHashCode();
    this.tearDown();

    this.setUp();
    this.testNegativeHashCode();
    this.tearDown();
  }
}