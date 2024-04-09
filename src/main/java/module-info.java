module sio.d3.javafx.climatiseursfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens sio.d3.javafx.climatiseursfx to javafx.fxml;
    exports sio.d3.javafx.climatiseursfx;
}