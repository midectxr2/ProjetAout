package com.emile.projetaout.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu extends Application{


    private Stage mainStage;

    private int width = 1000;
    private int height = 900;





    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setResizable(false);
        showMainMenu();

    }


    //methode startgame, qui permet de creer le jeu en joueur vs ia sur la configuration de base
    private void startGame() {
        Battleshiptest gameScreen = new Battleshiptest(mainStage);


        Scene gameScene = new Scene(gameScreen.createContent(10, 10, Arrays.asList(5, 4, 3, 3, 2)), width, height);
        Popup popup = gameScreen.getPopup();

        Label label = new Label();

        Button btn1 = new Button("REJOUER");
        btn1.setOnAction(event -> {
            showOptions();
            popup.hide();
        });
        Button btn2 = new Button("RECOMMENCER");
        btn2.setOnAction(event -> {
            startGame();
            popup.hide();

        });
        Button btn3 = new Button("QUITTER");
        btn3.setOnAction(event -> {
            System.exit(0);
        });


        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btn1, btn2, btn3);


        //bout de code de chatgpt
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: white;");
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonBox);
        layout.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        popup.getContent().add(layout);


        mainStage.setScene(gameScene);
    }



    //methode startgame, qui permet de creer le jeu en joueur vs ia sur la configuration modifiée dans les options
    private void startGame(int row, int col, List<Integer> boatList) {

        Battleshiptest gameScreen = new Battleshiptest(mainStage);

        Scene gameScene = new Scene(gameScreen.createContent(row, col, boatList), width, height);
        Popup popup = gameScreen.getPopup();

        Label label = new Label();

        Button btn1 = new Button("REJOUER");
        btn1.setOnAction(event -> {
            showOptions();
            popup.hide();
        });
        Button btn2 = new Button("RECOMMENCER");
        btn2.setOnAction(event -> {
            startGame(row, col, boatList);
            popup.hide();

        });
        Button btn3 = new Button("QUITTER");
        btn3.setOnAction(event -> {
            System.exit(0);
        });


        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btn1, btn2, btn3);

        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: white;");
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonBox);
        layout.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");

        popup.getContent().add(layout);
        mainStage.setScene(gameScene);
    }



    //methode startgameia, qui permet de creer le jeu en smartia vs ia sur la configuration de base

    public void startGameIa(){
        Battleshiptest gameScreen = new Battleshiptest(mainStage);
        Scene scene = new Scene(gameScreen.createContentSmartIaVsIa(10, 10, Arrays.asList(5, 4, 3, 3, 2)), width, height);
        Popup popup = gameScreen.getPopup();

        Label label = new Label();

        Button btn1 = new Button("REJOUER");
        btn1.setOnAction(event -> {
            showOptions();
            popup.hide();
        });
        Button btn2 = new Button("RECOMMENCER");
        btn2.setOnAction(event -> {
            startGameIa();
            popup.hide();

        });
        Button btn3 = new Button("QUITTER");
        btn3.setOnAction(event -> {
            System.exit(0);
        });


        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btn1, btn2, btn3);

        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: white;");
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonBox);
        layout.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");

        popup.getContent().add(layout);
        mainStage.setScene(scene);
    }


    //methode startgameia, qui permet de creer le jeu en smartia vs ia sur la configuration modifiée
    public void startGameIa(int row, int col, List<Integer> boatList){
        Battleshiptest gameScreen = new Battleshiptest(mainStage);
        Scene scene = new Scene(gameScreen.createContentSmartIaVsIa(row, col, boatList), width, height);
        Popup popup = gameScreen.getPopup();

        Label label = new Label();

        Button btn1 = new Button("REJOUER");
        btn1.setOnAction(event -> {
            showOptions();
            popup.hide();
        });
        Button btn2 = new Button("RECOMMENCER");
        btn2.setOnAction(event -> {
            startGameIa(row, col, boatList);
            popup.hide();

        });
        Button btn3 = new Button("QUITTER");
        btn3.setOnAction(event -> {
            System.exit(0);
        });


        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btn1, btn2, btn3);

        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: white;");
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonBox);
        layout.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");

        popup.getContent().add(layout);
        mainStage.setScene(scene);
    }

    //methode showoptions qui permet d'afficher le menu des otpions ainsi que de recuperer les options selectionées
    private void showOptions() {
        Options optionsScreen = new Options();

        optionsScreen.getReturnButton().setOnAction(event -> showMainMenu());


        optionsScreen.getStartButton().setOnMouseClicked(event -> {
            int col = optionsScreen.getRes_col();
            int row = optionsScreen.getRes_row();
            List<Integer> integerList = optionsScreen.getBoat();

            startGame(row, col, integerList);
        });

        optionsScreen.getStartIaButton().setOnMouseClicked(event -> {
            int col = optionsScreen.getRes_col();
            int row = optionsScreen.getRes_row();
            List<Integer> integerList = optionsScreen.getBoat();

            startGameIa(row, col, integerList);
        });


        Scene optionsScene = new Scene(optionsScreen, width, height);

        mainStage.setScene(optionsScene);
    }

    //methode showmainmenu qui permet d'afficher le menu
    private void showMainMenu() {
        GameMenu mainMenu = new GameMenu();
        mainMenu.getIaPlayButton().setOnAction(event -> startGameIa());
        mainMenu.getStartButton().setOnAction(event -> startGame());
        mainMenu.getOptionsButton().setOnAction(event -> showOptions());
        mainMenu.getExitButton().setOnAction(event -> System.exit(0));

        Scene mainMenuScene = new Scene(mainMenu, width, height);

        mainStage.setScene(mainMenuScene);
        mainStage.show();
    }

}
