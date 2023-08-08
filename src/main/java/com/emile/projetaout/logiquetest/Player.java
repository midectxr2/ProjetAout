package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;

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
}
