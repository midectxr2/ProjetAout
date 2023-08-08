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
    private ChoiceBox col;
    private ChoiceBox row;
    private final int DEFAULT_VALUE = 10;
    private int res_colonnes = DEFAULT_VALUE;
    private int res_lignes = DEFAULT_VALUE;


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
                        System.out.println("Je set la col a " + res_colonnes);
                }
        );

        col.setValue(DEFAULT_VALUE);

        row.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                 Number old_val, Number new_val) -> {
                    res_lignes = value[new_val.intValue()];
                    System.out.println("Je set la ligne a " + res_lignes);

                }
        );
        row.setValue(DEFAULT_VALUE);


        this.getChildren().addAll(returnButton, col, row, startButton);
    }

    public Button getReturnButton() {
        return returnButton;
    }
    public Button getStartButton() {
        System.out.println("Je Recupere le bouton start");
        return startButton;
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
