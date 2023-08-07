package com.emile.projetaout.javafx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Menu extends Application{


    private Stage mainStage;



    public void start(Stage stage) throws IOException {
        mainStage = stage;
        showMainMenu();

    }
    private void startGame() {
        Battleshiptest gameScreen = new Battleshiptest();

        // définir les actions nécessaires pour les boutons de l'écran du jeu ici

        Scene gameScene = new Scene(gameScreen.createContent(10, 10, Arrays.asList(3)), 600, 800);

        mainStage.setScene(gameScene);
    }

    private void showOptions() {
        Options optionsScreen = new Options();

        optionsScreen.getReturnButton().setOnAction(event -> showMainMenu());

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
