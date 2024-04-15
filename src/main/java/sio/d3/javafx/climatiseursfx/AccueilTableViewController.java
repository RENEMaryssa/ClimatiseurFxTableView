package sio.d3.javafx.climatiseursfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilTableViewController implements Initializable {

    //ajouter un attribut de type liste observable d'objets de type climatiseur. Cet attribut privé s'appelle : climatiseurs.
    // Ecrire l'accesseur correspondant. L'attribut et la méthode sont statiques (static).
    public static ObservableList climatiseurs  =  FXCollections.observableArrayList();
    public static ObservableList getClimatiseurs() {
        return climatiseurs;
    }

    @FXML
    private TableView tableViewClims;


    @FXML
    public void goToAjoutClimatiseur(MouseEvent event)
    {

        Stage stage = (Stage) tableViewClims.getScene().getWindow();

        try
        {
            FXMLLoader root = new FXMLLoader(GestionClimatiseurs.class.getResource("ajout-climatiseur.fxml"));
            Scene scene = new Scene(root.load(),700, 900);
            stage.setScene(scene);
            stage.setTitle("Nouveau climatiseur");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.err.println(getClass().getName()+" : Il y a une erreur lors de laffichage de la fenêtre d'ajout.");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("Initialisation de l'écran");
        Model.connect_to_database();

        //récupération des données de la base
        climatiseurs.addAll(Model.selectClimatiseurs());

        //association du tableView avec la liste observable tout élément ajouter dans la
        //liste observable sera automatiquement ajouté au tableView tout élément
        //supprimer de la liste observable sera automatiquement supprimé du tableView
        tableViewClims.setItems(climatiseurs);

        ///lors d'un double clic sur un item (ligne) du tableView, on
        //récupère le climatiseur sélectionne et on le supprime du modèle
        tableViewClims.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Le code de la méthode handle
                if (event.getClickCount() > 1) {
                    // Récupération du climatiseur sélectionné
                    Climatiseur selectedItem = (Climatiseur) tableViewClims.getSelectionModel().getSelectedItem();
                    System.out.println("Suppression de l'item … ");
                    // Mise à jour du modèle
                    climatiseurs.remove(selectedItem);
                }
            }
        });
    }
}
