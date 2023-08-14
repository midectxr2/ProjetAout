package com.emile.projetaout.logiquetest;

import java.util.Objects;

public class Cell {

    private boolean etat; // false si la cellule n'a pas été touchée, true sinon
    private Boat boat; // Référence au boat qui occupe cette cellule, null si aucune

    private Position position;


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
                if(!boat.isAlive()){
                    System.out.println("touché coulé");
                }
            }

            return true;
        }
        return false;

    }

    public Position getPosition() {
        return position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return etat == cell.etat && Objects.equals(boat, cell.boat) && Objects.equals(position, cell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(etat, boat, position);
    }
}

