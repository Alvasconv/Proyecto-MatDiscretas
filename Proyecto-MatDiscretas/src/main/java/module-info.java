module Modelo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Modelo to javafx.fxml;
    exports Modelo;
}
