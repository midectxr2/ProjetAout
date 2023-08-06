module com.emile.projetaout.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.emile.projetaout.javafx to javafx.fxml;
    exports com.emile.projetaout.javafx;
}