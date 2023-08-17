package com.emile.projetaout.logiquetest;

import java.util.*;

public class SmartIa extends Player {
    enum Mode {
        HUNT,
        TARGET
    }


    private Mode currentMode = Mode.HUNT;
    private Cell originHit = null;
    private Direction direction = null;

    //constructeur de l'ia intelligente
    public SmartIa(Grid grid, List<Integer> boatsLength) {
        super(grid, boatsLength);
        this.currentMode = Mode.HUNT;
        direction = null;
    }

    public void play(Grid grid) {
        if (currentMode == Mode.HUNT) {
            // Tirez au hasard (adaptez cette méthode à vos besoins)
            fireRandomly(grid);
        } else if (currentMode == Mode.TARGET) {
            target(grid);
        }
    }

    private void fireRandomly(Grid grid) {
        Random rand = new Random();
        int row, column;

        do {
            row = rand.nextInt(grid.getRows());
            column = rand.nextInt(grid.getColumns());
        } while (grid.getCell(row, column).isHit());

        if (grid.fire(row, column) && grid.getCell(row, column).getBoat() != null) {
            currentMode = Mode.TARGET;
            originHit = grid.getCell(row,column);
        }
    }

    private void target(Grid grid) {
        int row = originHit.getPosition().getRow();
        int col = originHit.getPosition().getCol();

        int rightCol = col + 1;
        int leftCol = col - 1;
        int botRow = row + 1;
        int topRow = row - 1;
        // On check si le bateau a deja ete detruit entierement ( pour les tailles 1 )
        if (originHit.getBoat().isDead()) {

            originHit = null;
            direction = null;
            currentMode = Mode.HUNT;
            return;
        }
        // On check si on a deja une direction
        if (direction == null) {
            if (grid.fire(row, rightCol)) {
                if (grid.getCell(row, rightCol).getBoat() != null){
                    direction = Direction.HORIZONTAL;
                }
            } else if (grid.fire(row, leftCol)) {
                if (grid.getCell(row, leftCol).getBoat() != null){
                    direction = Direction.HORIZONTAL;
                }
            } else if (grid.fire(botRow, col)) {

                if (grid.getCell(botRow, col).getBoat() != null){

                    direction = Direction.VERTICAL;
                }
            } else if (grid.fire(topRow, col)) {
                if (grid.getCell(topRow, col).getBoat() != null){

                    direction = Direction.VERTICAL;
                }
            }
        }else if (direction == Direction.HORIZONTAL) {
            // On tire a droite jusque ce que la derniere cellule touché ne soit pas un bateau ou que la bateau est détruit
            // Si c'est pas un bateau et que la bateau est pas détruit on fait la meme chose en partant de la gauche de la cellule d'origine
            int i = 1;
            int rowOrigin = originHit.getPosition().getRow();
            int colOrigin = originHit.getPosition().getCol();
            while (isValidPosition(rowOrigin, colOrigin + i, grid) &&
                    grid.getCell(rowOrigin, colOrigin+i).isHit() && grid.getCell(rowOrigin, colOrigin+i).getBoat() != null) {
                i++;
            }
            if (!isValidPosition(rowOrigin, colOrigin + i, grid) || ( grid.getCell(rowOrigin, colOrigin+i).isHit() && grid.getCell(rowOrigin, colOrigin+i).getBoat() == null)) {
                //Tirer a gauche de origin

                i = -1;
                while (isValidPosition(rowOrigin, colOrigin + i, grid) &&
                        grid.getCell(rowOrigin, colOrigin+i).isHit() && grid.getCell(rowOrigin, colOrigin+i).getBoat() != null) {
                    i--;
                }
            }
            grid.fire(row, colOrigin + i);
            if(originHit.getBoat().isDead()){


                currentMode = Mode.HUNT;
                originHit = null;
                direction = null;
            }
        }else if (direction == Direction.VERTICAL) {
            // On tire en bas jusque ce que la derniere cellule touché ne soit pas un bateau ou que la bateau est détruit
            // Si c'est pas un bateau et que la bateau est pas détruit on fait la meme chose en partant vers le haut de la cellule d'origine
            int i = 1;
            int rowOrigin = originHit.getPosition().getRow();
            int colOrigin = originHit.getPosition().getCol();
            while (isValidPosition(rowOrigin+i, colOrigin, grid) &&
                    grid.getCell(rowOrigin+i, colOrigin).isHit() && grid.getCell(rowOrigin+i, colOrigin).getBoat() != null) {
                i++;
            }
            if (!isValidPosition(rowOrigin+i, colOrigin, grid) || ( grid.getCell(rowOrigin+i, colOrigin).isHit() && grid.getCell(rowOrigin+i, colOrigin).getBoat() == null)) {
                //Tirer en haut

                i = -1;
                while (isValidPosition(rowOrigin+i, colOrigin, grid) &&
                        grid.getCell(rowOrigin+i, colOrigin).isHit() && grid.getCell(rowOrigin+i, colOrigin).getBoat() != null) {
                    i--;
                }
            }
            grid.fire(row+i, colOrigin);
            if(originHit.getBoat().isDead()){

                currentMode = Mode.HUNT;
                originHit = null;
                direction = null;
            }
        }



    }


    private boolean isValidPosition(int row, int col, Grid grid) {
        if (row < 0 || col < 0 || row >= grid.getRows() || col >= grid.getColumns()) {
            return false;
        }
        return true;
    }


}
