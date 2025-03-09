package edu.ntnu.idi.idatt.view.window;

import edu.ntnu.idi.idatt.model.PlayingCard;
import edu.ntnu.idi.idatt.view.window.components.CardDisplay;
import java.util.List;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow implements Window {
  private final Group root = new Group();
  private final Stage primaryStage;
  private final VBox mainContent = new VBox();
  private final VBox rightSidebar = new VBox();
  private final Pane confettiOverlay = new Pane();

  private final Label sumOfFacesLabel = new Label("Sum of the faces:");
  private final Label CardsOfHeartsLabel = new Label("Cards of Hearts:");
  private final Label hasQueenOfSpadesLabel = new Label("Queen of Spades:");
  private final Label hasFlushLabel = new Label("Has Flush:");

  private final CardDisplay cardDisplay = new CardDisplay();

  public MainWindow(Stage stage) {
    primaryStage = stage;
  }

  @Override
  public void show() {
    primaryStage.show();
  }

  @Override
  public void close() {
    primaryStage.close();
  }

  @Override
  public void init() {
    primaryStage.setTitle("Flush Game");
    primaryStage.setResizable(true);
    primaryStage.setWidth(1200);
    primaryStage.setHeight(700);

    confettiOverlay.prefWidthProperty().bind(primaryStage.widthProperty());
    confettiOverlay.prefHeightProperty().bind(primaryStage.heightProperty());
    confettiOverlay.setStyle("-fx-background-color: transparent;");

    StackPane mainWindow = new StackPane();
    mainWindow.getChildren().addAll(root, confettiOverlay);
    mainWindow.getStyleClass().add("main-window");
    mainWindow.setAlignment(javafx.geometry.Pos.CENTER);

    mainContent.getStyleClass().add("main-content");
    mainContent.setAlignment(javafx.geometry.Pos.CENTER);
    mainContent.setSpacing(10);
    mainContent.setMaxWidth(primaryStage.getWidth() * 3 / 4);
    mainContent.setMaxHeight(primaryStage.getHeight());

    rightSidebar.getStyleClass().add("right-sidebar");
    rightSidebar.setAlignment(javafx.geometry.Pos.CENTER);
    rightSidebar.setSpacing(10);
    rightSidebar.setMaxWidth(primaryStage.getWidth() * 1 / 4);
    rightSidebar.setMaxHeight(primaryStage.getHeight());

    mainWindow.getChildren().addAll(mainContent, rightSidebar);
    StackPane.setAlignment(mainContent, javafx.geometry.Pos.CENTER_LEFT);
    StackPane.setAlignment(rightSidebar, javafx.geometry.Pos.CENTER_RIGHT);
    StackPane.setAlignment(confettiOverlay, javafx.geometry.Pos.CENTER);

    Scene scene = new Scene(mainWindow, primaryStage.getWidth(), primaryStage.getHeight());
    scene.getStylesheets().add("file:src/main/resources/styles/Styles.css");
    scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Atma:wght@300;400;500;600;700&family=Chewy&family=Rozha+One&display=swap");
    //System.out.println("Available fonts: \n" + javafx.scene.text.Font.getFamilies());
    primaryStage.setScene(scene);

    display();
  }

  private void display() {
    // Display the GUI
    displayTitle();
    displayCards();
    displayActionBar();
    displaySidebar();
  }

  private void displayTitle() {
    // Display the title
    Label title = new Label("Flush");
    title.getStyleClass().add("title");
    title.getStyleClass().add("rozha-one-regular");
    mainContent.getChildren().add(title);
  }

  private void displayCards() {
    // Display the cards
    cardDisplay.init();
    mainContent.getChildren().add(cardDisplay.getComponent());
  }

  private void displayActionBar() {
    // Display the action bar
    HBox actionBar = new HBox();
    actionBar.setAlignment(javafx.geometry.Pos.CENTER);
    actionBar.setPrefWidth(-1);

    actionBar.getStyleClass().add("action-bar");

    Insets btnPadding = new Insets(15, 30, 15, 30);
    Button dealHandButton = new Button("Deal hand");
    Button checkHandButton = new Button("Check hand");

    dealHandButton.getStyleClass().add("btn");
    dealHandButton.setPadding(btnPadding);

    checkHandButton.getStyleClass().add("btn");
    checkHandButton.getStyleClass().add("unavailable");
    checkHandButton.setPadding(btnPadding);

    actionBar.getChildren().addAll(dealHandButton, checkHandButton);
    actionBar.setAlignment(javafx.geometry.Pos.CENTER);

    dealHandButton.setOnAction(e -> {
      mainContent.getChildren().removeIf(node ->
          !(node instanceof Label && ((Label) node).getText().equals("Flush")) &&
              !node.equals(cardDisplay.getComponent()) &&
              !node.equals(actionBar)
      );
      cardDisplay.update();
      checkHandButton.getStyleClass().remove("unavailable");
      List<PlayingCard> currentHand = cardDisplay.getHand();

      // queen of spades
      if (currentHand.contains(new PlayingCard('S',12))) {
        hasQueenOfSpadesLabel.setText("Queen of Spades: Yes");
      } else {
        hasQueenOfSpadesLabel.setText("Queen of Spades: No");
      }

      //sum of faces
      int sumOfFaces = currentHand.stream().mapToInt(PlayingCard::getFaceValue).sum();
      sumOfFacesLabel.setText("Sum of the faces: " + sumOfFaces);

      //cards of hearts
      int cardsOfHearts = (int) currentHand.stream().filter(card -> card.getSuitChar() == 'H').count();
      CardsOfHeartsLabel.setText("Cards of Hearts: " + cardsOfHearts);
    });

    checkHandButton.setOnAction(e -> {
      if (!checkHandButton.getStyleClass().contains("unavailable")) {
        boolean hasFlush = cardDisplay.getCurrentHand().hasFlush();
        if (hasFlush) {
          createConfettiAnimation();
          hasFlushLabel.setText("Has Flush: Yes");
        } else {
          hasFlushLabel.setText("Has Flush: No");
        }
      }
    });

    mainContent.getChildren().add(actionBar);
  }

  private void displaySidebar() {
    // Display the sidebar menu
    rightSidebar.getStyleClass().add("right-sidebar");
    rightSidebar.setSpacing(50);

    Label sidebarTitle = new Label("Game info");
    sidebarTitle.getStyleClass().add("sidebar-title");

    sumOfFacesLabel.getStyleClass().add("sidebar-label");
    CardsOfHeartsLabel.getStyleClass().add("sidebar-label");
    hasQueenOfSpadesLabel.getStyleClass().add("sidebar-label");
    hasFlushLabel.getStyleClass().add("sidebar-label");

    rightSidebar.getChildren().addAll(sidebarTitle, sumOfFacesLabel, CardsOfHeartsLabel, hasQueenOfSpadesLabel, hasFlushLabel);

    rightSidebar.setAlignment(Pos.CENTER_LEFT);

  }

  private void createConfettiAnimation() {
    confettiOverlay.getChildren().clear();
    confettiOverlay.setOpacity(1.0);
    confettiOverlay.setVisible(true);
    confettiOverlay.setMouseTransparent(false); // Block clicks during animation
    confettiOverlay.toFront(); // Keep confetti visible

    Random random = new Random();
    Platform.runLater(() -> {
      System.out.println("Creating confetti animation...");
      double stageWidth = primaryStage.getWidth();
      double stageHeight = primaryStage.getHeight();
      System.out.println("Confetti overlay size: " + stageWidth + "x" + stageHeight);

      if (stageWidth <= 0 || stageHeight <= 0) {
        System.out.println("Invalid stage dimensions, skipping animation");
        return;
      }

      for (int i = 0; i < 50; i++) {
        // Randomize size for variety
        double size = 10 + random.nextDouble() * 50; // Between 5px and 20px
        Rectangle confetti = new Rectangle(size, size);
        confetti.setFill(getRandomColor());
        confetti.setTranslateX(random.nextDouble() * stageWidth); // Random X position
        confetti.setTranslateY(stageHeight); // Start from the bottom

        confettiOverlay.getChildren().add(confetti);
        System.out.println("Confetti piece " + i + " added at (" + confetti.getTranslateX() + ", " + confetti.getTranslateY() + ")");

        // Random upward distance (some go higher than others)
        double upwardDistance = stageHeight * (0.6 + random.nextDouble() * 0.4); // 60% to 100% of stage height

        // Random horizontal movement (left or right)
        double horizontalMovement = (random.nextDouble() - 0.5) * stageWidth * 0.4; // Moves up to 40% of width left or right

        // Random duration (some fall faster than others)
        double durationMillis = 400 + random.nextDouble() * 400;

        // Upward and sideways movement
        TranslateTransition translate = new TranslateTransition(Duration.millis(durationMillis), confetti);
        translate.setByX(horizontalMovement);
        translate.setByY(-upwardDistance);
        translate.setInterpolator(Interpolator.EASE_OUT);

        // Random rotation
        RotateTransition rotate = new RotateTransition(Duration.millis(durationMillis), confetti);
        rotate.setByAngle(180 + random.nextDouble() * 360); // Between 180° and 540°
        rotate.setInterpolator(Interpolator.LINEAR);

        // Fade out
        FadeTransition fade = new FadeTransition(Duration.millis(durationMillis), confetti);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        // Combine all animations
        ParallelTransition transition = new ParallelTransition(translate, rotate, fade);
        int finalI = i;
        transition.setOnFinished(e -> {
          confettiOverlay.getChildren().remove(confetti);
          System.out.println("Confetti piece " + finalI + " animation finished and removed");

          // Once all confetti is removed, allow clicks again
          if (confettiOverlay.getChildren().isEmpty()) {
            System.out.println("All confetti removed, making overlay click-through.");
            confettiOverlay.setMouseTransparent(true);
          }
        });
        transition.play();
      }
    });
  }

  private Color getRandomColor() {
    Random random = new Random();
    return Color.rgb(
        random.nextInt(256),
        random.nextInt(256),
        random.nextInt(256),
        1
    );
  }
}
