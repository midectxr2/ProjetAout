package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Battleshiptest extends Parent {
    private boolean running = false;
    private BoardJavafx playerGrid, enemyGrid;
    private Jeu jeu;
    private int shipsToPlace = 5;
    private boolean enemyTurn = false;
    private int score  = 0;

    private Menu menu = new Menu();
    private Random random = new Random();
    private int tailleGrid = 10;
    CelluleJavafx celluleJavafx;
    Cellule cellule;


    public Parent createContent(int ligne, int colonnes, List<Integer> list){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        jeu = new Jeu(ligne, colonnes, list);

        enemyGrid = new BoardJavafx(this, jeu.getP1().getGrille(), false);
        playerGrid = new BoardJavafx(this, jeu.getP2().getGrille(), true);

        VBox vbox = new VBox(50, enemyGrid, playerGrid);
        vbox.setAlignment(Pos.CENTER);



        CheckBox checkBoxCheat = new CheckBox("Cheat");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkBoxCheat.isSelected()){
                    allowCheat();
                }
                else{
                    disallowCheat();
                }
            }
        };
        checkBoxCheat.setOnAction(event);


        VBox vboxDroite = new VBox(50, checkBoxCheat);
        vboxDroite.setAlignment(Pos.CENTER);

        root.setRight(vboxDroite);
        root.setCenter(vbox);





        return root;


    }

    public void allowCheat(){
        for(int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox ligne = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < ligne.getChildren().size(); j++) {
                CelluleJavafx celluleJavafx1 = (CelluleJavafx) ligne.getChildren().get(j);
                celluleJavafx1.allowCheat();
            }
        }
    }

    public void disallowCheat(){
        for(int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox ligne = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < ligne.getChildren().size(); j++) {
                CelluleJavafx celluleJavafx1 = (CelluleJavafx) ligne.getChildren().get(j);
                celluleJavafx1.disallowCheat();
            }
        }
    }


    public Jeu getJeu() {
        return jeu;
    }

    public BoardJavafx getPlayerGrid() {
        return playerGrid;
    }
}
