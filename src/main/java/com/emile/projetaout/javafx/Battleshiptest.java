package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class Battleshiptest extends Parent {
    private BoardJavafx playerGrid, enemyGrid;
    private Game game;

    public Parent createContent(int rows, int columns, List<Integer> list){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        game = new Game(rows, columns, list);

        enemyGrid = new BoardJavafx(this, game.getP1().getGrid(), false);
        playerGrid = new BoardJavafx(this, game.getP2().getGrid(), true);

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


        VBox rightVbox = new VBox(50, checkBoxCheat);
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


    public Game getGame() {
        return game;
    }

    public BoardJavafx getPlayerGrid() {
        return playerGrid;
    }
}
