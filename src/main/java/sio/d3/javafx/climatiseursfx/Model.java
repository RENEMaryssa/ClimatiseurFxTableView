package sio.d3.javafx.climatiseursfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model
{
    private static PreparedStatement pstmt;
    private static Connection connexion;
    private static Statement stmt;
    private static List<Climatiseur> climatiseurs;


    public static void  connect_to_database(){
        try{
            //indication de l'hôte et de la base de données + paramétrages
            String url = "jdbc:mysql://localhost:3337/"
                    +"GestionClims?&useJJBCCompliantTimezoneShift=true"
                    +"&useLegacyDatetimeCode=false&serverTimezone=UTC";

            //nom d'utilisateur
            String user="climauser";
            //mot de passe
            String password = "climapass";

            connexion =  (Connection) DriverManager.getConnection(url,user,password);

            if(connexion!=null){
                System.out.println("La connexion est effective.");
            }

        }catch (SQLException ex){

            System.err.println("Erreur de connexion à la base.");
            ex.printStackTrace();
        }
    }

    public static List<Climatiseur> selectClimatiseurs() {
        // Initialiser la liste des climatiseurs si elle est nulle, ou l'effacer si elle ne l'est pas.
        if (climatiseurs == null) {
            climatiseurs = new ArrayList<>();
        } else {
            climatiseurs.clear();
        }

        try {
            System.out.println("Chargement des climatiseurs...");
            // création d'un objet "Statement" qui permettra d'exécuter la requête
            stmt = connexion.createStatement();

            // définition de la requête
            String sql = "SELECT * from climatiseurs";
            System.out.println("requête :" + sql);

            // Exécution de la requête
            pstmt = connexion.prepareStatement(sql);
            pstmt.execute();

            // Récupération des résultats
            ResultSet rs = pstmt.getResultSet();

            // parcours des enregistrements résultats,
            // création de nouveaux objets "climatiseurs" et
            // ajout de cet objet dans la liste
            while (rs.next()) {
                int id = rs.getInt("id");
                String mar = rs.getString("marque");
                String mod = rs.getString("modele");
                int pui = rs.getInt("puissance");
                int smi = rs.getInt("surfaceMin");
                int sma = rs.getInt("surfaceMax");

                // Créer un nouveau climatiseur avec les données de l'ensemble des résultats.
                Climatiseur c = new Climatiseur(mar, pui, mod);
                climatiseurs.add(c);
            }

            rs.close();
        } catch (SQLException se) {
            // exécuté si la requête ne s'est pas bien exécutée
            se.printStackTrace();
            System.err.println("La requête s'est mal déroulée.");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // Retourne la liste des climatiseurs
        return climatiseurs;
    }

    public static void insertClimatiseur(Climatiseur climatiseur) {
        String sql = "INSERT INTO climatiseurs (marque, modele, puissance, surfaceMin, surfaceMax) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, climatiseur.getMarque());
            pstmt.setString(2, climatiseur.getModele());
            pstmt.setInt(3, climatiseur.getPuissance());
            pstmt.setInt(4, climatiseur.getSmin());
            pstmt.setInt(5, climatiseur.getSmax());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("La requête s'est mal déroulée.");
        }
    }

}

