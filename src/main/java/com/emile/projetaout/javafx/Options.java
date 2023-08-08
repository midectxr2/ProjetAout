package com.emile.projetaout.javafx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Options extends VBox {

    private Button returnButton;
    private Button startButton;
    private ChoiceBox col;
    private ChoiceBox row;
    private final int DEFAULT_VALUE = 10;
    private int res_col = DEFAULT_VALUE;
    private int res_row = DEFAULT_VALUE;
    private VBox vBox1 = new VBox();

    public Options() {

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        returnButton = new Button("Return to Main Menu");
        startButton = new Button("Start");

        HBox hBox_grid = new HBox();
        hBox_grid.setSpacing(10);

        Text gridLength = new Text("Taille Grille: ");
        Text row_length = new Text("Lignes: ");
        Text col_length = new Text("Colonnes: ");


        col = new ChoiceBox(FXCollections.observableArrayList("5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        row = new ChoiceBox(FXCollections.observableArrayList("5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
        final int[] value = new int[]{5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        col.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) -> {
                        res_col = value[new_val.intValue()];
                }
        );

        col.setValue(DEFAULT_VALUE);

        row.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                 Number old_val, Number new_val) -> {
                    res_row = value[new_val.intValue()];
                }
        );

        row.setValue(DEFAULT_VALUE);
        hBox_grid.getChildren().addAll(gridLength, row_length, row, col_length, col);

        int res;

        for(int i=1; i<7;i++){
            HBox newHbox = new HBox();
            newHbox.setSpacing(10);
            Display display = new Display(i);
            vBox1.getChildren().addAll(display);
        }

        Text boatLength = new Text("Taille Bateaux: ");

        vBox.getChildren().addAll(returnButton, hBox_grid, boatLength, vBox1, startButton);

        this.getChildren().addAll(vBox);
    }

    public Button getReturnButton() {
        return returnButton;
    }
    public Button getStartButton() {
        return startButton;
    }


    public int getRes_col() {
        return res_col;
    }

    public int getRes_row() {
        return res_row;

    }

    public ArrayList<Integer> getBoat(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < vBox1.getChildren().size(); i++ ){
            for (int j = 0; j < ((Display) vBox1.getChildren().get(i)).getNumber(); j++ ){
                list.add( i+1 );
            }
        }
        return list;
    }


}
