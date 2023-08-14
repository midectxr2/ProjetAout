package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Boat {
    private int length;

    private int health;

    private List<Cell> cells;

    public Boat(int size) {
        this.length = size;
        this.health = size;
        this.cells = new ArrayList<>();
    }

    public int getLength() {
        return length;
    }


    public void addCell(Cell cell) {
        this.cells.add(cell);
        cell.setBoat(this);
    }

    @Override
    public String toString() {
        return "Boat{" +
                "length=" + length +
                ", health=" + health +
                //", cells=" + cells +
                '}';
    }

    public void hit() {
        this.health -= 1;
        System.out.println("Je perds de la health: " + this.health);
    }

    public boolean isAlive() {
        return this.health <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boat)) return false;
        Boat boat = (Boat) o;
        return length == boat.length && health == boat.health;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, health, cells);
    }
}
