package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import edu.ntnu.idi.idatt.model.DeckOfCardsTest;
import edu.ntnu.idi.idatt.model.HandTest;
import edu.ntnu.idi.idatt.model.PlayingCardTest;
import edu.ntnu.idi.idatt.view.controller.HandControllerTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAll {
  DeckOfCardsTest deckOfCardsTest;
  HandTest handTest;
  PlayingCardTest playingCardTest;
  HandControllerTest handControllerTest;

  List<Testable> testPipeline = new ArrayList<>();

  @BeforeEach
  void setUp() {
      deckOfCardsTest = new DeckOfCardsTest();
      testPipeline.add(deckOfCardsTest);

      handTest = new HandTest();
      testPipeline.add(handTest);

      playingCardTest = new PlayingCardTest();
      testPipeline.add(playingCardTest);

      handControllerTest = new HandControllerTest();
      testPipeline.add(handControllerTest);
  }

  @AfterEach
    void tearDown() {
        deckOfCardsTest = null;
        handTest = null;
        playingCardTest = null;
        handControllerTest = null;

        testPipeline.clear();
    }

  @Test
  void testAll() {
    testPipeline.forEach(t ->{
      System.out.println("Running test: " + t.getClass().getSimpleName());
      assertDoesNotThrow(t::test, "Did not expect an exception to be thrown for \""
          + t.getClass().getSimpleName() + "\".");
      System.out.println("Finished test: " + t.getClass().getSimpleName() + ": SUCCESS");
    });
  }

}
