package com.emile.projetaout.logiquetest;

import java.util.List;
import java.util.Random;

public class Ia extends Player{
    int LastX;


    int LastY;

    Random random = new Random();

    public Ia(Grid grid, List<Integer> boatsLength) {
        super(grid, boatsLength);
    }

    @Override
    public void play(Grid grid){
        LastX = random.nextInt(grid.getRows());
        LastY = random.nextInt(grid.getColumns());


        while (!grid.fire(LastX, LastY)){
            LastX = random.nextInt(grid.getRows());
            LastY = random.nextInt(grid.getColumns());
        }

        System.out.println("l'ia baka a tirée en LastX: "+LastX+", LastY: "+LastY);
        grid.showGrid();
    }

    public int getLastX() {
        return LastX;
    }

    public int getLastY() {
        return LastY;
    }
}
