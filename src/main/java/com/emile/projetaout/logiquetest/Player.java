package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Grille grille;
    private List<Bateau> bateaux;


    public Player(Grille grille, List<Integer> taillesBateaux){
        this.grille = grille;
        this.bateaux = new ArrayList<>();
        for (int taille : taillesBateaux) {
            this.bateaux.add(new Bateau(taille));
        }

    }

    public List<Bateau> getBateaux() {
        return bateaux;
    }

    public Grille getGrille() {
        return grille;
    }

    public boolean estTermine() {
        for (Bateau bateau : this.bateaux) {
            if (!bateau.estCoule()) {
                return false;
            }
        }
        return true;
    }
}
