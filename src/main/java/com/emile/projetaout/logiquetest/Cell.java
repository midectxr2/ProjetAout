package com.emile.projetaout.logiquetest;

public class Cell {

    private boolean etat; // false si la cellule n'a pas été touchée, true sinon
    private Boat boat; // Référence au boat qui occupe cette cellule, null si aucune

    private Position position;

    private int x;
    private int y;

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

    public boolean isHit() {
        return this.etat;
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public boolean fireAt() {
        if(!isHit()){
            this.etat = true;
            if (this.boat != null) {
                this.boat.hit();
            }
            return true;
        }
        return false;
    }
}
