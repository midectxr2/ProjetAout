package com.emile.projetaout.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu extends Application{


    private Stage mainStage;




    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setResizable(false);
        showMainMenu();

    }
    private void startGame() {
        Battleshiptest gameScreen = new Battleshiptest();

        // définir les actions nécessaires pour les boutons de l'écran du jeu ici

        Scene gameScene = new Scene(gameScreen.createContent(10, 10, Arrays.asList(5, 4, 3, 3, 2)), 600, 800);

        mainStage.setScene(gameScene);
    }

    private void startGame(int row, int col, List<Integer> boatList) {
        System.out.println(boatList);
        Battleshiptest gameScreen = new Battleshiptest();

        // définir les actions nécessaires pour les boutons de l'écran du jeu ici
        Scene gameScene = new Scene(gameScreen.createContent(row, col, boatList), 600, 800);

        mainStage.setScene(gameScene);
    }

    private void showOptions() {
        Options optionsScreen = new Options();

        optionsScreen.getReturnButton().setOnAction(event -> showMainMenu());


        optionsScreen.getStartButton().setOnMouseClicked(event -> {
            int col = optionsScreen.getRes_col();
            int row = optionsScreen.getRes_row();
            List<Integer> integerList = optionsScreen.listBoats;


            startGame(row, col, integerList);
        });

        // définir les actions nécessaires pour les boutons de l'écran d'options ici

        Scene optionsScene = new Scene(optionsScreen, 600, 800);

        mainStage.setScene(optionsScene);
    }

    private void showMainMenu() {
        GameMenu mainMenu = new GameMenu();

        mainMenu.getStartButton().setOnAction(event -> startGame());
        mainMenu.getOptionsButton().setOnAction(event -> showOptions());
        mainMenu.getExitButton().setOnAction(event -> System.exit(0));

        Scene mainMenuScene = new Scene(mainMenu, 600, 800);

        mainStage.setScene(mainMenuScene);
        mainStage.show();
    }
}
