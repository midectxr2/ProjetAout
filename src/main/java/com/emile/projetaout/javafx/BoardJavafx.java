package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Random;

import static java.lang.Math.abs;

public class BoardJavafx extends Parent {
    private Battleshiptest game;
    private Grid grid;
    private VBox rowss = new VBox();
    Random random = new Random();

    boolean isPlayer;

    public BoardJavafx(Battleshiptest game, Grid grid, boolean isPlayer) {
        this.game = game;
        this.grid = grid;
        this.isPlayer = isPlayer;

        for (int x = 0; x < grid.getRows(); x++) {
            HBox rows = new HBox();
            for (int y = 0; y < grid.getColumns(); y++) {
                CellJavafx cellJavafx1 = new CellJavafx(grid.getCell(x, y));
                rows.getChildren().add(cellJavafx1);
                if(!isPlayer) {
                    cellJavafx1.setOnMouseClicked(e -> {
                        System.out.println("Je tire en :" + cellJavafx1.getCell().getPosition().toString());
                        if(game.getGame().play(cellJavafx1.getCell())){

                            //cellJavafx1.fireAt();

                            if(game.getGame().isFinished()){
                                showAlert("Le joueur a gagné");
                            }else{
                                System.out.println(grid.distanceMan(cellJavafx1.getCell()));
                                System.out.println(grid.lengthNearestBoat());
                                game.getGame().play();
                                game.refreshAllView();

                                //Ia ia = (Ia) game.getGame().getPlayersList().get(1);
                                //HBox rows_grid = (HBox) game.getPlayerGrid().getRowss().getChildren().get(ia.getLastX());
                                //CellJavafx cell = (CellJavafx) rows_grid.getChildren().get(ia.getLastY());
                                //cell.fireAt();
                                if (game.getGame().isFinished())showAlert("Le bot a gagné");
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



    public VBox getRowss() {
        return rowss;
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
