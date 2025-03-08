package edu.ntnu.idi.idatt.view.window.components;

import edu.ntnu.idi.idatt.model.Hand;
import edu.ntnu.idi.idatt.model.PlayingCard;
import edu.ntnu.idi.idatt.view.controller.HandController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import java.util.Arrays;

public class CardDisplay implements Component {
  private final HandController currentHand;
  private final HBox cardView = new HBox();

  public CardDisplay() {
    currentHand = new HandController(new Hand<>());
  }

  public Hand<PlayingCard> getCurrentHand() {
      return new Hand<>(currentHand.getHand());
  }

  private VBox createCard(PlayingCard c) {
    VBox card = new VBox();
    card.getStyleClass().add("card");
    card.setMinWidth(130);
    card.setMinHeight(200);
    card.setMaxHeight(200);

    Label cardCornerLabel = new Label(c.getAsString());
    cardCornerLabel.getStyleClass().add("corner-label");
    cardCornerLabel.setTextAlignment(TextAlignment.LEFT);

    Label cardMainLabel = new Label(String.valueOf(c.getSuit()));
    cardMainLabel.getStyleClass().add("main-label");
    cardMainLabel.setTextAlignment(TextAlignment.CENTER);
    card.getChildren().addAll(cardCornerLabel, cardMainLabel);

    if (c.getColor().equals("Red")) {
        card.getStyleClass().add("red");
        card.getChildren().forEach(l -> l.getStyleClass().add("red"));
    } else {
        card.getStyleClass().add("black");
        card.getChildren().forEach(l -> l.getStyleClass().add("black"));
    }

    card.setAlignment(javafx.geometry.Pos.CENTER);

    return card;
  }

  @Override
  public void update() {
    cardView.getChildren().clear();
    currentHand.changeHand();
    Arrays.stream(currentHand.getHand())
        .forEach(c -> cardView.getChildren().add(createCard(c)));
  }

  @Override
  public void init() {
    cardView.setSpacing(30);
    cardView.setMaxHeight(400);
    cardView.setMinHeight(400);
    cardView.setMinWidth(900);
    cardView.setMaxWidth(900);
    cardView.setAlignment(javafx.geometry.Pos.CENTER);
    cardView.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
    cardView.getStyleClass().add("card-view");
  }


  @Override
  public Node getComponent() {
    return cardView;
  }
}
