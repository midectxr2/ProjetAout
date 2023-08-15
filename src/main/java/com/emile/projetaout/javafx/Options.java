package com.emile.projetaout.javafx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
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

    private Button startIaButton;
    private ChoiceBox col;
    private ChoiceBox row;
    private final int DEFAULT_VALUE = 10;
    private int res_col = DEFAULT_VALUE;
    private int res_row = DEFAULT_VALUE;
    private VBox vBox1 = new VBox();


    //constructeur de options, il permet d'afficher X fois les choiceBox definie dans Display ainsi que d'en creer 2 autres pour la
    // longueur et la largeur de la grille
    public Options() {

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        returnButton = new Button("Return to Main Menu");
        startButton = new Button("Start");
        startIaButton = new Button("IA START");

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



        for(int i=1; i<7;i++){
            Display display = new Display(i);
            vBox1.getChildren().addAll(display);
            vBox1.setAlignment(Pos.CENTER);
        }



        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(returnButton, hBox_grid, vBox1, startButton, startIaButton);

        this.getChildren().addAll(vBox);
    }

    //getter de returnbutton
    public Button getReturnButton() {
        return returnButton;
    }


    //getter de startbutton
    public Button getStartButton() {
        return startButton;
    }


    //getter de startiaboutton
    public Button getStartIaButton(){return startIaButton;}


    //getter de res_col
    public int getRes_col() {
        return res_col;
    }


    //getter de res_row
    public int getRes_row() {
        return res_row;

    }


    //methode getBoat, permet de retourner une liste de int, la methode sert a recuperer dans la liste la taille des différents bateaux choisis
    //dans les différentes choiceBox de options
    public ArrayList<Integer> getBoat(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < vBox1.getChildren().size(); i++ ){
            for (int j = 0; j < ((Display) vBox1.getChildren().get(i)).getNbr(); j++ ){
                list.add( i+1 );
            }
        }
        return list;
    }


}
