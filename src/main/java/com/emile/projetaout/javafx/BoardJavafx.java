package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.Grid;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Random;

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
                        if (cellJavafx1.fireAt()) {
                            if (game.getGame().getP1().isFinished()) {
                                System.out.println("Partie terminée, le joueur a gagné");
                                showAlert("Le joueur a gagné");
                            } else {
                                System.out.println("Tour du BOT");
                                playP2Turn();
                            }
                        } else {
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

    public void playP2Turn() {
        int x = random.nextInt(game.getGame().getP1().getGrid().getRows());
        int y = random.nextInt(game.getGame().getP1().getGrid().getColumns());

        HBox rows = (HBox) game.getPlayerGrid().getRowss().getChildren().get(x);
        CellJavafx cell = (CellJavafx) rows.getChildren().get(y);


        while (!cell.fireAt()) {
            x = random.nextInt(game.getGame().getP1().getGrid().getRows());
            y = random.nextInt(game.getGame().getP1().getGrid().getColumns());
            rows = (HBox) game.getPlayerGrid().getRowss().getChildren().get(x);
            cell = (CellJavafx) rows.getChildren().get(y);
        }
        System.out.println("l'ordi a tiré en x: " + x + ", y: " + y);
        // Verification si l'ordi a gagné
        if (game.getGame().getP2().isFinished()) {
            System.out.println("Partie terminée, le bot a gagné");
            showAlert("Le Bot a gagné");
        }else{
            game.getGame().getP1().getGrid().showGrid();

            game.getGame().getP2().getGrid().showGrid();
            System.out.println("Le bot n'a pas gagné");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
