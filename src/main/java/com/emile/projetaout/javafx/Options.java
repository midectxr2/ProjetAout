package com.emile.projetaout.javafx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Options extends VBox {

    private Button returnButton;
    private Button startButton;
    private int res_colonnes = 10;
    private int res_lignes = 10;


    public Options() {

        returnButton = new Button("Return to Main Menu");
        startButton = new Button("Start");
        ChoiceBox colonnes = new ChoiceBox(FXCollections.observableArrayList("5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        ChoiceBox lignes = new ChoiceBox(FXCollections.observableArrayList("5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        final int[] value = new int[]{5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        colonnes.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) -> {
                        res_colonnes = value[new_val.intValue()];
                }
        );
        lignes.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                 Number old_val, Number new_val) -> {
                    res_lignes = value[new_val.intValue()];
                }
        );


        this.getChildren().addAll(returnButton, colonnes, lignes, startButton);
    }

    public Button getReturnButton() {
        return returnButton;
    }
    public Button getStartButton() {
        return returnButton;
    }

    public int getColonnes(){
        return res_colonnes;
    }

    public int getRes_lignes(){
        return res_lignes;
    }

}
