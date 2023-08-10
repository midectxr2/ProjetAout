package com.emile.projetaout.logiquetest;

import javafx.geometry.Point2D;

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

    public void placeBoat(Boat boat, int x, int y, Direction direction) {
        // Vérifiez si la position de départ est dans la grille.
        if (x < 0 || y < 0 || x >= rows || y >= columns) {
            throw new IllegalArgumentException("Position de départ hors de la grille.");
        }

        // Vérifiez si le boat peut être placé dans la direction donnée à partir de la position de départ.
        if (direction == Direction.HORIZONTAL && x + boat.getLength() > columns || direction == Direction.VERTICAL && y + boat.getLength() > rows) {
            throw new IllegalArgumentException("Boat trop grand pour être placé à cette position dans cette direction.");
        }

        // Vérifiez si le boat chevauche un autre boat.
        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                //if (cells[x + i][y].getBoat() != null) {
                if (cells[y][x+i].getBoat() != null) {
                    throw new IllegalArgumentException("Boat chevauche un autre boat.");
                }
            } else { // Direction.VERTICAL
                //if (cells[x][y + i].getBoat() != null) {
                if (cells[y+i][x].getBoat() != null) {
                    throw new IllegalArgumentException("Boat chevauche un autre boat.");
                }
            }
        }


        for (int i = 0; i < boat.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                Cell[] neighbors = getNeihbour(x+i, y);
                for(Cell cell : neighbors){
                    if(cell.getBoat() != null){
                        throw new IllegalArgumentException(("Boat trop proche"));
                    }
                }
            }
            else{
                Cell[] neighbors = getNeihbour(x, y+i);
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
                cells[x + i][y].setBoat(boat);
                boat.addCell(cells[x + i][y]);

            } else { // Direction.VERTICAL
                cells[x][y + i].setBoat(boat);
                boat.addCell(cells[x][y+i]);
            }
        }
    }

    public Cell[] getNeihbour(int x, int y){
        Point2D[] points = new Point2D[]{
                /*
                new Point2D(x-1, y),
                new Point2D(x-1, y-1),
                new Point2D(x, y-1),
                new Point2D(x-1, y+1),
                new Point2D(x+1, y),
                new Point2D(x, y+1),
                new Point2D(x+1, y+1),
                new Point2D(x+1, y-1),*/
                new Point2D(y, x-1),
                new Point2D(y-1, x-1),
                new Point2D(y-1, x),
                new Point2D(y+1,x-1 ),
                new Point2D(y, x+1),
                new Point2D(y+1, x),
                new Point2D(y+1, x+1),
                new Point2D(y-1, x+1),
        };
        List<Cell> neighbors = new ArrayList<Cell>();
        for(Point2D p: points){
            if(isValidPoint(p)) {
                System.out.println("X:" + p.getX() + "Y:" + p.getY());
                System.out.println("X int " + (int) p.getX() + "Y int: " + (int) p.getY());
                neighbors.add(cells[(int) p.getY()][(int) p.getX()]);
            }
        }
        return neighbors.toArray(new Cell[0]);

    }

    private boolean isValidPoint(Point2D point2D){
        return isValidPoint(point2D.getX(), point2D.getY());
    }

    private boolean isValidPoint(double x, double y){
        x = (int) x;
        y = (int) y;
        return x>=0 && x< getColumns() && y>=0 && y< getRows();
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
        int posX = pos.getX();
        int posY = pos.getY();



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

            int posX_cell = position.getX();
            int posY_cell = position.getY();
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

