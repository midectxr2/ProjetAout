package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.Bateau;
import com.emile.projetaout.logiquetest.Cellule;
import com.emile.projetaout.logiquetest.Grille;
import com.emile.projetaout.logiquetest.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public class CelluleJavafx extends Rectangle {
    private Cellule cellule;
    private Player player;

    public CelluleJavafx(Cellule cellule){
        setWidth(30);
        setHeight(30);
        this.setFill(Color.LIGHTGRAY);
        this.setStroke(Color.BLACK);
        this.cellule = cellule;
    }

    public boolean tirerSur(){
        boolean res = cellule.tirerSur();
        updateView();
        return res;
    }

    public void updateView(){
        if(cellule.getBateau() == null){
            this.setFill(Color.BLUE);
        }else{
            this.setFill(Color.BLACK);
        }
    }

    public void allowCheat(){
        updateView();
    }

    public void disallowCheat(){
        if(cellule.estTouchee()){
            updateView();
        }else{
            this.setFill(Color.LIGHTGRAY);
        }
    }
}
