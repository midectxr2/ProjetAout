package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.Grille;
import com.emile.projetaout.logiquetest.Player;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardJavafx extends Parent {
    private Grille grille;
    private VBox rowss = new VBox();
    private Player player;


    public BoardJavafx(Grille grille) {
        this.grille = grille;



        for (int x = 0; x < grille.getLignes(); x++) {
            HBox rows = new HBox();
            for (int y = 0; y < grille.getColonnes(); y++) {
                CelluleJavafx celluleJavafx1 = new CelluleJavafx(grille.getCellules(x, y));
                rows.getChildren().add(celluleJavafx1);
                celluleJavafx1.setOnMouseClicked(e -> {
                    celluleJavafx1.updateView();
                });
            }
            rowss.getChildren().add(rows);

        }
        getChildren().add(rowss);
    }

    public Grille getGrille() {
        return grille;
    }
}
