package com.emile.projetaout.logiquetest;

import java.util.Objects;

public class Cell {


    private boolean etat; // false si la cellule n'a pas été touchée, true sinon
    private Boat boat; // Référence au boat qui occupe cette cellule, null si aucune

    private Position position;



    //constructeur de cellules
    public Cell(int x, int y) {
        this.position = new Position(x, y);
        this.etat = false;
        this.boat = null;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "etat=" + etat +
                ", boat=" + boat +
                '}';
    }



    //methode qui retourne vrai si la cellule a deja été touché ou sinon faux
    public boolean isHit() {
        return this.etat;
    }



    //getter de boat
    public Boat getBoat() {
        return this.boat;
    }


    //setter de boat
    public void setBoat(Boat boat) {
        this.boat = boat;
    }



    //methode qui retrourne faux si l'on peut tirer sur la cellule ou non. si l'on peut alors, elle tire sur le bateau lié a la cellule si il y en a un
    public boolean fireAt() {
        if(!isHit()){
            this.etat = true;
            if (this.boat != null) {
                this.boat.hit();
                if(!boat.isAlive()){
                }
            }

            return true;
        }
        return false;

    }



    //getter de position
    public Position getPosition() {
        return position;
    }



    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return etat == cell.etat && Objects.equals(boat, cell.boat) && Objects.equals(position, cell.position);
    }


    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public int hashCode() {
        return Objects.hash(etat, boat, position);
    }


}

