package com.emile.projetaout.logiquetest;

import java.util.List;
import java.util.Random;

public class Ia extends Player{

    Random random = new Random();



    //constructeur ia basique
    public Ia(Grid grid, List<Integer> boatsLength) {
        super(grid, boatsLength);
    }


    //second constructeur ia(pour la grille txt en console)
    public Ia(Grid grid){
        super(grid);
    }




    //methode play du joueur de type IA basique, elle permet a chaque tour de tirer au hasard sur une case ou elle n'a pas encore tiré
    //ainsi que d'affciher la grille
    @Override
    public void play(Grid grid){
        int row = random.nextInt(grid.getRows());
        int col = random.nextInt(grid.getColumns());


        while (!grid.fire(row, col)){
            row = random.nextInt(grid.getRows());
            col = random.nextInt(grid.getColumns());
        }

        System.out.println("l'ia basique a tirée en LastX: "+LastX+", LastY: "+LastY);
        grid.showGrid();
    }

}
