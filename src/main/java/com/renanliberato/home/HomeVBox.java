package com.renanliberato.home;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Created by renan on 21/12/2016.
 */
public class HomeVBox {
    private final GridPane pane;
    private Button initialButton, topButton, bottomButton;
    private int row, column;

    public HomeVBox(GridPane pane, Button initialButton, Button topButton, Button bottomButton, int row, int column) {
        this.pane = pane;
        this.initialButton = initialButton;
        this.topButton = topButton;
        this.bottomButton = bottomButton;
        this.row = row;
        this.column = column;
        setUp();
    }

    private void setUp() {
        VBox vBox = new VBox(topButton, bottomButton);

        initialButton.setOnAction(event -> {
            double width = initialButton.getWidth();
            double height = initialButton.getHeight()/2;
            bottomButton.setPrefSize(width, height);
            topButton.setPrefSize(width, height);
            FadeTransition expandTransition = new FadeTransition(Duration.millis(100), initialButton);
            expandTransition.setFromValue(1.0);
            expandTransition.setToValue(0.0);
            expandTransition.play();
            expandTransition.setOnFinished(eventTransition -> {
                pane.getChildren().remove(initialButton);
                pane.add(vBox, column, row);
                FadeTransition afterExpandTransition = new FadeTransition(Duration.millis(100), vBox);
                afterExpandTransition.setFromValue(0.0);
                afterExpandTransition.setToValue(1.0);
                afterExpandTransition.play();
            });
        });
    }

    public void setTopButtonAction(EventHandler<ActionEvent> eventHandler) {
        topButton.setOnAction(eventHandler);
    }

    public void setBottomButtonAction(EventHandler<ActionEvent> eventHandler) {
        bottomButton.setOnAction(eventHandler);
    }
}
