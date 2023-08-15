package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Boat {
    private int length;

    private int health;

    private List<Cell> cells;


    //constructeur de Boat
    public Boat(int size) {
        this.length = size;
        this.health = size;
        this.cells = new ArrayList<>();
    }


    //getter de length
    public int getLength() {
        return length;
    }



    //methode qui permet d'ajouter une cellule a la list de cellule de la grille
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



    //methode hit, elle permet de retirer de la vie a l'attribut health du bateau
    public void hit() {
        this.health -= 1;
        System.out.println("Je perds de la health: " + this.health);
    }


    //methode qui renvoie vrai ou faux si le bateaux n'est pas coulé ou si il l'est
    public boolean isAlive() {
        return this.health <= 0;
    }



    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boat)) return false;
        Boat boat = (Boat) o;
        return length == boat.length && health == boat.health;
    }


    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public int hashCode() {
        return Objects.hash(length, health, cells);
    }
}
