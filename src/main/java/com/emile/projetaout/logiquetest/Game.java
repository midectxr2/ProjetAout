package com.emile.projetaout.logiquetest;

import java.io.*;
import java.util.*;

public class Game {

    private int turn = 0;
    Random random = new Random();

    List<Player> playersList = new ArrayList<>();


    public Game() {

    }
    public static void main(String[] args) throws Exception {
        int rows = 10;
        int col = 10;
        Game game = new Game();
        game.loadGame("C:\\Users\\emile\\IdeaProjects\\ProjetAoutNEWNEW\\src\\main\\java\\com\\emile\\projetaout\\logiquetest\\grid.txt");
        //game.setPlayerVsIa(rows, col, Arrays.asList(5, 4, 3, 3, 2));
        System.out.println("DEBUT DU JEU");
        game.playGame();
    }

    public void setPlayerVsIa(int rows, int columns, List<Integer> boatsLength) {
        Player p1 = new Player(new Grid(rows, columns), boatsLength);
        Ia p2 = new Ia(new Grid(rows, columns), boatsLength);
        playersList.add(p1);
        playersList.add(p2);
        this.initGame();
    }

    public void setSmartIaVsIa(int rows, int columns, List<Integer> boatsLength) {
        Ia p1 = new Ia(new Grid(rows, columns), boatsLength);
        Ia p2 = new Ia(new Grid(rows, columns), boatsLength);
        playersList.add(p1);
        playersList.add(p2);
        this.initGame();
    }


    public List<Player> getPlayersList() {
        return playersList;
    }

    private void initGame() {
        for (Player player : playersList) {
            placeBoatPlayer(player);
        }
    }

    private void placeBoatPlayer(Player player){
        for (Boat boat : player.getBoats()) {
            while (true) {
                // Choisissez une position de départ aléatoire.
                int row = random.nextInt(player.getGrid().getRows());
                int col = random.nextInt(player.getGrid().getColumns());

                // Choisissez une direction aléatoire.
                Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                try {
                    player.getGrid().placeBoat(boat, row, col, direction);
                    break;
                } catch (IllegalArgumentException e) {
                    // Si le boat ne peut pas être placé à la position/direction choisie, continuez à essayer avec une nouvelle position/direction.

                }
            }
        }
    }


    public void play() {
        Player player = playersList.get(turn % 2);
        Grid grid = playersList.get((turn + 1) % 2).getGrid();
        player.play(grid);
        turn++;
    }

    public boolean play(Cell cell) {
        boolean res = playersList.get((turn + 1) % 2).play(cell);
        if (res) turn++;
        return res;
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Commande pour Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Commande pour UNIX
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Gérer l'exception
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        return playersList.get(0).isFinished() || playersList.get(1).isFinished();
    }



    public void playGame() {
        playersList.get(1).getGrid().cheatMode();
        while (!isFinished()) {
            play();
        }
        boolean p1Win = !playersList.get(1).isFinished();
        System.out.println("Fin du game");
        if (p1Win) {
            System.out.println("Le joueur a win");
        } else {
            System.out.println("l'ordi a win");
        }
    }


    public void loadGame(String filename) throws Exception {
        int row = 0;
        int col = 0;
        ArrayList<String> list = new ArrayList<>();
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream(filename);
            Scanner scanner = new Scanner(file);


            //renvoie true s'il y a une autre ligne à lire
            while (scanner.hasNextLine()) {
                row++;
                String line =  scanner.nextLine();
                list.add(line);
                if(   col > 0 && col != line.length()  ){
                    throw new Exception("Colonnes/lignes de taille différentes");
                }
                col = line.length();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        Grid grid = new Grid(row, col);
        ArrayList<Boat> listBoats = new ArrayList<>();
        System.out.println(list);

        for(int i=0; i<list.size(); i++){
            String string = list.get(i);

            System.out.println(string);

            for(int j=0; j<string.length(); j++){
                char c = string.charAt(j);
                if(c == 'X'){
                    System.out.println(" c un bateau, row: "+i+" ,col: "+j);
                    if(grid.getCell(i, j).getBoat() == null){
                        System.out.println("pas encore ajouté ce bateau alala il est beau ce bateau");
                        char bottom = list.get(i+1).charAt(j);
                        char right = list.get(i).charAt(j+1);
                        int sizeBoat = 1;
                        Direction direction = Direction.HORIZONTAL;
                        if(bottom == 'X'){
                            sizeBoat++;
                            direction = Direction.VERTICAL;

                            while(list.get(i + sizeBoat).charAt(j) == 'X'){
                                sizeBoat++;
                            }

                        }

                        if(right == 'X'){
                            sizeBoat++;
                            direction = Direction.HORIZONTAL;

                            while(list.get(i).charAt(j+sizeBoat) == 'X'){
                                sizeBoat++;
                            }

                        }
                        Boat boat = new Boat(sizeBoat);
                        grid.placeBoat(boat, i, j, direction);
                        listBoats.add(boat);
                        System.out.println("ajout du bateau" + boat);



                    }
                }
            }
        }

        launchLoadedGame(grid, listBoats);

    }


    public void launchLoadedGame(Grid grid, ArrayList<Boat> boats){
        Player player = new Player(grid);
        player.setBoats(boats);
        System.out.println(player.getBoats().toString());
        Ia ia = new Ia(new Grid(grid.getRows(), grid.getColumns()));

        ArrayList<Boat> boatsIA = new ArrayList<>();
        for (Boat boat: boats){
            boatsIA.add(new Boat(boat.getLength()));
        }
        ia.setBoats(boatsIA);
        placeBoatPlayer(ia);
        playersList.add(player);
        playersList.add(ia);
    }
}

