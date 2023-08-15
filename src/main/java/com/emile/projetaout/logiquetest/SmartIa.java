package com.emile.projetaout.logiquetest;

import java.util.*;

public class SmartIa extends Player {

    ArrayList<Cell> cellArrayList = new ArrayList<>();


    Random random = new Random();

    //constructeur de l'ia intelligente
    public SmartIa(Grid grid, List<Integer> boatsLength) {
        super(grid, boatsLength);
    }



    //methode play de l'ia intelligente, decrite plus précisement dans le rapport
    @Override
    public void play(Grid grid) {
        if (cellArrayList.size() == 0) {
            int row = (int) grid.getRows() / 2;
            int col = (int) grid.getColumns() / 2;
            Cell cell = grid.getCell(row, col);
            int distanceMan = grid.distanceMan(cell);
            System.out.println("distance man: "+distanceMan);
            int boatLength = grid.lengthNearestBoat();
            System.out.println("lg bateau le plus proche: " +boatLength);
            cellArrayList.add(cell);
            grid.fire(row, col);
        } else {

            int lg = cellArrayList.size();
            Cell cell = cellArrayList.get(lg - 1);
            int distance = grid.distanceMan(cell);
            int length = grid.lengthNearestBoat();

            System.out.println("distance man: "+distance);
            System.out.println("lg bateau le plus proche: "+ length);

            if (distance == 0) {
                int cellRow = cell.getPosition().getRow();
                int cellCol = cell.getPosition().getCol();

                int rand = random.nextInt(2);
                if (rand == 0) {
                    if (grid.isValidPoint(cellRow - 1, cellCol)) {
                        if (grid.fire(cellRow - 1, cellCol)) {
                            Cell cell1 = grid.getCell(cellRow - 1, cellCol);
                            cellArrayList.add(cell1);
                        } else {
                            int LastX = random.nextInt(grid.getRows());
                            int LastY = random.nextInt(grid.getColumns());


                            while (!grid.fire(LastX, LastY)) {
                                LastX = random.nextInt(grid.getRows());
                                LastY = random.nextInt(grid.getColumns());
                            }
                            Cell cell1 = grid.getCell(LastX, LastY);
                            cellArrayList.add(cell1);
                        }
                    } else {
                        int LastX = random.nextInt(grid.getRows());
                        int LastY = random.nextInt(grid.getColumns());


                        while (!grid.fire(LastX, LastY)) {
                            LastX = random.nextInt(grid.getRows());
                            LastY = random.nextInt(grid.getColumns());
                        }
                        Cell cell1 = grid.getCell(LastX, LastY);
                        cellArrayList.add(cell1);
                    }
                } else {
                    if (grid.isValidPoint(cellRow, cellCol + 1)) {
                        if (grid.fire(cellRow, cellCol + 1)) {
                            Cell cell1 = grid.getCell(cellRow, cellCol + 1);
                            cellArrayList.add(cell1);
                        } else {
                            int LastX = random.nextInt(grid.getRows());
                            int LastY = random.nextInt(grid.getColumns());


                            while (!grid.fire(LastX, LastY)) {
                                LastX = random.nextInt(grid.getRows());
                                LastY = random.nextInt(grid.getColumns());
                            }
                            Cell cell1 = grid.getCell(LastX, LastY);
                            cellArrayList.add(cell1);
                        }
                    } else {
                        int LastX = random.nextInt(grid.getRows());
                        int LastY = random.nextInt(grid.getColumns());


                        while (!grid.fire(LastX, LastY)) {
                            LastX = random.nextInt(grid.getRows());
                            LastY = random.nextInt(grid.getColumns());
                        }
                        Cell cell1 = grid.getCell(LastX, LastY);
                        cellArrayList.add(cell1);
                    }
                }
            } else {
                int cellRow = cell.getPosition().getRow();
                int cellCol = cell.getPosition().getCol();

                int rand = random.nextInt(2);
                if (rand == 0) {
                    if (grid.isValidPoint(cellRow + distance, cellCol)) {
                        if (grid.fire(cellRow + distance, cellCol)) {
                            Cell cell1 = grid.getCell(cellRow + distance, cellCol);
                            cellArrayList.add(cell1);
                        } else {
                            int LastX = random.nextInt(grid.getRows());
                            int LastY = random.nextInt(grid.getColumns());


                            while (!grid.fire(LastX, LastY)) {
                                LastX = random.nextInt(grid.getRows());
                                LastY = random.nextInt(grid.getColumns());
                            }
                            Cell cell1 = grid.getCell(LastX, LastY);
                            cellArrayList.add(cell1);
                        }
                    } else {
                        int LastX = random.nextInt(grid.getRows());
                        int LastY = random.nextInt(grid.getColumns());


                        while (!grid.fire(LastX, LastY)) {
                            LastX = random.nextInt(grid.getRows());
                            LastY = random.nextInt(grid.getColumns());
                        }
                        Cell cell1 = grid.getCell(LastX, LastY);
                        cellArrayList.add(cell1);
                    }
                } else {
                    if (grid.isValidPoint(cellRow, cellCol - distance)) {
                        if (grid.fire(cellRow, cellCol - distance)) {
                            Cell cell1 = grid.getCell(cellRow, cellCol - distance);
                            cellArrayList.add(cell1);
                        } else {
                            int LastX = random.nextInt(grid.getRows());
                            int LastY = random.nextInt(grid.getColumns());


                            while (!grid.fire(LastX, LastY)) {
                                LastX = random.nextInt(grid.getRows());
                                LastY = random.nextInt(grid.getColumns());
                            }
                            Cell cell1 = grid.getCell(LastX, LastY);
                            cellArrayList.add(cell1);
                        }
                    } else {
                        int LastX = random.nextInt(grid.getRows());
                        int LastY = random.nextInt(grid.getColumns());


                        while (!grid.fire(LastX, LastY)) {
                            LastX = random.nextInt(grid.getRows());
                            LastY = random.nextInt(grid.getColumns());
                        }
                        Cell cell1 = grid.getCell(LastX, LastY);
                        cellArrayList.add(cell1);
                    }
                }
            }
        }
        System.out.println("l'ia intelligente a tiree");
    }
}
