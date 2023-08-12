package com.emile.projetaout.logiquetest;

import java.util.*;

public class Game {

    private int turn = 0;
    Random random = new Random();

    List<Player> playersList = new ArrayList<>();



    public Game() {

   }

   public void setPlayerVsIa(int rows, int columns, List<Integer> boatsLength){
       Player p1 = new Player(new Grid(rows, columns), boatsLength);
       Ia p2 = new Ia(new Grid(rows, columns), boatsLength);
       playersList.add(p1);
       playersList.add(p2);
       this.initGame();
   }

   public void setSmartIaVsIa(int rows, int columns, List<Integer> boatsLength){
       SmartIa p1 = new SmartIa(new Grid(rows, columns), boatsLength);
       Ia p2 = new Ia(new Grid(rows, columns), boatsLength);
       playersList.add(p1);
       playersList.add(p2);
       this.initGame();
   }


    public List<Player> getPlayersList() {
        return playersList;
    }

    private void initGame() {
        for(Player player: playersList){
            for (Boat boat : player.getBoats()) {
                while (true) {
                    // Choisissez une position de départ aléatoire.
                    int x = random.nextInt(player.getGrid().getRows());
                    int y = random.nextInt(player.getGrid().getColumns());

                    // Choisissez une direction aléatoire.
                    Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                    try {
                        player.getGrid().placeBoat(boat, x, y, direction);
                        break;
                    } catch (IllegalArgumentException e) {
                        // Si le boat ne peut pas être placé à la position/direction choisie, continuez à essayer avec une nouvelle position/direction.

                    }
                }
            }
        }
    }


    public void play(){
        Player player = playersList.get(turn % 2);
        Grid grid = playersList.get((turn+1) % 2).getGrid();
        player.play(grid);
        turn++;
    }

    public boolean play(Cell cell){
        boolean res = playersList.get((turn+1) %2).play(cell);
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

   public boolean isFinished(){
       return playersList.get(0).isFinished() || playersList.get(1).isFinished();
   }

   public static void main(String[] args){
       int rows = 15;
       int col = 7;
       Game game = new Game();
       game.setPlayerVsIa(rows , col , Arrays.asList(5, 4, 3, 3, 2));
       System.out.println("DEBUT DU JEU");
       game.playGame();
   }

   public void playGame(){
       playersList.get(1).getGrid().cheatMode();
       while(!isFinished()){
           play();
       }
       boolean p1Win = !playersList.get(1).isFinished();
       System.out.println("Fin du game");
       if (p1Win){
           System.out.println("Le joueur a win");
       } else{
           System.out.println("l'ordi a win");
       }
   }
}

