package edu.ntnu.idi.idatt.view.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.Testable;
import edu.ntnu.idi.idatt.model.Hand;
import edu.ntnu.idi.idatt.model.PlayingCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandControllerTest implements Testable {
  private HandController handController;
  private PlayingCard[] tempHand;

  @BeforeEach
  void setUp() {
    handController = new HandController(new Hand<>());

    tempHand = new PlayingCard[5];
    for (int i = 0; i < tempHand.length; i++) {
      tempHand[i] = handController.getHand()[i];
    }
  }

  @AfterEach
  void tearDown() {
    handController = null;
    tempHand = null;
  }

  @Test
  void changeHand() {
    handController.changeHand();
    assertNotEquals(tempHand, handController.getHand(), "Hand should not be the same after changeHand()");
    handController.changeHand();
    assertNotEquals(tempHand, handController.getHand(), "Hand should not be the same after changeHand()");
  }

  @Test
  void getHand() {
    assertEquals(tempHand.length, handController.getHand().length, "Hand length should be 5");
    assertEquals(new HandController(new Hand<>(tempHand)),
        handController, "Hand hand should be the same, but not the same object");
  }

  @Test
  void hasFlushPositive() {
    Hand<PlayingCard> flushHand = new Hand<>(new PlayingCard[] {
        new PlayingCard('S', 1),
        new PlayingCard('S', 2),
        new PlayingCard('S', 3),
        new PlayingCard('S', 4),
        new PlayingCard('S', 5)
    });

    HandController flushHandController = new HandController(flushHand);
    assertTrue(flushHandController.hasFlush(), "Hand should have a flush");
  }

  @Test
  void hasFlushNegative() {
    Hand<PlayingCard> nonFlushHand = new Hand<>(new PlayingCard[] {
        new PlayingCard('S', 1),
        new PlayingCard('H', 2),
        new PlayingCard('S', 3),
        new PlayingCard('S', 4),
        new PlayingCard('S', 5)
    });

    HandController nonFlushHandController = new HandController(nonFlushHand);
    assertFalse(nonFlushHandController.hasFlush(), "Hand should not have a flush");
  }

  @Override
  public void test() {
    this.setUp();
    this.changeHand();
    this.tearDown();

    this.setUp();
    this.getHand();
    this.tearDown();

    this.setUp();
    this.hasFlushPositive();
    this.tearDown();

    this.setUp();
    this.hasFlushNegative();
    this.tearDown();
  }
}