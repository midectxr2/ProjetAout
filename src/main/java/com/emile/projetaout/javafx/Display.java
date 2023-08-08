package com.emile.projetaout.javafx;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javax.net.ssl.HostnameVerifier;
import java.util.concurrent.atomic.AtomicInteger;

public class Display extends HBox {

    private int number = 0;


    public Display(int nb){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setSpacing(10);
        Button buttonLess = new Button("-");
        Button buttonMore = new Button("+");
        Text text = new Text(Integer.toString(0));

        buttonMore.setOnMouseClicked(event -> {
            text.setText(Integer.toString(number));
            ++number;
        });

        buttonLess.setOnMouseClicked(event -> {
            text.setText(Integer.toString(number));
            --number;
        });

        this.getChildren().addAll(new Text("" + nb), buttonLess, text, buttonMore);
    }

    public int getNumber() {
        return number-1;
    }
}
