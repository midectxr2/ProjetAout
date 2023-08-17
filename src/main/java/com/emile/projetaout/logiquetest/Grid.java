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
    private int length;



    //constructeur de grille, creer une matrice de cellules
    public Grid(int columns, int rows){
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }

        //this.showGrid();
    }


    //getter de rows
    public int getRows() {
        return rows;
    }



    //getter de columns
    public int getColumns() {
        return columns;
    }



    //getter de cell en fonction d'un x et y
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }




    //fonction place boat, permet de place un bateau donné sur la grille
    //algo developpé dans le rapport
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

                    throw new IllegalArgumentException("Boat chevauche un autre boat.");
                }
            } else { // Direction.VERTICAL

                if (cells[row +i][col].getBoat() != null) {

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


    //methode qui permet de regarde au alentour d'une cellule (d'un x et d'un y), à 1 de distance pour savoir si
    //il y a un bateau ou non trop proche pour pouvoir en placer un autre
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


    //methode qui appelle valid point du dessous
    public boolean isValidPoint(Position point2D){
        return isValidPoint(point2D.getRow(), point2D.getCol());
    }


    //methode qui regarde si le point (x et y) donné est dans la grille ou non, retourne vrai ou faux en fonction
    public boolean isValidPoint(int row, int col){
        return row >=0 && row < getRows() && col >=0 && col < getColumns();
    }



    //methode fire de grille qui permet en fonction d'un x et d'un y, de regarder si ils sont dans la grille et si oui, appelle la fonction
    //fireAt de cellule
    public boolean fire(int row, int col) {

        if (row < 0 || col < 0 || row >= rows || col >= columns) {
            return false;
        }

        Cell cell = cells[row][col];

        return cell.fireAt();


    }

    //methode show grid , permet d'afficher la grille dans le terminal, (choix personnel)
    public void showGrid() {

        for (int i = 0; i < columns; i++){
            System.out.print(" " + i );
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i);
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isHit()) {

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


    //methode cheat mode utilisée pour le début de la grille en console, elle affiche les bateaux de l'adversaire
    public void cheatMode() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {


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



    //methode qui permet d'en fonction d'une cellule de calculer la distance entre cette celulle et le bateaux le plus proches
    // et change aussi la taille du bateaux le plus proche si plusieurs bateaux sont  a une meme distance

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



    //methode qui retourne la longueur du bateau le plus proche
    public int lengthNearestBoat(){
        return getLength();
    }


    //getter de length (taille bateau)
    public int getLength() {
        return length;
    }





    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid)) return false;
        Grid grid = (Grid) o;
        return rows == grid.rows && columns == grid.columns && length == grid.length && Arrays.equals(cells, grid.cells);
    }


    //methode normalement utilisée dans les tests, voir rapport explications.
    @Override
    public int hashCode() {
        int result = Objects.hash(rows, columns, length);
        result = 31 * result + Arrays.hashCode(cells);
        return result;
    }
}

