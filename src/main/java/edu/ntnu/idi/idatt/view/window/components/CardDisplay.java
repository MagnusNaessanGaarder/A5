package edu.ntnu.idi.idatt.view.window.components;

import edu.ntnu.idi.idatt.model.Hand;
import edu.ntnu.idi.idatt.model.PlayingCard;
import edu.ntnu.idi.idatt.view.controller.HandController;
import java.util.Arrays;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class CardDisplay implements Component {
  private final HandController currentHand;
  private final HBox cardView = new HBox();

  public CardDisplay() {
    currentHand = new HandController(new Hand<>());
  }

  public HandController getCurrentHand() {
      return currentHand;
  }

  private StackPane createCard(PlayingCard c) {
    StackPane card = new StackPane();
    card.getStyleClass().add("card");
    card.setMinWidth(130);
    card.setMaxWidth(130);
    card.setMinHeight(200);
    card.setMaxHeight(200);

    StackPane cardOutline = new StackPane();
    cardOutline.getStyleClass().add("card-outline");
    cardOutline.setMinSize(card.getMinWidth() + 10,
        card.getMinHeight() + 10);

    Label cardCornerLabel = new Label(c.getAsString());
    cardCornerLabel.getStyleClass().add("corner-label");
    cardCornerLabel.setAlignment(javafx.geometry.Pos.TOP_LEFT);

    Label cardMainLabel = new Label(String.valueOf(c.getSuit()));
    cardMainLabel.getStyleClass().add("main-label");
    cardMainLabel.setAlignment(javafx.geometry.Pos.CENTER);
    cardMainLabel.setTextAlignment(TextAlignment.CENTER);

    card.getChildren().addAll(cardCornerLabel, cardMainLabel);

    StackPane.setAlignment(cardCornerLabel, javafx.geometry.Pos.TOP_LEFT);
    StackPane.setAlignment(cardMainLabel, javafx.geometry.Pos.CENTER);

    if (c.getColor().equalsIgnoreCase("red")) {
        card.getStyleClass().add("red");
        card.getChildren().forEach(l -> l.getStyleClass().add("red"));
    } else {
        card.getStyleClass().add("black");
        card.getChildren().forEach(l -> l.getStyleClass().add("black"));
    }

    cardOutline.getChildren().add(card);
    StackPane.setAlignment(card, javafx.geometry.Pos.CENTER);

    return cardOutline;
  }

  @Override
  public void update() {
    cardView.getChildren().clear();
    //System.out.println("Before changeHand(): " + currentHand.toString());
    currentHand.changeHand();
    //System.out.println("After changeHand(): " + currentHand.toString());

    /* Code to test the flush functionality
    currentHand = new HandController(new Hand<>(new PlayingCard[] {
        new PlayingCard('S', 10), new PlayingCard('S', 11), new PlayingCard('S', 12),
        new PlayingCard('S', 13), new PlayingCard('S', 1)
    }));
    */

    final Integer[] i = {1};
    for (PlayingCard c : currentHand.getHand()) {
        StackPane card = createCard(c);
        card.setTranslateY(100); // Start off-screen to the left
        card.setOpacity(0); // Start hidden
        cardView.getChildren().add(card);

        // Slide animation
        TranslateTransition slideTransition = new TranslateTransition(Duration.millis(200), card);
        slideTransition.setFromY(100);
        slideTransition.setToY(0);

        // Fade animation
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1), card);
        fadeTransition.setFromValue(0); // Start fully transparent
        fadeTransition.setToValue(1); // End fully opaque

        // Combine slide and fade into a parallel transition
        ParallelTransition transition = new ParallelTransition(slideTransition, fadeTransition);
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setDelay(Duration.millis(i[0]++ * 140));
        //transition.setOnFinished(e -> System.out.println("Animation finished for card: " + c.getAsString()));
        transition.play();
    }
  }

  @Override
  public void init() {
    Label handLabel = new Label("Empty hand!\nPlease select 'Deal hand' to get a new hand.");
    handLabel.getStyleClass().add("hand-label");

    cardView.setSpacing(30);
    cardView.setAlignment(javafx.geometry.Pos.CENTER);
    cardView.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
    cardView.setPrefWidth(-1);
    cardView.getStyleClass().add("card-view");

    cardView.getChildren().add(handLabel);

    StackPane.setAlignment(handLabel, javafx.geometry.Pos.CENTER);
  }

  @Override
  public Node getComponent() {
    return cardView;
  }

  public List<PlayingCard> getHand() {
    return Arrays.asList(currentHand.getHand());
  }
}