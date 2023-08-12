package com.emile.projetaout.logiquetest;

import java.util.List;
import java.util.Random;

public class Ia extends Player{

    Random random = new Random();

    public Ia(Grid grid, List<Integer> boatsLength) {
        super(grid, boatsLength);
    }

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
