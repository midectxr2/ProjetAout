package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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


    public Parent createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        jeu = new Jeu(10, 10, Arrays.asList(3, 3, 3, 3, 3, 3, 3));

        enemyGrid = new BoardJavafx(jeu.getP1().getGrille());
        playerGrid = new BoardJavafx(jeu.getP2().getGrille());



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




    }

    public void disallowCheat(){
        Player p1 = jeu.getP1();
        Player p2 = jeu.getP2();
        List<Bateau> bateauListP1 = p1.getBateaux();
        List<Bateau> bateauListP2 = p2.getBateaux();
        for(Bateau bateau: bateauListP1){
            List<Cellule> ListCelluleP1 = bateau.getCellules();
            for(Cellule cellule1: ListCelluleP1){
                CelluleJavafx newcellule = new CelluleJavafx(cellule1);
                newcellule.setFill(Color.LIGHTGRAY);
            }
        }
        for(Bateau bateau: bateauListP2){
            List<Cellule> ListCelluleP2 = bateau.getCellules();
            for(Cellule cellule1: ListCelluleP2){
                CelluleJavafx newcellule1 = new CelluleJavafx(cellule1);
                newcellule1.setFill(Color.LIGHTGRAY);
            }
        }
    }






}
