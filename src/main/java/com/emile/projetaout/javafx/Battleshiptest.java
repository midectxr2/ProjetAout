package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    public Battleshiptest(Stage stage){
        super();
        this.stage = stage;
    }


    public Parent createContent(int rows, int columns, List<Integer> list){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        game = new Game();
        game.setPlayerVsIa(rows , columns , list);

        enemyGrid = new BoardJavafx(this, game.getPlayersList().get(1).getGrid(), false);
        playerGrid = new BoardJavafx(this, game.getPlayersList().get(0).getGrid(), true);

        VBox vbox = new VBox(50, enemyGrid, playerGrid);
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

        enemyGrid = new BoardJavafx(this, game.getPlayersList().get(1).getGrid(), false);
        playerGrid = new BoardJavafx(this, game.getPlayersList().get(0).getGrid(), false);

        VBox vbox = new VBox(50, enemyGrid, playerGrid);
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



    public void allowCheat(){
        for(int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox rows = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < rows.getChildren().size(); j++) {
                CellJavafx cellJavafx1 = (CellJavafx) rows.getChildren().get(j);
                cellJavafx1.allowCheat();
            }
        }
    }

    public void disallowCheat(){
        for(int i = 0; i < enemyGrid.getRowss().getChildren().size(); i++) {
            HBox rows = (HBox) enemyGrid.getRowss().getChildren().get(i);
            for (int j = 0; j < rows.getChildren().size(); j++) {
                CellJavafx cellJavafx1 = (CellJavafx) rows.getChildren().get(j);
                cellJavafx1.disallowCheat();
            }
        }
    }

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

        System.out.println("je refresh");
        checkBoxCheat.setSelected(false);
    }


    public Game getGame() {
        return game;
    }


    public void showAlert(String message) {
        VBox vbox = (VBox)popup.getContent().get(0);
        Label label = (Label)vbox.getChildren().get(0);
        label.setText(message);
        popup.show(stage);
    }


    public Popup getPopup() {
        return popup;
    }
}
