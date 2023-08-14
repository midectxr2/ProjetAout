package com.emile.projetaout.logiquetest;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SmartIa extends Player {

    boolean firstShot = true;
    int distanceMan;
    int boatLength;


    Random random = new Random();
    int startCol;
    int startRow;

    public SmartIa(Grid grid, List<Integer> boatsLength) {
        super(grid, boatsLength);
    }

    @Override
    public void play(Grid grid){
        if(firstShot) {
            startCol = random.nextInt(grid.getRows());
            startRow = random.nextInt(grid.getColumns());


            while (!grid.fire(startCol, startRow)) {
                startCol = random.nextInt(grid.getRows());
                startRow = random.nextInt(grid.getColumns());
            }
            Cell cell = grid.getCell(startCol, startRow);
            distanceMan = grid.distanceMan(cell);
            System.out.println("distance man = "+distanceMan);
            boatLength = grid.lengthNearestBoat();
            firstShot = false;
        }
        else{
            if(distanceMan == 0){
                startCol = startCol+1;
                grid.fire(startCol, startRow);
            }
            else{
                startCol = startCol + distanceMan;
                grid.fire(startCol, startRow);
            }
        }








        System.out.println("l'ia intelligente a tirée en x: "+startCol+", y: "+startRow);

    }
}
