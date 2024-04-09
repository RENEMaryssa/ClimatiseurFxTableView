package sio.d3.javafx.climatiseursfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjoutClimatiseurController{
    @FXML
    private Label marque;

    @FXML
    private TextArea valId;

    @FXML
    private TextArea valMarque;

    @FXML
    private TextArea valPuissance;

    @FXML
    private TextArea valModele;
    @FXML
    private TextArea valSurfaceMin;
    @FXML
    private TextArea valSurfaceMax;

    @FXML
    private Button primaryButton;

    public void enregistrer(MouseEvent event)
    {
        // Récupérer les valeurs des champs de texte
        int id = Integer.parseInt(valId.getText());
        String modele = valModele.getText();
        int puissance = Integer.parseInt(valPuissance.getText());
        String marque = valMarque.getText();

        System.out.println("Enregistrement du climatiseur .... ");

        //création d'un nouvel objet
        Climatiseur c = new Climatiseur(marque,puissance,modele,id);

        //ajout du climatiseur dans la liste du climatiseur du modèle
        AccueilTableViewController.getClimatiseurs().add(c);


        Stage stage = (Stage) valModele.getScene().getWindow();

        try
        {
            FXMLLoader root = new FXMLLoader(GestionClimatiseurs.class.getResource("accueil-tableview.fxml"));
            Scene scene = new Scene(root.load(),700, 900);
            stage.setScene(scene);
            stage.setTitle("Gestion des climatiseur");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.err.println(getClass().getName()+" : Il y a une erreur lors de laffichage de la liste des climatiseurs après ajout d'un nouveu climatiseur.");
        }

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundl)
    {
        marque.setText("Saisir la marque : ");
    }

    //ajouter une collection (liste) de type List . La collection permet de stocker des éléments de type Climatiseur
    private List<Climatiseur> climatiseurs = new ArrayList<>();

    //méthode AjoutClimatiseur qui permet d'ajouter un nouveau climatiseur
    public void AjoutClimatiseur(String marque, int puissance, String modele, int id) {
        climatiseurs.add(new Climatiseur(marque, puissance, modele, id));
    }

    //méthode climatiseursToString qui retourne la liste des climatiseurs
    public String climatiseursToString() {
        String climString = "";
        for (int i = 0; i < climatiseurs.size(); i++) {
            climString += "Climatisseur"+(i+1)+":"+climatiseurs.get(i).getPuissance()+"BTU, de"+climatiseurs.get(i).getSmin()+"à"+climatiseurs.get(i).getSmax()+"m2";
        }
        return climString;
    }

    //méthode ClimParMarque qui retourne une liste des climatiseurs dont la marque est la même
    public List<Climatiseur> ClimParMarque(String marque) {
        List<Climatiseur> climMarque = new ArrayList<Climatiseur>();
        for (Climatiseur clim : climatiseurs) {
            if (clim.getMarque().equals(marque)) {
                climMarque.add(clim);
            }
        }
        return climMarque;
    }

    //méthode ClimParPuissance qui retourne une liste des climatiseurs dont la puissance est la même
    public List<Climatiseur> ClimParPuissance(int puissance) {
        List<Climatiseur> climPuissance = new ArrayList<Climatiseur>();
        for (Climatiseur clim : climatiseurs) {
            if (clim.getPuissance() == puissance) {
                climPuissance.add(clim);
            }
        }
        return climPuissance;
    }

}
