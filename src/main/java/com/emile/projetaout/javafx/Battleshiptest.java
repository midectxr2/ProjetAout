package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Battleshiptest extends Parent {
    Popup popup = new Popup();

    private BoardJavafx playerGrid, enemyGrid;
    private Game game;
    CheckBox checkBoxCheat = new CheckBox("Cheat");
    Stage stage;

    //constructeur Battleshiptest

    public Battleshiptest(Stage stage){
        super();
        this.stage = stage;
    }

    /*
    methode qui créer une partie joueur vs ia et qui creer les 2 grilles javafx des 2 joueurs
     */
    public Parent createContent(int rows, int columns, List<Integer> list){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        Label label = new Label("GRILLE IA");
        Label label1 = new Label("GRILLE JOUEUR");
        label1.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);
        game = new Game();
        game.setPlayerVsIa(rows , columns , list);

        enemyGrid = new BoardJavafx(this, game.getPlayersList().get(1).getGrid(), true);
        playerGrid = new BoardJavafx(this, game.getPlayersList().get(0).getGrid(), false);

        VBox vbox = new VBox(30, label, enemyGrid, label1 ,playerGrid);
        vbox.setAlignment(Pos.CENTER);




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


        VBox rightVbox = new VBox(50, checkBoxCheat);
        rightVbox.setAlignment(Pos.CENTER);

        root.setRight(rightVbox);
        root.setCenter(vbox);


        return root;


    }


    /*
    methode qui créer une partie smartia vs ia et qui creer les 2 grilles javafx des 2 joueurs + le bouton pour finir la partie directement avec ses events
     */

    public Parent createContentPlayervsSmartIa(int rows, int columns, List<Integer> list){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        game = new Game();
        game.setPlayerVsSmartIa(rows, columns, list);

        Label label = new Label("GRILLE SMART IA");
        Label label1 = new Label("GRILLE JOUEUR");
        label1.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);
        enemyGrid = new BoardJavafx(this, game.getPlayersList().get(1).getGrid(), true);
        playerGrid = new BoardJavafx(this, game.getPlayersList().get(0).getGrid(), false);

         VBox vbox = new VBox(30, label, enemyGrid, label1 ,playerGrid);
        vbox.setAlignment(Pos.CENTER);

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


        VBox rightVbox = new VBox(50, checkBoxCheat);
        rightVbox.setAlignment(Pos.CENTER);

        root.setRight(rightVbox);
        root.setCenter(vbox);


        return root;


    }
    public Parent createContentSmartIaVsIa(int rows, int columns, List<Integer> list){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        game = new Game();
        game.setSmartIaVsIa(rows , columns , list);

        Label label = new Label("GRILLE IA");
        Label label1 = new Label("GRILLE SMART IA");
        label1.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);

        enemyGrid = new BoardJavafx(this, game.getPlayersList().get(1).getGrid(), false);
        playerGrid = new BoardJavafx(this, game.getPlayersList().get(0).getGrid(), false);

        VBox vbox = new VBox(30, label, enemyGrid, label1 ,playerGrid);
        vbox.setAlignment(Pos.CENTER);

        Button buttonNext = new Button("NEXT");
        buttonNext.setOnMouseClicked(event -> {
            game.play();
            this.refreshAllView();
            if(game.getPlayersList().get(1).isFinished()){
                showAlert("La smart IA a gagné " + "en " +(int) game.getTurn()/2+" tours.");

            }
            if(game.getPlayersList().get(0).isFinished()){
                showAlert("L'IA a gagné " + "en " +(int) game.getTurn()/2+" tours.");
            }


        });

        Button buttonFinish = new Button("FINIR LA PARTIE");
        buttonFinish.setOnMouseClicked(event -> {
            while(!game.isFinished()){
                game.play();
            }
            refreshAllView();
            if(game.getPlayersList().get(1).isFinished()){
                showAlert("La smart IA a gagné " + "en " +(int) game.getTurn()/2+" tours.");


            }
            if(game.getPlayersList().get(0).isFinished()){
                showAlert("L'IA a gagné " + "en " +(int) game.getTurn()/2+" tours.");


            }


        });


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
        VBox rightVbox = new VBox(50, checkBoxCheat, buttonNext, buttonFinish);
        rightVbox.setAlignment(Pos.CENTER);
        root.setRight(rightVbox);
        root.setCenter(vbox);
        return root;
    }


    // methode qui active le cheat en recupérant d'abord toutes les HBOX de le la grille adverse puis Les Cellules javafx contenues dans les HBOX
    // et appelle la fonction allowcheat de cellule javafx voir sa description
    public void allowCheat(){
        for(int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox rows = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < rows.getChildren().size(); j++) {
                CellJavafx cellJavafx1 = (CellJavafx) rows.getChildren().get(j);
                cellJavafx1.allowCheat();
            }
        }
    }

    //meme methode que allow cheat mais ici pour desactiver le cheat
    //voir def de disallow cheat dans cellulejavafx
    public void disallowCheat(){
        for(int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox rows = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < rows.getChildren().size(); j++) {
                CellJavafx cellJavafx1 = (CellJavafx) rows.getChildren().get(j);
                cellJavafx1.disallowCheat();
            }
        }
    }


    //methode qui permet d'actualiser a chaque tour les 2 grilles avec les changement effectués
    //par exemple bateau touché donc case en noir etc.
    // voir methode disallow cheat cellulejavafx
    public void refreshAllView(){
        for (int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox rows = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < rows.getChildren().size(); j++) {
                CellJavafx cellJavafx1 = (CellJavafx) rows.getChildren().get(j);
                cellJavafx1.disallowCheat();

            }
        }
        for(int i = 0; i < playerGrid.getRowss().getChildren().size(); i++) {
            HBox rows = (HBox) playerGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < rows.getChildren().size(); j++) {
                CellJavafx cellJavafx1 = (CellJavafx) rows.getChildren().get(j);
                cellJavafx1.disallowCheat();

            }
        }


        checkBoxCheat.setSelected(false);
    }
    //guetteur de game
    public Game getGame() {
        return game;
    }


    //methode qui affiche l'alerte de fin de partie
    public void showAlert(String message) {
        VBox vbox = (VBox)popup.getContent().get(0);
        Label label = (Label)vbox.getChildren().get(0);
        label.setText(message);
        popup.show(stage);
    }

    //guetter de popup
    public Popup getPopup() {
        return popup;
    }
}
