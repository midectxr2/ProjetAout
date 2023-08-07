package com.emile.projetaout.logiquetest;

import javafx.geometry.Pos;

public class Cellule {

    private boolean etat; // false si la cellule n'a pas été touchée, true sinon
    private Bateau bateau; // Référence au bateau qui occupe cette cellule, null si aucune

    private Position position;

    private int x;
    private int y;

    public Cellule(int x, int y) {
        this.position = new Position(x, y);
        this.etat = false;
        this.bateau = null;
    }

    @Override
    public String toString() {
        return "Cellule{" +
                "etat=" + etat +
                ", bateau=" + bateau +
                '}';
    }

    public boolean estTouchee() {
        return this.etat;
    }

    public Bateau getBateau() {
        return this.bateau;
    }

    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
    }

    public boolean tirerSur() {
        if(!estTouchee()){
            this.etat = true;
            if (this.bateau != null) {
                this.bateau.toucher();
            }
            return true;
        }
        return false;
    }
}

