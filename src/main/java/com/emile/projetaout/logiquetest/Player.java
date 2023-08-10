package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private Grid grid;
    private List<Boat> boats;


    public Player(Grid grid, List<Integer> boatsLength){
        this.grid = grid;
        this.boats = new ArrayList<>();
        for (int size : boatsLength) {
            this.boats.add(new Boat(size));
        }

    }

    public List<Boat> getBoats() {
        return boats;
    }

    public Grid getGrid() {
        return grid;
    }

    public boolean isFinished() {
        for (Boat boat : this.boats) {
            if (!boat.isAlive()) {
                return false;
            }
        }
        return true;
    }


    public void play(Grid grid){
        System.out.println("Entrez la ligne");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("Entrez la colonne");
        int y = sc.nextInt();
        Game.clearConsole();
        System.out.println("Vous avez tiré en x: "+x+", y: "+y);

        while (!grid.fire(x, y)){
            System.out.println("Entrez une valeur correcte");
            System.out.println("Entrez la ligne");
            x = sc.nextInt();
            System.out.println("Entrez la colonne");
            y = sc.nextInt();
            Game.clearConsole();
            System.out.println("Vous avez tiré en x: "+x+", y: "+y);
        }
        grid.showGrid();

    }

    public boolean play(Cell cell){
        int x = cell.getPosition().getX();
        int y = cell.getPosition().getY();
        System.out.println("Vous avez tiré en x: "+x+", y: "+y);

        return grid.fire(x, y);
    }



}
