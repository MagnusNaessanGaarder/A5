package edu.ntnu.idi.idatt.view.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
  private final Group root = new Group();

  public void init(Stage primaryStage) {
    primaryStage = new Stage();
    Scene scene = new Scene(root, 640, 480);
    primaryStage.setTitle("Flush Game");
    primaryStage.setScene(scene);
  }

  @Override
  public void start(Stage primaryStage) {
    init(primaryStage);
    primaryStage.show();
  }
}
