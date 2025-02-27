package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PlayingCardTest {
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
    assertEquals(2, playingCard.getAsString().length());
  }

  @Test
  void getSuit() {
    assertTrue(new String(suits).contains(String.valueOf(playingCard.getSuit())));
  }

  @Test
  void getFace() {
    assertTrue(playingCard.getFace() >= 1 && playingCard.getFace() <= 13);
  }
}