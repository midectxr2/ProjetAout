package com.emile.projetaout.logiquetest;

import java.util.Objects;

public class Position {
    private int row;
    private int col;


    //constructeur de position
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    //getter de row
    public int getRow() {
        return row;
    }


    //getter de col
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }



    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }


    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
