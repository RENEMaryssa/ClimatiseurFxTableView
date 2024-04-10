module sio.d3.javafx.climatiseursfx {
    requires javafx.controls;
    requires javafx.fxml;

    //paquets requis pour la connexion à la base
    requires mysql.connector.java;
    requires java.sql;


    opens sio.d3.javafx.climatiseursfx to javafx.fxml;
    exports sio.d3.javafx.climatiseursfx;
}