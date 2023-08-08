package com.emile.projetaout.logiquetest;

import java.util.*;

public class Game {
    private Player p1;
    private Player p2;
    private boolean myTurn = true;
    Random random = new Random();


    public Game(int rows, int columns, List<Integer> boatsLength) {
        p1 = new Player(new Grid(rows, columns), boatsLength);
        p2 = new Player(new Grid(rows, columns), boatsLength);

       this.initGame();
   }


    public Player getP1() {
        return this.p1;
    }

    public Player getP2() {
        return this.p2;
    }

    private void initGame() {

        for (Boat boat : p1.getBoats()) {
            while (true) {
                // Choisissez une position de départ aléatoire.
                int x = random.nextInt(p1.getGrid().getRows());
                int y = random.nextInt(p1.getGrid().getColumns());

                // Choisissez une direction aléatoire.
                Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                try {
                    p1.getGrid().placeBoat(boat, x, y, direction);
                    break;
                } catch (IllegalArgumentException e) {
                    // Si le boat ne peut pas être placé à la position/direction choisie, continuez à essayer avec une nouvelle position/direction.
                }
            }
        }
        for (Boat boat : p2.getBoats()) {
            while (true) {
                // Choisissez une position de départ aléatoire.
                int x = random.nextInt(p2.getGrid().getRows());
                int y = random.nextInt(p2.getGrid().getColumns());

                // Choisissez une direction aléatoire.
                Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                try {
                    p2.getGrid().placeBoat(boat, x, y, direction);
                    break;
                } catch (IllegalArgumentException e) {
                    // Si le boat ne peut pas être placé à la position/direction choisie, continuez à essayer avec une nouvelle position/direction.
                }
            }
        }


    }

    public void playP1Turn() {
        //Demande a l'utilisateur les coordonnées
        System.out.println("Entrez la ligne");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("Entrez la colonne");
        int y = sc.nextInt();
        clearConsole();
        System.out.println("Vous avez tiré en x: "+x+", y: "+y);

        while (!this.p2.getGrid().fire(x, y)){
            System.out.println("Entrez une valeur correcte");
            System.out.println("Entrez la ligne");
            x = sc.nextInt();
            System.out.println("Entrez la colonne");
             y = sc.nextInt();
            clearConsole();
            System.out.println("Vous avez tiré en x: "+x+", y: "+y);
        }

        this.p2.getGrid().showGrid();
        myTurn = !myTurn;


    }

    public void playP2Turn() {
        int x = random.nextInt(p1.getGrid().getRows());
        int y = random.nextInt(p1.getGrid().getColumns());


        while (!this.p1.getGrid().fire(x, y)){
             x = random.nextInt(p1.getGrid().getRows());
             y = random.nextInt(p1.getGrid().getColumns());
        }

        System.out.println("l'ordi a tiré en x: "+x+", y: "+y);
        this.p1.getGrid().showGrid();
        myTurn = !myTurn;

    }

    public  void clearConsole() {
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
       return p1.isFinished() || p2.isFinished();
   }

   public static void main(String[] args){
       int rows = 10;

       int col = 10;

       Game game = new Game(rows , col , Arrays.asList(5, 4, 3, 3, 2));
       System.out.println("DEBUT DU JEU");
       game.getP2().getGrid().cheatMode();
       while(!game.isFinished()){
            if (game.myTurn){
                game.playP1Turn();
            }else{
                game.playP2Turn();
            }
       }
       boolean p1Win = !game.p1.isFinished();
       System.out.println("Fin du game");
       if (p1Win){
           System.out.println("Le joueur a win");
       } else{
           System.out.println("l'ordi a win");
       }
   }
}

