package com.emile.projetaout.javafx;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameMenu extends Parent {
    Button SmartIaPlay = new Button("PLAY VS SMARTIA");
    Button IaPlay = new Button("PLAY IA");
    Button options = new Button("OPTIONS");
    Button play = new Button("PLAY");
    Button exit = new Button("EXIT");

    //constructeur de Gamemenu, permet de creer une vbox et d'y ajouter tout les boutons du menu principal
    public GameMenu() {

        VBox menu = new VBox(10);

        menu.getChildren().addAll(play,IaPlay, SmartIaPlay, options, exit);
        getChildren().addAll(menu);

    }

    //getter de start button
    public Button getStartButton() {
        return play;
    }


    //getter de options boutton
    public Button getOptionsButton() {
        return options;
    }

    //getter de exit boutton
    public Button getExitButton() {
        return exit;
    }

    //getter de iaplayboutton
    public Button getIaPlayButton(){return IaPlay;}

    public Button getSmartIaPlay(){
        return SmartIaPlay;
    }
}
