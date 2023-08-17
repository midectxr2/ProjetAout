package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private Grid grid;
    private List<Boat> boats;


    //constructeur général d'un player
    public Player(Grid grid, List<Integer> boatsLength){
        this.grid = grid;
        this.boats = new ArrayList<>();
        for (int size : boatsLength) {
            this.boats.add(new Boat(size));
        }

    }
    // second constructeur player (utilisé dans la grille txt en terminal)
    public Player(Grid grid){
        this.grid = grid;
        this.boats = boats;
    }



    //setter de liste de bateaux
    public void setBoats(ArrayList<Boat> boats){
        this.boats = boats;
    }



    //getter de la liste de bateaux
    public List<Boat> getBoats() {
        return boats;
    }


   //getter de grille
    public Grid getGrid() {
        return grid;
    }



    //methode isfinish de joueur, elle permet de retourner si la partie est finie(true) ou non(false)
    //en regardant si tout les bateaux du joueur sont coulés ou non
    public boolean isFinished() {
        for (Boat boat : this.boats) {
            if (!boat.isDead()) {
                return false;
            }
        }
        return true;

    }




    //methode play du joueur de type humain
    //utilisée dans la console, elle demande des coordonées tant que les coordonnées entre ne sont pas correctes
    //cad en dehors de la grille ou deja tiré a cet endroit
    public void play(Grid grid){
        System.out.println("Entrez la ligne");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("Entrez la colonne");
        int y = sc.nextInt();
        Game.clearConsole();


        while (!grid.fire(x, y)){
            System.out.println("Entrez une valeur correcte");
            System.out.println("Entrez la ligne");
            x = sc.nextInt();
            System.out.println("Entrez la colonne");
            y = sc.nextInt();
            Game.clearConsole();

        }
        //grid.showGrid();
    }


    //methode play de joueur utilisée dans la grille du fichier txt
    //en console
    public boolean play(Cell cell){
        int x = cell.getPosition().getRow();
        int y = cell.getPosition().getCol();
        //System.out.println("Vous avez tiré en x: "+x+", y: "+y);

        return grid.fire(x, y);
    }

    @Override
    public String toString() {
        return "Player{" +
                "boats=" + boats +
                '}';
    }
}
