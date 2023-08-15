package com.emile.projetaout.logiquetest;

import java.io.*;
import java.util.*;

public class Game {

    private int turn = 0;
    Random random = new Random();

    List<Player> playersList = new ArrayList<>();

    //constructeur de Game, vide, sert uniquement à initiliser
    public Game() {

    }


    //fonction main qui permet si executée de lancer une partie dans la console avec la grille définie dans le pdf
    //cela lance une vraie partie ou l'on peut jouer contre l'ia
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.loadGame("src\\main\\resources\\com\\emile\\projetaout\\grid.txt");
        System.out.println("DEBUT DU JEU");
        game.playGame();
    }


    //getter de turn
    public int getTurn() {
        return turn;
    }


    //methode qui permet de set les players (ici joueur vs ia), de les ajouter a la liste des joueurs et d'initialiser la partie
    public void setPlayerVsIa(int rows, int columns, List<Integer> boatsLength) {
        Player p1 = new Player(new Grid(rows, columns), boatsLength);
        Ia p2 = new Ia(new Grid(rows, columns), boatsLength);
        playersList.add(p1);
        playersList.add(p2);
        this.initGame();
    }

    //methode qui permet de set les players (ici smartia vs ia), de les ajouter a la liste des joueurs et d'initialiser la partie
    public void setSmartIaVsIa(int rows, int columns, List<Integer> boatsLength) {
        SmartIa p1 = new SmartIa(new Grid(rows, columns), boatsLength);
        Ia p2 = new Ia(new Grid(rows, columns), boatsLength);
        playersList.add(p1);
        playersList.add(p2);
        this.initGame();
    }


    //getter de la playerlist
    public List<Player> getPlayersList() {
        return playersList;
    }



    //methode qui permet d'initialiser la partie
    //pour chaque joueur, elle va placer sur la grille correspondante tout ses bateaux
    //en utilisant la methode placeboatPlayer
    private void initGame() {
        for (Player player : playersList) {
            placeBoatPlayer(player);
        }
    }


    //methode qui permet de placer tout les bateaux d'un joueur en donnant un nombre x et y aléatoire ansi qu'une direction aléatoire
    //et va ensuite appeller la methode placeboat de grid
    private void placeBoatPlayer(Player player){
        for (Boat boat : player.getBoats()) {
            while (true) {

                int row = random.nextInt(player.getGrid().getRows());
                int col = random.nextInt(player.getGrid().getColumns());


                Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                try {
                    player.getGrid().placeBoat(boat, row, col, direction);
                    break;
                } catch (IllegalArgumentException e) {

                }
            }
        }
    }



    //methode play qui permet d'augmenter le nombres de tour et ainsi a l'aide du modulo de choisir dans la liste
    // de joueurs a qui est le tour de jouer, attention , chaque joueur a sa propre methode play
    public void play() {
        Player player = playersList.get(turn % 2);
        Grid grid = playersList.get((turn + 1) % 2).getGrid();
        player.play(grid);
        turn++;
    }


    //methode play qui en fonction d'une cellule permet de faire la meme chose qu'au dessus mais d'appeller play du joueur avec un arguement Cell
    public boolean play(Cell cell) {
        boolean res = playersList.get((turn + 1) % 2).play(cell);
        if (res) turn++;
        return res;
    }

    //chat gpt
    //methode qui sert a clear la console
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



    //methode qui retourne vrai ou faux en fonction de si un des deux joueurs a finit la partie
    //utilise player.isfinish voir dans la classe Player

    public boolean isFinished() {
        return playersList.get(0).isFinished() || playersList.get(1).isFinished();
    }



    //methode qui permet tant que la partie n'est pas finie, de regarder si elle l'est et ensuite, si elle l'est
    //d'afficher la grille terminée ainsi que l'affcihage de qui a gagné

    public void playGame() {
        playersList.get(1).getGrid().cheatMode();

        while (!isFinished()) {
            play();
        }
        boolean p1Win = !playersList.get(1).isFinished();
        System.out.println("Fin du jeu");
        if (p1Win) {
            System.out.println("Le joueur a win");
            System.out.println("score : " +turn);
        } else {
            System.out.println("l'ordi a win");
            System.out.println("score : "+turn);
        }
    }



    //methode qui permet de load un fichier.txt et d'en creer une veritable grille de jeu
    //si l'on rentre une grille qui n'a pas le mm nombre de colonne/grille, une erreur se lance
    public void loadGame(String filename) throws Exception {
        int row = 0;
        int col = 0;
        ArrayList<String> list = new ArrayList<>();
        try {

            FileInputStream file = new FileInputStream(filename);
            Scanner scanner = new Scanner(file);


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


        for(int i=0; i<list.size(); i++){
            String string = list.get(i);



            for(int j=0; j<string.length(); j++){
                char c = string.charAt(j);
                if(c == 'X'){

                    if(grid.getCell(i, j).getBoat() == null){

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




                    }
                }
            }
        }

        launchLoadedGame(grid, listBoats);

    }


    //methode qui permet de lancer la grille de jeu chargée au dessus comme une vraie partie
    public void launchLoadedGame(Grid grid, ArrayList<Boat> boats){
        Player player = new Player(grid);
        player.setBoats(boats);
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

