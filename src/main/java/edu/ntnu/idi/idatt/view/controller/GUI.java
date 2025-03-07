package edu.ntnu.idi.idatt.view.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
  private final Group root = new Group();
  private Stage primaryStage;

  public void init(Stage stage) {
    this.primaryStage = stage;
    Scene scene = new Scene(root, 640, 480);
    this.primaryStage.setTitle("Flush Game");
    this.primaryStage.setScene(scene);
  }

  @Override
  public void start(Stage primaryStage) {
    init(primaryStage);
    this.primaryStage.show();
  }

  private void display() {
    // Display the GUI
    displayCards();
    displayActionBar();
    displaySidebarMenu();
  }

  private void displayCards() {
    // Display the cards
  }

  private void displayActionBar() {
    // Display the action bar
  }

  private void displaySidebarMenu() {
    // Display the sidebar menu
  }
}
