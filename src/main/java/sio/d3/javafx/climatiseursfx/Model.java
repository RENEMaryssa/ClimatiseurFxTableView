package sio.d3.javafx.climatiseursfx;

import java.sql.*;

public class Model
{
    private static Connection connexion;
    private static Statement stmt;

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

}
