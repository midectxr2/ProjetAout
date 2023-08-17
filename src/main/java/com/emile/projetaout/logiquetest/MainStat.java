package com.emile.projetaout.logiquetest;

import java.util.Arrays;

public class MainStat {
    private int score;
    private int averageScore;
    private int currentScore;

    public void gameGenerator(int games){
        score = 0;

        for(int i=0; i < games;i++){
            System.out.println("parties: "+i);
            currentScore = 0;
            Game game = new Game();
            game.setSmartIaVsIa(10, 10, Arrays.asList(2, 3, 3, 4, 5));

            while(!game.isFinished()){
                game.play();
                score++;
                currentScore++;
            }
            System.out.println("Score de la partie:  " + currentScore/2);
        }
        System.out.println("Score total de "+games+" jeux : " + score /2);
        averageScore = score / (games *2);
        System.out.println("Score moyen de " +games + " jeux : " +averageScore);

    }


    public static void main(String[] args){
        int games =Integer.parseInt(args[0]);
        MainStat mainStat =new MainStat();
        mainStat.gameGenerator(games);
    }
}