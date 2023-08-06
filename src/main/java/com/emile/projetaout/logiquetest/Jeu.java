package com.emile.projetaout.logiquetest;

import java.util.*;

public class Jeu {
    private Player p1;
    private Player p2;
    private boolean myTurn = true;
    Random random = new Random();


    public Jeu(int lignes, int colonnes, List<Integer> taillesBateaux) {
        p1 = new Player(new Grille(lignes, colonnes),  taillesBateaux);
        p2 = new Player(new Grille(lignes, colonnes),  taillesBateaux);

       this.initialiserJeu();
   }

    public Jeu(int taille, List<Integer> taillesBateaux){
       this(taille, taille, taillesBateaux);
    }

    public Player getP1() {
        return this.p1;
    }

    public Player getP2() {
        return this.p2;
    }

    private void initialiserJeu() {

        for (Bateau bateau : p1.getBateaux()) {
            while (true) {
                // Choisissez une position de départ aléatoire.
                int x = random.nextInt(p1.getGrille().getLignes());
                int y = random.nextInt(p1.getGrille().getColonnes());

                // Choisissez une direction aléatoire.
                Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                try {
                    p1.getGrille().placerBateau(bateau, x, y, direction);
                    break;
                } catch (IllegalArgumentException e) {
                    // Si le bateau ne peut pas être placé à la position/direction choisie, continuez à essayer avec une nouvelle position/direction.
                }
            }
        }
        for (Bateau bateau : p2.getBateaux()) {
            while (true) {
                // Choisissez une position de départ aléatoire.
                int x = random.nextInt(p2.getGrille().getLignes());
                int y = random.nextInt(p2.getGrille().getColonnes());

                // Choisissez une direction aléatoire.
                Direction direction = random.nextBoolean() ? Direction.HORIZONTAL : Direction.VERTICAL;

                try {
                    p2.getGrille().placerBateau(bateau, x, y, direction);
                    break;
                } catch (IllegalArgumentException e) {
                    // Si le bateau ne peut pas être placé à la position/direction choisie, continuez à essayer avec une nouvelle position/direction.
                }
            }
        }


    }

    public void jouerTourp1() {
        //Demande a l'utilisateur les coordonnées
        System.out.println("Entrez la ligne");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("Entrez la colonne");
        int y = sc.nextInt();
        clearConsole();
        System.out.println("Vous avez tiré en x: "+x+", y: "+y);

        while (!this.p2.getGrille().tirer(x, y)){
            System.out.println("Entrez une valeur correcte");
            System.out.println("Entrez la ligne");
            x = sc.nextInt();
            System.out.println("Entrez la colonne");
             y = sc.nextInt();
            clearConsole();
            System.out.println("Vous avez tiré en x: "+x+", y: "+y);
        }

        this.p2.getGrille().afficherGrille();
        myTurn = !myTurn;
    }

    public void jouerTourp2() {
        int x = random.nextInt(p1.getGrille().getLignes());
        int y = random.nextInt(p1.getGrille().getColonnes());


        while (!this.p1.getGrille().tirer(x, y)){
             x = random.nextInt(p1.getGrille().getLignes());
             y = random.nextInt(p1.getGrille().getColonnes());
        }

        System.out.println("l'ordi a tiré en x: "+x+", y: "+y);
        this.p1.getGrille().afficherGrille();
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
       return p1.estTermine() || p2.estTermine();
   }

   public static void main(String[] args){
       int lig = 10;
       int col = 10;
       Jeu jeu = new Jeu(lig , col , Arrays.asList(2));
       System.out.println("DEBUT DU JEU");
       jeu.getP2().getGrille().cheatMode();
       while(!jeu.isFinished()){
            if (jeu.myTurn){
                jeu.jouerTourp1();

            }else{
                jeu.jouerTourp2();
            }
       }
       boolean p1Win = !jeu.p1.estTermine();
       System.out.println("Fin du jeu");
       if (p1Win){
           System.out.println("Le joueur a win");
       } else{
           System.out.println("l'ordi a win");
       }
   }
}

