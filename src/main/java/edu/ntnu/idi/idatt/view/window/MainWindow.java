package edu.ntnu.idi.idatt.view.window;

import edu.ntnu.idi.idatt.view.window.components.CardDisplay;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow implements Window {
  private final Group root = new Group();
  private final Stage primaryStage;
  private final VBox mainContent = new VBox();
  private final VBox rightSidebar = new VBox();

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
    StackPane mainWindow = new StackPane();
    mainWindow.getChildren().add(root);
    mainWindow.getStyleClass().add("main-window");

    display();

    Scene scene = new Scene(mainWindow, 1200, 600);
    scene.getStylesheets().add("file:src/main/resources/styles/Styles.css");

    primaryStage.setTitle("Flush Game");
    primaryStage.setScene(scene);
    primaryStage.setResizable(true);
    primaryStage.sizeToScene();
  }

  private void display() {
    // Display the GUI
    displayCards();
    displayActionBar();
    displaySidebarMenu();
  }

  private void displayCards() {
    // Display the cards
    CardDisplay cardDisplay = new CardDisplay();
    cardDisplay.init();
    cardDisplay.update();

    mainContent.getChildren().add(cardDisplay.getComponent());
  }

  private void displayActionBar() {
    // Display the action bar
  }

  private void displaySidebarMenu() {
    // Display the sidebar menu
  }
}
