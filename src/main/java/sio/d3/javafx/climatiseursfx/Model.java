package sio.d3.javafx.climatiseursfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model
{
    private static Connection connexion;
    private static Statement stmt;
    // Déclarez l'attribut climatiseurs
    private static List<Climatiseur> climatiseurs;

    public static List<Climatiseur> connect_to_database(){
        if (climatiseurs==null)
        {
            climatiseurs = new ArrayList<>();
        }
        else{
            climatiseurs.clear();
        }

        try
        {

            System.out.println("Chargement des climatisuers...");
            //création d'un objet "Statement" qui permettra d'exécuter la requête
            stmt = connexion.createStatement();

            //définition de la requête
            String sql = "SELECT * from climatiseurs";
            System.out.println("requête :"+sql);
            //exécution de la requête
            ResultSet rs = stmt.executeQuery(sql);

            //parcours des enregistrements résultats,
            //création de nouveaux objets "climatiseurs" et
            //ajout de cet objet dans la liste
            while(rs.next())
            {
                int id = rs.getInt("id");
                String mar = rs.getString("marque");
                String mod = rs.getString("modele");
                int pui = rs.getInt("puissance");
                int smi = rs.getInt("surfaceMin");
                int sma = rs.getInt("surfaceMax");

                Climatiseur c = new Climatiseur(mar,pui,mod,smi,sma,id);
                climatiseurs.add(c);
            }

            rs.close();

        }catch(SQLException se)
        {
            //exécuté si la requête ne s'est pas bien exécutée
            se.printStackTrace();
            System.err.println("La requête c'est mal déroulée.");
        }
        finally
        {
            if(stmt!=null)
            {
                try
                {
                    stmt.close();
                } catch (SQLException ex)
                {
                }
            }
        }

        return climatiseurs;
    }

}
