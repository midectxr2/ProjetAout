package com.emile.projetaout.javafx;

import com.emile.projetaout.logiquetest.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellJavafx extends Rectangle {
    private Cell cell;


    //constructeur de CellJavafx, definit sa taille ainsi que sa couleur

    public CellJavafx(Cell cell){
        setWidth(25);
        setHeight(25);
        this.setFill(Color.LIGHTGRAY);
        this.setStroke(Color.BLACK);
        this.cell = cell;
    }


    //methode updateview de CellJavafx, permet lorsqu'appellée de changer la couleur des cellulejavajx en fonction de certains évenements
    public void updateView() {
        if (cell.getBoat() == null) {
            setFill(Color.BLUE);

        } else {
            this.setFill(Color.BLACK);
            if (cell.getBoat().isDead()) {
                setFill(Color.GREEN);
            }
        }
    }



    //methode qui permet d'activer le cheat (activer le cheat revient a faire update view pour toutes les cellules javafx de la grille adverse)
    public void allowCheat(){
        updateView();
    }



    //methode qui permet de desactiver le cheat, cad les cellules ou l'on a pas encore tirés se remettent juste en gris
    public void disallowCheat(){
        if(cell.isHit()){
            updateView();

        }else{
            this.setFill(Color.LIGHTGRAY);
        }
    }


    //getter de cell
    public Cell getCell() {
        return cell;
    }



}
