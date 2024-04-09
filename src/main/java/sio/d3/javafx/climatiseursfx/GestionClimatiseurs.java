package sio.d3.javafx.climatiseursfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionClimatiseurs extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GestionClimatiseurs.class.getResource("accueil-tableview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 900);
        stage.setTitle("Gestion des climatiseurs");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}