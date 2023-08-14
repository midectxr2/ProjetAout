package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.Boat;
import com.emile.projetaout.logiquetest.Cell;
import com.emile.projetaout.logiquetest.Player;
import com.emile.projetaout.logiquetest.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CellJavafx extends Rectangle {
    private Cell cell;

    public CellJavafx(Cell cell){
        setWidth(25);
        setHeight(25);
        this.setFill(Color.LIGHTGRAY);
        this.setStroke(Color.BLACK);
        this.cell = cell;
    }

    public void updateView() {
        if (cell.getBoat() == null) {
            setFill(Color.BLUE);

        } else {
            this.setFill(Color.BLACK);
            if (cell.getBoat().isAlive()) {
                setFill(Color.GREEN);
            }
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

    public Cell getCell() {
        return cell;
    }



}
