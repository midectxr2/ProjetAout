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
        int LastX = random.nextInt(grid.getRows());
        int LastY = random.nextInt(grid.getColumns());


        while (!grid.fire(LastX, LastY)){
            LastX = random.nextInt(grid.getRows());
            LastY = random.nextInt(grid.getColumns());
        }

        System.out.println("l'ia baka a tirée en LastX: "+LastX+", LastY: "+LastY);
        grid.showGrid();
    }

}
