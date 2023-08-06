package com.emile.projetaout.javafx;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameMenu extends Parent {
    Button options = new Button("OPTIONS");
    Button play = new Button("PLAY");
    Button exit = new Button("EXIT");
    GameMenu() {


        VBox menu = new VBox(10);





        menu.getChildren().addAll(play, options, exit);
        getChildren().addAll(menu);

    }

    public Button getStartButton() {
        return play;
    }

    public Button getOptionsButton() {
        return options;
    }

    public Button getExitButton() {
        return exit;
    }
}
