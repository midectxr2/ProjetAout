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
                if(!isPlayer) {
                    celluleJavafx1.setOnMouseClicked(e -> {
                        if (celluleJavafx1.tirerSur()) {
                            if (jeu.getJeu().getP1().estTermine()) {
                                System.out.println("Partie terminée, le joueur a gagné");
                                showAlert("Le joueur a gagné");
                            } else {
                                System.out.println("Tour du BOT");
                                jouerTourp2();
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

    public Grille getGrille() {
        return grille;
    }

    public VBox getRowss() {
        return rowss;
    }

    public void jouerTourp2() {
        int x = random.nextInt(jeu.getJeu().getP1().getGrille().getLignes());
        int y = random.nextInt(jeu.getJeu().getP1().getGrille().getColonnes());

        HBox ligne = (HBox) jeu.getPlayerGrid().getRowss().getChildren().get(x);
        CelluleJavafx cell = (CelluleJavafx) ligne.getChildren().get(y);

        while (!cell.tirerSur()) {
            x = random.nextInt(jeu.getJeu().getP1().getGrille().getLignes());
            y = random.nextInt(jeu.getJeu().getP1().getGrille().getColonnes());
            ligne = (HBox) jeu.getPlayerGrid().getRowss().getChildren().get(x);
            cell = (CelluleJavafx) ligne.getChildren().get(y);
        }
        System.out.println("l'ordi a tiré en x: " + x + ", y: " + y);
        // Verification si l'ordi a gagné
        if (jeu.getJeu().getP2().estTermine()) {
            System.out.println("Partie terminée, le bot a gagné");
            showAlert("Le Bot a gagné");
        }else{
            jeu.getJeu().getP1().getGrille().afficherGrille();

            jeu.getJeu().getP2().getGrille().afficherGrille();
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
