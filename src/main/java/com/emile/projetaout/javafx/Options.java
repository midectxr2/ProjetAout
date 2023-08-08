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

import java.util.Arrays;

public class Options extends VBox {

    private Button returnButton;
    private Button startButton;
    private int res_colonnes = 10;
    private int res_lignes = 10;


    public Options() {

        returnButton = new Button("Return to Main Menu");
        startButton = new Button("Start");


         col = new ChoiceBox(FXCollections.observableArrayList("5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
         row = new ChoiceBox(FXCollections.observableArrayList("5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        final int[] value = new int[]{5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        col.getSelectionModel().selectedIndexProperty().addListener(
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
        row.setValue(DEFAULT_VALUE);


        this.getChildren().addAll(returnButton, colonnes, lignes, startButton);
    }

    public Button getReturnButton() {
        return returnButton;
    }
    public Button getStartButton() {
        return returnButton;
    }

    public ChoiceBox getCol() {
        return col;
    }

    public ChoiceBox getRow() {
        return row;
    }

    public int getRes_colonnes() {
        return res_colonnes;
    }

    public int getRes_lignes() {
        return res_lignes;
    }



}
