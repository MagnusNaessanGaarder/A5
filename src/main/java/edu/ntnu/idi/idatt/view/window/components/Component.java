package edu.ntnu.idi.idatt.view.window.components;

import javafx.scene.Node;

public interface Component {
  void update();
  void init();
  Node getComponent();
}
