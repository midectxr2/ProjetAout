package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.util.Random;

import static java.lang.Math.abs;

public class BoardJavafx extends Parent {
    private Battleshiptest game;
    private Grid grid;
    private VBox rowss = new VBox();
    boolean isPlayer;

    /*
    méthode qui creer les grille de jeu en javafx ainsi que ses conditions en fonction d'a qui appartient la grille
    elle permet surtout de gerer le moment ou l'on veut jouer en player vs ia cad lorsque l'on clique directement sur la grille
     */
    public BoardJavafx(Battleshiptest game, Grid grid, boolean isPlayer) {
        this.game = game;
        this.grid = grid;
        this.isPlayer = isPlayer;





        for (int x = 0; x < grid.getRows(); x++) {
            HBox rows = new HBox();
            for (int y = 0; y < grid.getColumns(); y++) {
                CellJavafx cellJavafx1 = new CellJavafx(grid.getCell(x, y));
                rows.getChildren().add(cellJavafx1);
                if(isPlayer) {
                    cellJavafx1.setOnMouseClicked(e -> {
                        if(game.getGame().play(cellJavafx1.getCell())){
                            game.refreshAllView();

                            if(game.getGame().isFinished()){

                                showAlert("Le joueur a gagné " + "en " +(int) game.getGame().getTurn()/2+" tours.");
                            }else{
                                System.out.println("Distace man: "+grid.distanceMan(cellJavafx1.getCell()));
                                System.out.println("Longueur du bateau le plus proche :" +grid.lengthNearestBoat());
                                System.out.println("--------------------------------------------------------" + "\n");
                                game.getGame().play();
                                game.refreshAllView();

                                if (game.getGame().isFinished())showAlert("L'IA a gagné " + "en " +(int) game.getGame().getTurn()/2+" tours.");
                            }
                        }else{
                            System.out.println("Tirez ailleurs!");
                        }

                    });
                }
            }
            rowss.getChildren().add(rows);

        }
        getChildren().add(rowss);
    }


    // guetteur de Rowss (Vbox)
    public VBox getRowss() {
        return rowss;
    }


    //methode qui permet de recuprer le showalert de la classe Game
    private void showAlert(String message) {

        game.showAlert(message);
    }


}
