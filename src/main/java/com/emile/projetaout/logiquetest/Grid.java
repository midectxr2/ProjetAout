package com.emile.projetaout.logiquetest;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Grid {
    private int rows;
    private int columns;
    private Cell[][] cells;

    private ArrayList<Boat> boatArrayList;
    
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
        // Vérifiez si la position de départ est dans la grille.
        if (row < 0 || col < 0 || row >= rows || col >= columns) {
            throw new IllegalArgumentException("Position de départ hors de la grille.");
        }

        // Vérifiez si le boat peut être placé dans la direction donnée à partir de la position de départ.
        if (direction == Direction.HORIZONTAL && col + boat.getLength() > columns || direction == Direction.VERTICAL && row + boat.getLength() > rows) {
            throw new IllegalArgumentException("Boat trop grand pour être placé à cette position dans cette direction.");
        }


        // Vérifiez si le boat chevauche un autre boat.
        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                //if (cells[x + i][y].getBoat() != null) {
                if (cells[row][col +i].getBoat() != null) {
                    System.out.println("Boat chevauve un autre boat");
                    throw new IllegalArgumentException("Boat chevauche un autre boat.");
                }
            } else { // Direction.VERTICAL
                //if (cells[x][y + i].getBoat() != null) {
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




        // Placez le boat sur la grille.
        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                cells[row][col+i].setBoat(boat);
                boat.addCell(cells[row][col+i]);

            } else { // Direction.VERTICAL
                cells[row+i][col].setBoat(boat);
                boat.addCell(cells[row+i][col]);
            }
        }
    }

    public Cell[] getNeihbour(int row, int col){
        Position[] points = new Position[]{
                /*
                new Point2D(x-1, y),
                new Point2D(x-1, y-1),
                new Point2D(x, y-1),
                new Point2D(x-1, y+1),
                new Point2D(x+1, y),
                new Point2D(x, y+1),
                new Point2D(x+1, y+1),
                new Point2D(x+1, y-1),*/
                new Position(col, row -1),
                new Position(col -1, row -1),
                new Position(col -1, row),
                new Position(col +1, row -1 ),
                new Position(col, row +1),
                new Position(col +1, row),
                new Position(col +1, row +1),
                new Position(col -1, row +1),
        };
        List<Cell> neighbors = new ArrayList<Cell>();
        for(Position p: points){
            if(isValidPoint(p)) {
                System.out.println("row:" + p.getRow() + "col:" + p.getCol());
                neighbors.add(cells[ p.getRow()][ p.getCol()]);
            }
        }
        return neighbors.toArray(new Cell[0]);

    }

    private boolean isValidPoint(Position point2D){
        return isValidPoint(point2D.getRow(), point2D.getCol());
    }

    private boolean isValidPoint(double row, double col){
        row = (int) row;
        col = (int) col;
        return row >=0 && row < getRows() && col >=0 && col < getColumns();
    }


    public boolean fire(int x, int y) {

        // Vérifiez si la position est dans la grille.
        if (x < 0 || y < 0 || x >= rows || y >= columns) {
            return false;
        }

        Cell cell = cells[x][y];

        // Marquez la cell comme étant touchée.
        // Return false et ne tire pas sur la cell si la cell a deja ete toucher

        return cell.fireAt();

        // Si la cell contient un bateau, informez le bateau qu'il a été touché.
        /*Boat bateau = cell.getBateau();
        if (bateau != null) {
            System.out.println("Boat touche");
            bateau.toucher();
        }*/
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






}

