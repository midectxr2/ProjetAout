package com.emile.projetaout.javafx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.net.ssl.HostnameVerifier;
import java.util.concurrent.atomic.AtomicInteger;

public class Display extends VBox {

    private ChoiceBox boats;
    private final int defaultValue  = 0;
    private int nbr = defaultValue;


    public Display(int nb){
        boats = new ChoiceBox(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        final int[] value = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boats.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                 Number old_val, Number new_val) -> {
                    nbr = value[new_val.intValue()];
                }
        );
        boats.setValue(nbr);


        VBox vBox = new VBox();
        vBox.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(new Text("Nombre de bateaux de taille :"+nb), boats);

    }

    public int getNbr(){
        return nbr;
    }
}
