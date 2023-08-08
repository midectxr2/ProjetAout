package com.emile.projetaout.javafx;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicInteger;

public class Display {

    private int number;



    public HBox display(){
        AtomicInteger nbr = new AtomicInteger();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setSpacing(10);
        Button buttonLess = new Button("-");
        Button buttonMore = new Button("+");
        Text text = new Text(Integer.toString(nbr.get()));

        buttonMore.setOnMouseClicked(event -> {
            text.setText(Integer.toString(nbr.get()));
            nbr.getAndIncrement();
            number++;
        });
        buttonLess.setOnMouseClicked(event -> {
            text.setText(Integer.toString(nbr.get()));
            nbr.getAndDecrement();
            number--;
        });



        hBox.getChildren().addAll(buttonLess, text, buttonMore);
        return hBox;


    }

    public int getNumber() {
        return number-1;
    }
}
