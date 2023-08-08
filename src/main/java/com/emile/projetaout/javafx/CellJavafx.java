package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.Cell;
import com.emile.projetaout.logiquetest.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellJavafx extends Rectangle {
    private Cell cell;
    private Player player;

    public CellJavafx(Cell cell){
        setWidth(30);
        setHeight(30);
        this.setFill(Color.LIGHTGRAY);
        this.setStroke(Color.BLACK);
        this.cell = cell;
    }

    public boolean fireAt(){
        boolean res = cell.fireAt();
        updateView();
        return res;
    }

    public void updateView(){
        if(cell.getBoat() == null){
            this.setFill(Color.BLUE);
        }else{
            this.setFill(Color.BLACK);
        }
    }

    public void allowCheat(){
        updateView();
    }

    public void disallowCheat(){
        if(cell.isHit()){
            updateView();
        }else{
            this.setFill(Color.LIGHTGRAY);
        }
    }
}
