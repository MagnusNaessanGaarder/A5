package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.view.window.MainWindow;
import javafx.application.Application;

import javafx.stage.Stage;

public class GUI extends Application {
  @Override
  public void start(Stage stage) {
    MainWindow mainWindow = new MainWindow(stage);
    mainWindow.init();
    mainWindow.show();
  }
}
