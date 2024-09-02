package Modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Mapa.fxml"));
        Parent p = fxmlLoader.load();
        scene = new Scene(p, 1020, 500);
        stage.setScene(scene);
        stage.setTitle("Algoritmo Floyd-Warshall");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}