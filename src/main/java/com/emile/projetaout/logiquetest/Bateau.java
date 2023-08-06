package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;

public class Bateau {
    private int taille;
    private int vie;
    private List<Cellule> cellules;

    public Bateau(int taille) {
        this.taille = taille;
        this.vie = taille;
        this.cellules = new ArrayList<>();
    }

    public int getTaille() {
        return taille;
    }

    public List<Cellule> getCellules() {
        return cellules;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void ajouterCellule(Cellule cellule) {
        this.cellules.add(cellule);
        cellule.setBateau(this);
    }

    @Override
    public String toString() {
        return "Bateau{" +
                "taille=" + taille +
                ", vie=" + vie +
                ", cellules=" + cellules +
                '}';
    }

    public void toucher() {
        this.vie -= 1;
        System.out.println("Je perds de la vie: " + this.vie);
    }

    public boolean estCoule() {
        return this.vie <= 0;
    }
}
