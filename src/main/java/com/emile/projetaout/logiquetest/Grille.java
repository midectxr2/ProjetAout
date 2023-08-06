package com.emile.projetaout.logiquetest;

import javafx.geometry.Point2D;
import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private int lignes;
    private int colonnes;
    private Cellule[][] cellules;

    public Grille(int taille) {
        this.lignes = taille;
        this.colonnes = taille;
        this.cellules = new Cellule[lignes][colonnes];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.cellules[i][j] = new Cellule(i, j);
            }
        }
    }

    public Grille(int colonnes, int lignes){
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.cellules = new Cellule[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                this.cellules[i][j] = new Cellule(i, j);
            }
        }
        this.afficherGrille();
    }

    public int getLignes() {
        return lignes;
    }

    public int getColonnes() {
        return colonnes;
    }

    /*public void setTaille(int taille) {
        this.taille = taille;
    }*/

    public Cellule getCellules(int x, int y) {
        return cellules[x][y];
    }

    public void placerBateau(Bateau bateau, int x, int y, Direction direction) {
        // Vérifiez si la position de départ est dans la grille.
        if (x < 0 || y < 0 || x >= lignes || y >= colonnes) {
            throw new IllegalArgumentException("Position de départ hors de la grille.");
        }

        // Vérifiez si le bateau peut être placé dans la direction donnée à partir de la position de départ.
        if (direction == Direction.HORIZONTAL && x + bateau.getTaille() > colonnes || direction == Direction.VERTICAL && y + bateau.getTaille() > lignes) {
            throw new IllegalArgumentException("Bateau trop grand pour être placé à cette position dans cette direction.");
        }

        // Vérifiez si le bateau chevauche un autre bateau.
        for (int i = 0; i < bateau.getTaille(); i++) {
            if (direction == Direction.HORIZONTAL) {
                if (cellules[x + i][y].getBateau() != null) {
                    throw new IllegalArgumentException("Bateau chevauche un autre bateau.");
                }
            } else { // Direction.VERTICAL
                if (cellules[x][y + i].getBateau() != null) {
                    throw new IllegalArgumentException("Bateau chevauche un autre bateau.");
                }
            }
        }


        for (int i = 0; i < bateau.getTaille(); i++) {
            if (direction == Direction.HORIZONTAL) {
                Cellule[] neighbors = getNeihbour(x+i, y);
                for(Cellule cellule: neighbors){
                    if(cellule.getBateau() != null){
                        throw new IllegalArgumentException(("Bateau trop proche"));
                    }
                }
            }
            else{
                Cellule[] neighbors = getNeihbour(x, y+i);
                for(Cellule cellule: neighbors){
                    if(cellule.getBateau() != null){
                        throw new IllegalArgumentException(("Bateau trop proche"));
                    }
                }

            }

        }




        // Placez le bateau sur la grille.
        for (int i = 0; i < bateau.getTaille(); i++) {
            if (direction == Direction.HORIZONTAL) {
                cellules[x + i][y].setBateau(bateau);
            } else { // Direction.VERTICAL
                cellules[x][y + i].setBateau(bateau);
            }
        }
    }

    public Cellule[] getNeihbour(int x, int y){
        Point2D[] points = new Point2D[]{
                new Point2D(x-1, y),
                new Point2D(x-1, y-1),
                new Point2D(x, y-1),
                new Point2D(x-1, y+1),
                new Point2D(x+1, y),
                new Point2D(x, y+1),
                new Point2D(x+1, y+1),
                new Point2D(x+1, y-1),
        };
        List<Cellule> neighbors = new ArrayList<Cellule>();
        for(Point2D p: points){
            if(isValidPoint(p))
                neighbors.add(cellules[(int)p.getX()][(int)p.getY()]);
            }
        return neighbors.toArray(new Cellule[0]);

    }

    private boolean isValidPoint(Point2D point2D){
        return isValidPoint(point2D.getX(), point2D.getY());
    }

    private boolean isValidPoint(double x, double y){
        return x>=0 && x<getColonnes() && y>=0 && y<getLignes();
    }


    public void tirer(int x, int y) {
        System.out.println("Je tire en : " + x + " - "+ y);

        // Vérifiez si la position est dans la grille.
        if (x < 0 || y < 0 || x >= lignes || y >= colonnes) {
            throw new IllegalArgumentException("Position hors de la grille.");
        }

        Cellule cellule = cellules[x][y];

        // Vérifiez si la cellule a déjà été touchée.
        if (cellule.estTouchee()) {
            throw new IllegalArgumentException("Cellule déjà touchée.");
        }

        // Marquez la cellule comme étant touchée.
        cellule.tirerSur();

        // Si la cellule contient un bateau, informez le bateau qu'il a été touché.
        /*Bateau bateau = cellule.getBateau();
        if (bateau != null) {
            System.out.println("Bateau touche");
            bateau.toucher();
        }*/
    }

    public void afficherGrille() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (cellules[i][j].estTouchee()) {
                    //System.out.println(cellules[i][j].toString());

                    if (cellules[i][j].getBateau() != null) {
                        System.out.print("O "); // Tir réussi
                    } else {
                        System.out.print("X "); // Tir manqué
                    }
                } else {
                    System.out.print("~ "); // Cellule non touchée
                }
            }
            System.out.println();
        }
    }
}

