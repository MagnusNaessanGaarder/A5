package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.Testable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayingCardTest implements Testable {
  private final char[] validSuits = {'♠', '♥', '♦', '♣'};
  private final char[] suits = {'S', 'H', 'D', 'C'};
  private PlayingCard playingCard;

  @BeforeEach
  void setUp() {
    //creating a random suit and face
    int suitIndex = (int)(Math.random()*(suits.length - 1)) + 1;
    char suit = suits[suitIndex];

    int face = (int)(Math.random()*13) + 1;

    //creating a playing card with the random suit and face values.
    playingCard = new PlayingCard(suit, face);
  }

  @AfterEach
  void tearDown() {
    playingCard = null;
  }

  @Test
  void testConstructor() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('A', 1);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('H', 0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new PlayingCard('H', 14);
    });

    assertDoesNotThrow(() -> {new PlayingCard('H', 1);} ,
        "Exception should not be thrown, but it was.");
  }

  @Test
  void getAsString() {
    String actual = playingCard.getAsString();
    String numStr = String.join(" ", actual.split("\\D")).strip();
    String suitStr = String.join(" ", actual.split("\\d")).strip();

    //Checking if the face value is between 1 and 13
    assertDoesNotThrow(() -> {Integer.parseInt(numStr);},
        "The face value is not a number");

    //Checking if the suit is one of the four suits
    assertTrue(String.valueOf(validSuits).contains(suitStr));
  }

  @Test
  void getSuit() {
    assertTrue(new String(validSuits).contains(String.valueOf(playingCard.getSuit())));
  }

  @Test
  void getSuitChar() {
    assertTrue(new String(suits).contains(String.valueOf(playingCard.getSuitChar())));
  }

  @Test
  void testGetFaceSymbol() {
    if (playingCard.getFace().matches("\\d+")) {
      assertTrue(Integer.parseInt(playingCard.getFace()) >= 1
          && Integer.parseInt(playingCard.getFace()) <= 10, "The face value is not " +
          "a valid number");
    } else {
      assertTrue(String.valueOf(playingCard.getFace()).matches("[AJQK]"),
          "The face value is not a valid letter");
    }
  }

  @Override
  public void test() {
    this.setUp();
    this.testConstructor();
    this.tearDown();

    this.setUp();
    this.getAsString();
    this.tearDown();

    this.setUp();
    this.getSuit();
    this.tearDown();

    this.setUp();
    this.getSuitChar();
    this.tearDown();

    this.setUp();
    this.testGetFaceSymbol();
    this.tearDown();
  }
}