module edu.ntnu.idi.idatt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
  requires java.smartcardio;
  opens edu.ntnu.idi.idatt.view.controller to javafx.graphics;
  exports edu.ntnu.idi.idatt.app;
  opens edu.ntnu.idi.idatt.view to javafx.graphics;
  opens edu.ntnu.idi.idatt.view.window to javafx.graphics;
  opens edu.ntnu.idi.idatt.view.window.components to javafx.graphics;
    opens edu.ntnu.idi.idatt.model to javafx.graphics;
}