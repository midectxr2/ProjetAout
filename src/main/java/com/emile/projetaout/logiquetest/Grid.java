package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class Grid {
    private int rows;
    private int columns;
    private Cell[][] cells;


    
    public Grid(int columns, int rows){
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }

        this.showGrid();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void placeBoat(Boat boat, int row, int col, Direction direction) {

        if (row < 0 || col < 0 || row >= rows || col >= columns) {
            throw new IllegalArgumentException("Position de départ hors de la grille.");
        }

        if (direction == Direction.HORIZONTAL && col + boat.getLength() > columns || direction == Direction.VERTICAL && row + boat.getLength() > rows) {
            throw new IllegalArgumentException("Boat trop grand pour être placé à cette position dans cette direction.");
        }



        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {

                if (cells[row][col +i].getBoat() != null) {
                    System.out.println("Boat chevauve un autre boat");
                    throw new IllegalArgumentException("Boat chevauche un autre boat.");
                }
            } else { // Direction.VERTICAL

                if (cells[row +i][col].getBoat() != null) {
                    System.out.println("Boat chevauve un autre boat");
                    throw new IllegalArgumentException("Boat chevauche un autre boat.");
                }
            }
        }


        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                Cell[] neighbors = getNeihbour(row, col+i);
                for(Cell cell : neighbors){
                    if(cell.getBoat() != null){
                        throw new IllegalArgumentException(("Boat trop proche"));
                    }
                }
            }
            else{
                Cell[] neighbors = getNeihbour(row+i, col);
                for(Cell cell : neighbors){
                    if(cell.getBoat() != null){
                        throw new IllegalArgumentException(("Boat trop proche"));
                    }
                }

            }

        }

        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                cells[row][col+i].setBoat(boat);
                boat.addCell(cells[row][col+i]);

            } else {
                cells[row+i][col].setBoat(boat);
                boat.addCell(cells[row+i][col]);
            }
        }
    }

    public Cell[] getNeihbour(int row, int col){
        Position[] points = new Position[]{
                new Position(row, col -1),
                new Position(row -1, col -1),
                new Position(row -1, col),
                new Position(row +1, col -1 ),
                new Position(row, col +1),
                new Position(row +1, col),
                new Position(row +1, col +1),
                new Position(row -1, col +1),
        };
        List<Cell> neighbors = new ArrayList<Cell>();


        for(Position p: points){
            if(isValidPoint(p)) {

                neighbors.add(cells[ p.getRow()][ p.getCol()]);
            }
        }
        return neighbors.toArray(new Cell[0]);

    }

    private boolean isValidPoint(Position point2D){
        return isValidPoint(point2D.getRow(), point2D.getCol());
    }

    private boolean isValidPoint(int row, int col){
        return row >=0 && row < getRows() && col >=0 && col < getColumns();
    }


    public boolean fire(int x, int y) {

        if (x < 0 || y < 0 || x >= rows || y >= columns) {
            return false;
        }

        Cell cell = cells[x][y];

        return cell.fireAt();


    }

    public void showGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isHit()) {
                    //System.out.println(cells[i][j].toString());

                    if (cells[i][j].getBoat() != null) {
                        System.out.print("O "); // Tir réussi
                    } else {
                        System.out.print("X "); // Tir manqué
                    }
                } else {
                    System.out.print("~ "); // Cell non touchée
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void cheatMode() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //System.out.println(cells[i][j].toString());

                if (cells[i][j].getBoat() != null) {
                    System.out.print("O "); // Tir réussi
                } else {
                    System.out.print("X "); // Tir manqué
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    private int length;

    public int distanceMan(Cell cell){
        ArrayList<Cell> cellArrayList = new ArrayList<>();
        Position pos = cell.getPosition();
        int posX = pos.getRow();
        int posY = pos.getCol();



        for(int i=0; i<getRows(); i++){
            for(int j=0; j<getColumns();j++){
                Cell cell1 = getCell(i, j);
                if(cell1.getBoat() != null){
                    cellArrayList.add(cell1);
                }
            }
        }


        Cell cell1 = cellArrayList.get(0);
        int tmp = getColumns() + getRows()+1;
        for(Cell c: cellArrayList){
            Position position = c.getPosition();

            int posX_cell = position.getRow();
            int posY_cell = position.getCol();
            int res = abs(posX_cell - posX) + abs(posY_cell - posY);



            if(res < tmp){
                tmp = res;
                cell1 = c;
            }
            if(res == tmp){
                if(cell1.getBoat().getLength() >= c.getBoat().getLength()){
                    cell1 = c;
                }
            }

        }

        length = cell1.getBoat().getLength();
        return tmp;
    }

    public int lengthNearestBoat(){
        return getLength();
    }

    public int getLength() {
        return length;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid)) return false;
        Grid grid = (Grid) o;
        return rows == grid.rows && columns == grid.columns && length == grid.length && Arrays.equals(cells, grid.cells);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, columns, length);
        result = 31 * result + Arrays.hashCode(cells);
        return result;
    }
}

