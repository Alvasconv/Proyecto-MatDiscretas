module Modelo {
    requires javafx.controls;
    requires javafx.fxml;

    opens Modelo to javafx.fxml;
    exports Modelo;
}
